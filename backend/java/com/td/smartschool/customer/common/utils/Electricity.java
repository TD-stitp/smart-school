package com.td.smartschool.customer.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2021年9月9日 20:03:05
 */

public class Electricity {

    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "202";

    private static final String VALIDATE_CODE_URL = "http://218.91.39.59:8998/Login/GetValidateCode?time=";
    private static final String RSA_KEY_URL = "http://218.91.39.59:8998/Common/GetRsaKey";
    private static final String LOGIN_URL = "http://218.91.39.59:8998/Login/LoginBySnoQuery";
    private static final String UPDATE_PASSWORD_URL = "http://218.91.39.59:8998/User/MendQueryPasswd";
    // QUERY_BATTERY_URL 需获得账号ticket并拼接到url当中，否则会出错
    private static final String QUERY_BATTERY_URL = "http://218.91.39.59:8988//web/Common/Tsm.html?ticket=";
    private static final String QUERY_FLOW_URL = "http://218.91.39.59:8998/Report/GetPersonTrjn";
    private static final String CARD_FLAG_URL = "http://218.91.39.59:8998/User/GetCardInfoByAccountNoParm";
    private static final String CARD_BALANCE_URL = "http://218.91.39.59:8998/User/GetCardAccInfo";
    private static final String LOST_CARD_URL = "http://218.91.39.59:8998/User/LostCard";
    private static final String UNLOST_CARD_URL = "http://218.91.39.59:8998/User/UnLostCard";
    private static final String CARD_RECHARGE_URL = "http://218.91.39.59:8998/User/Account_Pay";
    private static final String GET_JSESSIONID_URL = "http://218.91.39.59:8988/web/common/checkEle.html?ticket=";
    private static final String BUY_ELECTRICITY_URL = "http://218.91.39.59:8988/web/Elec/PayElecGdc.html";

    private final Subject subject = SecurityUtils.getSubject();
    private final Session session = subject.getSession();
    private String pageToken = null;

    private CloseableHttpClient httpClient;
    private BasicCookieStore basicCookieStore;

    public Electricity() {
        init();
    }

    private void init() {
        basicCookieStore = new BasicCookieStore();
        httpClient = HttpClients
                .custom()
                .setDefaultCookieStore(basicCookieStore)
                .build();
        getHttpClientWithCookies();
    }

    private String getUsername() {
        return (String) session.getAttribute("username");
    }

    private String getAccount() {
        return (String) session.getAttribute("account");
    }

    private HttpRequestBase setHeader(HttpRequestBase httpRequest, String token, String referer) {
        httpRequest.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
        httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpRequest.setHeader("Connection", "keep-alive");
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpRequest.setHeader("Host", "218.91.39.59:8998");
        httpRequest.setHeader("Origin", "http://218.91.39.59:8998");
        httpRequest.setHeader("Referer", referer);
        httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.54 Safari/537.36");
        httpRequest.setHeader("X-Request-With", "XMLHttpRequest");
        httpRequest.setHeader("X_REQUESTED_WITH", token);
        return httpRequest;
    }

    private HttpRequestBase setHeader(HttpRequestBase httpRequest, String token) {
        return setHeader(httpRequest, token, "http://218.91.39.59:8998/");
    }

    private CloseableHttpClient getHttpClientWithCookies() {
        String sessionID = (String) session.getAttribute("ASP.NET_SessionId");
        String pageToken = (String) session.getAttribute("pageToken");
        if (sessionID != null && pageToken != null) {
            this.pageToken = pageToken;

            BasicCookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie("ASP.NET_SessionId", sessionID);
            cookie.setVersion(0);
            cookie.setDomain("218.91.39.59");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);

            cookie = new BasicClientCookie("pageToken", pageToken);
            cookie.setVersion(0);
            cookie.setDomain("218.91.39.59");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);

            String username = (String) session.getAttribute("username");
            if (username != null) {
                cookie = new BasicClientCookie("username", username);
                cookie.setVersion(0);
                cookie.setDomain("218.91.39.59");
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }

            String ticket = (String) session.getAttribute("hallticket");
            if (ticket != null) {
                cookie = new BasicClientCookie("hallticket", ticket);
                cookie.setVersion(0);
                cookie.setDomain("218.91.39.59");
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }

            httpClient = HttpClients
                    .custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();
            basicCookieStore = cookieStore;
        }
        return httpClient;
    }

    private List<NameValuePair> getParams(String roomNumber) {
        List<NameValuePair> params = new ArrayList<>();
        String preData;
        if (roomNumber.length() == 4) {
            int buildNum = Integer.parseInt(roomNumber.substring(0, 1));
            preData = "{ \"query_elec_roominfo\": { \"aid\":\"0030000000002501\", \"account\": \"17125\",\"room\": { \"roomid\":" +
                    "\"" + roomNumber + "\"" + ", \"room\": \"" + roomNumber + "\" }," +
                    "\"floor\": { \"floorid\": \"\", \"floor\": \"\" }, \"area\": { \"area\": \"南京邮电大学通达学院\", \"areaname\": " +
                    "\"南京邮电大学通达学院\" }, \"building\": { \"buildingid\": \"" + (buildNum + 1) + "\", \"building\": \"" +
                    buildNum + "号楼\" },\"extdata\":\"info1=\" } }";
        } else {
            preData = "{ \"query_elec_roominfo\": { \"aid\":\"0030000000012301\", \"account\": \"17125\",\"room\": { \"roomid\":" +
                    "\"" + roomNumber + "\"" + ", \"room\": \"" + roomNumber + "\" }," +
                    "\"floor\": { \"floorid\": \"\", \"floor\": \"\" }, \"area\": { \"area\": \"1\", \"areaname\": " +
                    "\"扬州通达\" }, \"building\": { \"buildingid\": \"" + 3716 + "\", \"building\": \"" +
                    10 + "号楼\" },\"extdata\":\"info1=\" } }";
        }
        params.add(new BasicNameValuePair("jsondata", preData));
        params.add(new BasicNameValuePair("funname", "synjones.onecard.query.elec.roominfo"));
        params.add(new BasicNameValuePair("json", "true"));
        return params;
    }

    private List<NameValuePair> getEleParams(String account, double tran, String roomID) {
        String aID;
        String buildingID;
        String building;
        String areaID;
        String areaName;
        if (roomID.length() == 5) {
            aID = "0030000000012301";
            buildingID = "3716";
            building = "10号楼";
            areaID = "1";
            areaName = "扬州通达";
        } else {
            aID = "0030000000002501";
            int num = Integer.parseInt(roomID.substring(0, 1));
            buildingID = String.valueOf(num + 1);
            building = num + "号楼";
            areaID = "南京邮电大学通达学院";
            areaName = "南京邮电大学通达学院";
        }
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("acctype", "000"));
        params.add(new BasicNameValuePair("paytype", "3"));
        params.add(new BasicNameValuePair("aid", aID));
        params.add(new BasicNameValuePair("account", account));
        params.add(new BasicNameValuePair("tran", String.valueOf(tran * 100)));
        params.add(new BasicNameValuePair("roomid", roomID));
        params.add(new BasicNameValuePair("room", roomID));
        params.add(new BasicNameValuePair("floorid", ""));
        params.add(new BasicNameValuePair("floor", ""));
        params.add(new BasicNameValuePair("buildingid", buildingID));
        params.add(new BasicNameValuePair("building", building));
        params.add(new BasicNameValuePair("areaid", areaID));
        params.add(new BasicNameValuePair("areaname", areaName));
        params.add(new BasicNameValuePair("qpwd", ""));
        params.add(new BasicNameValuePair("json", "true"));
        System.out.println(params);
        return params;
    }

    private String getCookie() {
        HttpGet sessionGet = new HttpGet(GET_JSESSIONID_URL + SecurityUtils.getSubject().getSession().getAttribute("hallticket"));
        sessionGet.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        try {
            httpClient.execute(sessionGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder str = new StringBuilder();
        for (Cookie cookie : basicCookieStore.getCookies()) {
            if ("JSESSIONID".equals(cookie.getName())) {
                str.append(cookie.getName());
                str.append("=");
                str.append(cookie.getValue());
            }
        }
        return str.toString();
    }

    public JSONObject queryBattery(String roomNumber) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJSON = new JSONObject().put("code", FAIL_CODE);
        HttpPost queryPost = new HttpPost(QUERY_BATTERY_URL);
        setHeader(queryPost, pageToken);
        List<NameValuePair> queryParams = getParams(roomNumber);
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = httpClient.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf8");
                try {
                    tempJSON = new JSONObject(emJson);
                    tempJSON.put("code", SUCCESS_CODE);
                    result.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("电量余量 JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("电量余量 请求失败");
        } finally {
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("电量余量 response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("电量余量 httpClient请求失败");
            }
        }
        tempJSON = tempJSON.getJSONObject("query_elec_roominfo");
        result.put("msg", tempJSON.getString("errmsg"));
        return result;
    }

    public InputStream getValidateCode(String time) {
        InputStream result = null;
        HttpGet codeGet = new HttpGet(VALIDATE_CODE_URL + time);
        // image Accept
        codeGet.setHeader("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
        CloseableHttpResponse codeResponse = null;
        try {
            codeResponse = httpClient.execute(codeGet);
            if (String.valueOf(codeResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                HttpEntity entity = codeResponse.getEntity();
                if (entity != null) {
                    result = entity.getContent();
                }
                List<Cookie> cookies = basicCookieStore.getCookies();
                for (Cookie cookie : cookies) {
                    session.setAttribute(cookie.getName(), cookie.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println("电力系统获取验证码请求失败");
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("httpClient关闭失败");
            }
        }
        return result;
    }

    public JSONObject getRSAKey() {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        HttpPost encrypPost = new HttpPost(RSA_KEY_URL);
        setHeader(encrypPost, pageToken);
        CloseableHttpResponse rsaResponse = null;
        try {
            rsaResponse = httpClient.execute(encrypPost);
            if (String.valueOf(rsaResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(rsaResponse.getEntity(), "utf8");
                try {
                    result = new JSONObject(emJson);
                    result.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("RSAkey JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("RSAkey 请求失败");
        } finally {
            try {
                if (rsaResponse != null) {
                    rsaResponse.close();
                }
            } catch (IOException e) {
                System.out.println("RSAkey response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("RSAkey httpClient请求失败");
            }
        }
        return result;
    }

    public JSONObject login(String key, String code, String username, String password) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost loginPost = new HttpPost(LOGIN_URL);
        setHeader(loginPost, pageToken);
        List<NameValuePair> loginParams = new ArrayList<>();
        loginParams.add(new BasicNameValuePair("sno", (String) SecurityUtils.getSubject().getSession().getAttribute("username")));
//        loginParams.add(new BasicNameValuePair("sno", username));
        loginParams.add(new BasicNameValuePair("pwd", password));
        loginParams.add(new BasicNameValuePair("ValiCode", code));
        loginParams.add(new BasicNameValuePair("remember", "1"));
        loginParams.add(new BasicNameValuePair("uclass", "1s"));
        loginParams.add(new BasicNameValuePair("zqcode", ""));
        loginParams.add(new BasicNameValuePair("key", key));
        loginParams.add(new BasicNameValuePair("josn", "true"));
        CloseableHttpResponse loginResponse = null;
        try {
            loginPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
            loginResponse = httpClient.execute(loginPost);
            if (String.valueOf(loginResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(loginResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("登陆信息JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("登陆请求失败");
        } finally {
            try {
                if (loginResponse != null) {
                    loginResponse.close();
                }
            } catch (IOException e) {
                System.out.println("loginResponse关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("login httpClient请求失败");
            }
        }
        if ((Boolean) tempJson.get("IsSucceed")) {
            result.put("code", SUCCESS_CODE);
            result.put("msg", "登录成功");
            String account = tempJson.getString("Obj");
            result.put("account", account);
            String ticket = tempJson.getString("Obj2");
            result.put("ticket", ticket);
            session.setAttribute("hallticket", ticket);
            session.setAttribute("account", account);
        } else {
            result.put("msg", tempJson.getString("Msg"));
        }
        return result;
    }

    public JSONObject updatePassword(String key, String account, String oldPassword, String password) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost updatePost = new HttpPost(UPDATE_PASSWORD_URL);
        setHeader(updatePost, pageToken);
        List<NameValuePair> updateParam = new ArrayList<>();
        updateParam.add(new BasicNameValuePair("acc", (String) SecurityUtils.getSubject().getSession().getAttribute("account")));
        updateParam.add(new BasicNameValuePair("pwd", oldPassword));
        updateParam.add(new BasicNameValuePair("npwd", password));
        updateParam.add(new BasicNameValuePair("vcode", ""));
        updateParam.add(new BasicNameValuePair("key", key));
        updateParam.add(new BasicNameValuePair("josn", "true"));
        CloseableHttpResponse updateResponse = null;
        try {
            updatePost.setEntity(new UrlEncodedFormEntity(updateParam, "utf8"));
            updateResponse = httpClient.execute(updatePost);
            if (String.valueOf(updateResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(updateResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("修改密码JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("登陆请求失败");
        } finally {
            try {
                if (updateResponse != null) {
                    updateResponse.close();
                }
            } catch (IOException e) {
                System.out.println("修改密码Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("修改密码 httpClient请求失败");
            }
        }
        if ((Boolean) tempJson.get("IsSucceed")) {
            result.put("code", SUCCESS_CODE);
            result.put("msg", tempJson.getString("RMsg"));
        }
        return result;
    }

    public JSONObject queryFlow(String startDate, String endDate, String account) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost flowPost = new HttpPost(QUERY_FLOW_URL);
        setHeader(flowPost, pageToken);
        List<NameValuePair> flowParams = new ArrayList<>();
        flowParams.add(new BasicNameValuePair("sdate", startDate));
        flowParams.add(new BasicNameValuePair("edate", endDate));
        flowParams.add(new BasicNameValuePair("account", (String) SecurityUtils.getSubject().getSession().getAttribute("account")));
        flowParams.add(new BasicNameValuePair("page", "1"));
        flowParams.add(new BasicNameValuePair("rows", "9999"));
        CloseableHttpResponse flowResponse = null;
        try {
            flowPost.setEntity(new UrlEncodedFormEntity(flowParams, "utf8"));
            flowResponse = httpClient.execute(flowPost);
            if (String.valueOf(flowResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(flowResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("流水信息JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("流水信息请求失败");
        } finally {
            try {
                if (flowResponse != null) {
                    flowResponse.close();
                }
            } catch (IOException e) {
                System.out.println("flowResponse关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("flow httpClient请求失败");
            }
        }
        if (tempJson.get("code").equals(SUCCESS_CODE)) {
            result.put("code", SUCCESS_CODE);
            result.put("total", String.valueOf(tempJson.get("total")));
            JSONArray array = tempJson.getJSONArray("rows");
            JSONArray temp = new JSONArray();
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("ROW", array.getJSONObject(i).get("RO"));
                jsonObject.put("OCCTIME", array.getJSONObject(i).getString("OCCTIME"));
                jsonObject.put("MERCNAME", array.getJSONObject(i).getString("MERCNAME").strip());
                jsonObject.put("TRANAMT", array.getJSONObject(i).get("TRANAMT"));
                jsonObject.put("TRANNAME", array.getJSONObject(i).getString("TRANNAME").strip());
                jsonObject.put("CARDBAL", array.getJSONObject(i).get("CARDBAL"));
                jsonObject.put("POSCODE", array.getJSONObject(i).get("POSCODE"));
                temp.put(jsonObject);
            }
            result.put("rows", temp);
        }
        return result;
    }

    public JSONObject cardFlag() {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost cardFlagPost = new HttpPost(CARD_FLAG_URL);
        setHeader(cardFlagPost, pageToken);
        List<NameValuePair> cardFlagParams = new ArrayList<>();
        cardFlagParams.add(new BasicNameValuePair("account", ""));
        cardFlagParams.add(new BasicNameValuePair("json", "true"));
        CloseableHttpResponse cardFlagResponse = null;
        try {
            cardFlagPost.setEntity(new UrlEncodedFormEntity(cardFlagParams, "utf8"));
            cardFlagResponse = httpClient.execute(cardFlagPost);
            if (String.valueOf(cardFlagResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(cardFlagResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("查询卡挂失状态JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("查询卡挂失状态请求失败");
        } finally {
            try {
                if (cardFlagResponse != null) {
                    cardFlagResponse.close();
                }
            } catch (IOException e) {
                System.out.println("查询卡挂失状态Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("查询卡挂失状态 httpClient请求失败");
            }
        }
        String flag = new JSONObject(tempJson.getString("Msg"))
                .getJSONObject("query_card")
                .getJSONArray("card")
                .getJSONObject(0)
                .getString("lostflag");
        if ("0".equals(flag)) {
            result.put("code", SUCCESS_CODE);
            result.put("flag", false);
        } else if ("1".equals(flag)) {
            result.put("code", SUCCESS_CODE);
            result.put("flag", true);
        }
        return result;
    }

    public JSONObject cardBalance(String account) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost balancePost = new HttpPost(CARD_BALANCE_URL);
        setHeader(balancePost, pageToken);
        List<NameValuePair> balanceParams = new ArrayList<>();
        balanceParams.add(new BasicNameValuePair("acc", (String) SecurityUtils.getSubject().getSession().getAttribute("account")));
        balanceParams.add(new BasicNameValuePair("json", "true"));
        CloseableHttpResponse balanceResponse = null;
        try {
            balancePost.setEntity(new UrlEncodedFormEntity(balanceParams, "utf8"));
            balanceResponse = httpClient.execute(balancePost);
            if (String.valueOf(balanceResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(balanceResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("查询卡余额JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("查询卡余额请求失败");
        } finally {
            try {
                if (balanceResponse != null) {
                    balanceResponse.close();
                }
            } catch (IOException e) {
                System.out.println("查询卡余额Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("查询卡余额 httpClient请求失败");
            }
        }
        String balanceStr = new JSONObject(tempJson.getString("Msg").replace("\\", ""))
                .getJSONObject("query_accinfo")
                .getJSONArray("accinfo")
                .getJSONObject(0)
                .getString("balance");
        double balance = Integer.parseInt(balanceStr);
        if (!"0".equals(balanceStr)) {
            balance = balance / 100;
        }
        result.put("code", SUCCESS_CODE);
        result.put("balance", balance);
        return result;
    }

    public JSONObject lostCard(String account, String password, Boolean lostFlag) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost lostPost;
        if (lostFlag) {
            lostPost = new HttpPost(UNLOST_CARD_URL);
        } else {
            lostPost = new HttpPost(LOST_CARD_URL);
        }
        setHeader(lostPost, pageToken);
        List<NameValuePair> lostParams = new ArrayList<>();
        lostParams.add(new BasicNameValuePair("acc", (String) SecurityUtils.getSubject().getSession().getAttribute("account")));
        lostParams.add(new BasicNameValuePair("pwd", password));
        lostParams.add(new BasicNameValuePair("json", "true"));
        CloseableHttpResponse lostResponse = null;
        try {
            lostPost.setEntity(new UrlEncodedFormEntity(lostParams, "utf8"));
            lostResponse = httpClient.execute(lostPost);
            if (String.valueOf(lostResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(lostResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("一卡通挂失/解挂JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("一卡通挂失/解挂请求失败");
        } finally {
            try {
                if (lostResponse != null) {
                    lostResponse.close();
                }
            } catch (IOException e) {
                System.out.println("一卡通挂失/解挂Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("一卡通挂失/解挂 httpClient请求失败");
            }
        }
        if ((Boolean) tempJson.get("IsSucceed")) {
            result.put("code", SUCCESS_CODE);
            result.put("msg", tempJson.getString("RMsg"));
        }
        return result;
    }

    public JSONObject cardRecharge(String account, double tranamt, String password) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost rechargePost = new HttpPost(CARD_RECHARGE_URL);
        setHeader(rechargePost, pageToken);
        List<NameValuePair> rechargeParams = new ArrayList<>();
        rechargeParams.add(new BasicNameValuePair("account", (String) SecurityUtils.getSubject().getSession().getAttribute("account")));
        rechargeParams.add(new BasicNameValuePair("acctype", "000"));
        rechargeParams.add(new BasicNameValuePair("tranamt", String.valueOf(tranamt * 100)));
        rechargeParams.add(new BasicNameValuePair("qpwd", password));
        rechargeParams.add(new BasicNameValuePair("paymethod", "2"));
        rechargeParams.add(new BasicNameValuePair("paytype", "使用绑定的默认账号"));
        rechargeParams.add(new BasicNameValuePair("client_type", "web"));
        rechargeParams.add(new BasicNameValuePair("json", "true"));
        CloseableHttpResponse rechargeResponse = null;
        try {
            rechargePost.setEntity(new UrlEncodedFormEntity(rechargeParams, "utf8"));
            rechargeResponse = httpClient.execute(rechargePost);
            if (String.valueOf(rechargeResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(rechargeResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("一卡通充值JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("一卡通充值请求失败");
        } finally {
            try {
                if (rechargeResponse != null) {
                    rechargeResponse.close();
                }
            } catch (IOException e) {
                System.out.println("一卡通充值Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("一卡通充值 httpClient请求失败");
            }
        }
        String msg = tempJson.getString("Msg");
        result.put("msg", msg);
        if ((Boolean) tempJson.get("IsSucceed")) {
            result.put("code", SUCCESS_CODE);
            msg = new JSONObject(msg.replace("\\", ""))
                    .getJSONObject("transfer")
                    .getString("errmsg");
            result.put("msg", msg);

        }
        return result;
    }

    public JSONObject buyElectricity(String account, double tran, String roomID) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        HttpPost buyPost = new HttpPost(BUY_ELECTRICITY_URL);
        setHeader(buyPost, pageToken);
        buyPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        buyPost.setHeader("Cookie", getCookie());
        List<NameValuePair> buyParams = getEleParams((String) SecurityUtils.getSubject().getSession().getAttribute("account"), tran, roomID);
        CloseableHttpResponse buyResponse = null;
        try {
            buyPost.setEntity(new UrlEncodedFormEntity(buyParams, "utf8"));
            buyResponse = httpClient.execute(buyPost);
            if (String.valueOf(buyResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String json = EntityUtils.toString(buyResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(json);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("宿舍购电JSON解析失败");
                }
            }
        } catch (Exception e) {
            System.out.println("宿舍购电请求失败");
        } finally {
            try {
                if (buyResponse != null) {
                    buyResponse.close();
                }
            } catch (IOException e) {
                System.out.println("宿舍购电Response关闭失败");
            }
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                System.out.println("宿舍购电 httpClient请求失败");
            }
        }
        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            JSONObject temp = tempJson.getJSONObject("pay_elec_gdc");
            result.put("code", SUCCESS_CODE);
            result.put("msg", temp.getString("errmsg"));
            result.put("tranamt", Integer.parseInt(temp.getString("tranamt")) / 100);
        }
        return result;
    }
}
