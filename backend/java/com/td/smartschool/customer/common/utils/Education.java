package com.td.smartschool.customer.common.utils;

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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author K8lyN
 * @version 1.0
 * @date 2021年9月9日 20:03:05
 */

public class Education {

    public static final String SUCCESS_CODE = "200";
    public static final String FAIL_CODE = "202";

    private static final String PUBLIC_KEY_URL = "https://jwxt.nytdc.edu.cn/jwglxt/xtgl/login_getPublicKey.html?time=";
    private static final String LOGIN_URL = "https://jwxt.nytdc.edu.cn/jwglxt/xtgl/login_slogin.html?time=";
    private static final String CHECK_PASSWORD_URL = "https://jwxt.nytdc.edu.cn/jwglxt/xtgl/mmgl_cxCheckRsaYhMm.html?gnmkdm=index";
    private static final String UPDATE_PASSWORD_URL = "https://jwxt.nytdc.edu.cn/jwglxt/xtgl/mmgl_xgRsaMm.html?gnmkdm=index";
    private static final String QUERY_CLASS_URL = "https://jwxt.nytdc.edu.cn/jwglxt/kbcx/xskbcx_cxXsgrkb.html";
    private static final String QUERY_EXAM_URL = "https://jwxt.nytdc.edu.cn/jwglxt/kwgl/kscx_cxXsksxxIndex.html?doType=query&gnmkdm=N358105";
    private static final String QUERY_SCORE_URL = "https://jwxt.nytdc.edu.cn/jwglxt/cjcx/cjcx_cxXsgrcj.html?doType=query&gnmkdm=N305005";
    private static final String QUERY_MESSAGE_URL = "https://jwxt.nytdc.edu.cn/jwglxt/xtgl/index_cxDbsy.html?doType=query";
    private static final String QUERY_GPA_URL = "http://jwxt.nytdc.edu.cn/jwglxt/xtgl/index_initMenu.html";

    // 以下URL未更新
    private static final String COURSES_PARAM_URL = "http://jwxt.nytdc.edu.cn/jwglxt/kcyx/kcyx_cxYxkcIndex.html?gnmkdm=N253525&layout=default";
    private static final String PRE_COURSES_URL = "http://jwxt.nytdc.edu.cn/jwglxt/kcyx/kcyx_cxYxkcListIndex.html?gnmkdm=N253525";
    private static final String SELECTED_COURSES_URL = "http://jwxt.nytdc.edu.cn/jwglxt/kcyx/kcyx_cxYxkcChoosed.html?gnmkdm=N253525";
    private static final String SELECT_COURSES_URL = "http://jwxt.nytdc.edu.cn/jwglxt/kcyx/kcyx_xkBcYxkc.html?gnmkdm=N253525";
    private static final String UNSELECT_COURSES_URL = "http://jwxt.nytdc.edu.cn/jwglxt/kcyx/kcyx_tuikScYxkc.html?gnmkdm=N253525";

    private static final String CURRENT_YEAR = "2022";

    private CloseableHttpClient httpClient;
    private BasicCookieStore basicCookieStore;

    private final Subject subject = SecurityUtils.getSubject();
    private final Session session = subject.getSession();

    public Education() {
        basicCookieStore = new BasicCookieStore();
        httpClient = HttpClients
                .custom()
                .setDefaultCookieStore(basicCookieStore)
                .build();
    }

    private HttpRequestBase setHeader(HttpRequestBase httpRequest) {
        //httpRequest.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpRequest.setHeader("Accept-Encoding", "gzip, deflate");
        httpRequest.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        httpRequest.setHeader("Host", "jwxt.nytdc.edu.cn");
        httpRequest.setHeader("Proxy-Connection", "keep-alive");
        httpRequest.setHeader("Origin", "https://jwxt.nytdc.edu.cn");
        //httpRequest.setHeader("Referer", "http://jwxt.nytdc.edu.cn/jwglxt/xtgl/login_slogin.html?time=" + System.currentTimeMillis());
        httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
        httpRequest.setHeader("Upgrade-Insecure-Requests", "1");
        return httpRequest;
    }

    private CloseableHttpClient getHttpClientWithCookies() {
        String big = (String) session.getAttribute("BIGipServerpool_xk");
        String sessionID = (String) session.getAttribute("JSESSIONID");
        if (big != null && sessionID != null) {
            BasicCookieStore cookieStore = new BasicCookieStore();

            BasicClientCookie cookie = new BasicClientCookie("BIGipServerpool_xk", big);
            cookie.setDomain("jwxt.nytdc.edu.cn");
            cookie.setPath("/");
            cookie.setVersion(0);
            cookieStore.addCookie(cookie);

            cookie = new BasicClientCookie("JSESSIONID", sessionID);
            cookie.setDomain("jwxt.nytdc.edu.cn");
            cookie.setPath("/jwglxt");
            cookie.setVersion(0);
            cookieStore.addCookie(cookie);

            httpClient = HttpClients
                    .custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

        }
        return httpClient;
    }

    private JSONObject getRSAKey() {
        getHttpClientWithCookies();
        JSONObject result = new JSONObject();
        HttpGet gpkHttpGet = new HttpGet(PUBLIC_KEY_URL + System.currentTimeMillis());
        setHeader(gpkHttpGet);
        CloseableHttpResponse gpkResponse = null;
        try {
            gpkResponse = httpClient.execute(gpkHttpGet);
            if (String.valueOf(gpkResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(gpkResponse.getEntity(), "utf8");
                result = new JSONObject(emJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                gpkResponse.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
        return result;
    }

    private String encryp(String exponent, String modulus, String password) {
        try {
            password = RSAUtil.encryptByRSAPublicKeySpec(password, modulus, exponent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    private String encryp(String password, JSONObject rsaKey) {
        String exponent = rsaKey.getString("exponent");
        String modulus = rsaKey.getString("modulus");

        //二、根据公钥进行密码加密
        //System.out.println(modulus);
        //System.out.println(exponent);
        //System.out.println(password);

        password = encryp(exponent, modulus, password);

        return password;
    }

    private String crawlCsrfToken(String timestamp) {
        String csrftoken;
        HttpGet csrftokenHttpGet = new HttpGet(LOGIN_URL + timestamp);
        CloseableHttpResponse csrftokenResponse = null;
        try {
            csrftokenResponse = httpClient.execute(csrftokenHttpGet);
            if (String.valueOf(csrftokenResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                Document csrftokenDoc = Jsoup.parse(EntityUtils.toString(csrftokenResponse.getEntity(), "utf8"));
                csrftoken = csrftokenDoc
                        .select(".col-sm-4")
                        .select(".sl_log_rt")
                        .select("input[id=csrftoken]")
                        .first()
                        .attr("value");
                return csrftoken;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csrftokenResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private JSONObject checkPassword(String password, JSONObject rsaKey) {
        String username = (String) session.getAttribute("username");
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        HttpPost checkPost = new HttpPost(CHECK_PASSWORD_URL + username);
        setHeader(checkPost);
        CloseableHttpClient client = getHttpClientWithCookies();
        List<NameValuePair> checkParams = new ArrayList<>();
        password = encryp(password, rsaKey);
        checkParams.add(new BasicNameValuePair("kl", password));
        CloseableHttpResponse checkResponse = null;
        try {
            checkPost.setEntity(new UrlEncodedFormEntity(checkParams, "utf8"));
            checkResponse = client.execute(checkPost);
            if (String.valueOf(checkResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String checkResult = EntityUtils.toString(checkResponse.getEntity(), "utf-8");
                result.put("flag", checkResult);
                if ("true".equals(checkResult)) {
                    result.put("code", SUCCESS_CODE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (checkResponse != null) {
                    checkResponse.close();
                }
            } catch (IOException e) {
                System.out.println("验证密码response关闭失败");
            }
        }
        return result;
    }

    private List<NameValuePair> queryCoursesParam() {
        List<NameValuePair> param = new ArrayList<>();
        getHttpClientWithCookies();
        HttpGet paramGet = new HttpGet(COURSES_PARAM_URL);
        setHeader(paramGet);
        CloseableHttpResponse paramResponse = null;
        try {
            paramResponse = httpClient.execute(paramGet);
            if (String.valueOf(paramResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String html = EntityUtils.toString(paramResponse.getEntity(), "utf8");
                Document document = Jsoup.parse(html);
                Elements elements = document.select("input[type=hidden]");
                List<String> key = elements.eachAttr("name");
                List<String> value = elements.eachAttr("value");
                for (int i = 11; i < key.size() - 1; i++) {
                    param.add(new BasicNameValuePair(key.get(i), value.get(i + 2)));
                }
                System.out.println();
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (paramResponse != null) {
                    paramResponse.close();
                }
            } catch (IOException e) {
                System.out.println("预选课参数response关闭失败");
            }
        }
        return param;
    }

    public JSONObject login(String username, String password) {
        JSONObject result = new JSONObject("code", FAIL_CODE);
        String timestamp = String.valueOf(System.currentTimeMillis());
        HttpPost loginHttpPost = new HttpPost(LOGIN_URL + timestamp);
        setHeader(loginHttpPost);
        List<NameValuePair> loginParams = new ArrayList<>();
        password = encryp(password, getRSAKey());
        String csrftoken = crawlCsrfToken(timestamp);
        loginParams.add(new BasicNameValuePair("csrftoken", csrftoken));
        loginParams.add(new BasicNameValuePair("language", "zh_CN"));
        loginParams.add(new BasicNameValuePair("yhm", username));
        loginParams.add(new BasicNameValuePair("mm", password));
        loginParams.add(new BasicNameValuePair("mm", password));
        CloseableHttpResponse loginResponse;
        try {
            loginHttpPost.setEntity(new UrlEncodedFormEntity(loginParams, "utf8"));
            loginResponse = httpClient.execute(loginHttpPost);
            int resultCode = loginResponse.getStatusLine().getStatusCode();
            if (resultCode == 302) {
                session.setAttribute("username", username);
                // 成功登陆
                result.put("code", SUCCESS_CODE);
            } else if (resultCode == 200) {
                // 登陆失败
                result.put("code", FAIL_CODE);
            } else { // 未知错误
                result.put("code", String.valueOf(resultCode));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Cookie> cookies = basicCookieStore.getCookies();
        for (Cookie cookie : cookies) {
            session.setAttribute(cookie.getName(), cookie.getValue());
        }
//        User user = userService.getUser(username);
//        if(user != null) {
//            result.put("flag", "1");
//        }else {
//            result.put("flag", "0");
//        }
        return result;
    }

    public JSONObject updatePassword(String oldPassword, String newPassword, String newAgain) {
        JSONObject result = new JSONObject()
                .put("code", FAIL_CODE)
                .put("flag", "false");
        if (newPassword.equals(newAgain)) {
            JSONObject rsaKey = getRSAKey();
            JSONObject flag = checkPassword(oldPassword, rsaKey);
            CloseableHttpResponse updateResponse = null;
            //if(flag.getString("code").equals(SUCCESS_CODE) && "true".equals(flag.getString("flag"))) {
            HttpPost updatePost = new HttpPost(UPDATE_PASSWORD_URL);
            setHeader(updatePost);
            List<NameValuePair> updateParams = new ArrayList<>();
            oldPassword = encryp(oldPassword, rsaKey);
            newPassword = encryp(newPassword, rsaKey);
            updateParams.add(new BasicNameValuePair("ymm", oldPassword));
            updateParams.add(new BasicNameValuePair("mm", newPassword));
            updateParams.add(new BasicNameValuePair("doType", "save"));
            try {
                updatePost.setEntity(new UrlEncodedFormEntity(updateParams, "utf8"));
                updateResponse = httpClient.execute(updatePost);
                if (String.valueOf(updateResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                    String updateResult = EntityUtils.toString(updateResponse.getEntity(), "utf-8");
                    if (updateResult.contains("成功")) {
                        result.put("flag", "true");
                        result.put("code", "200");
                    }
                }
            } catch (Exception e) {
                System.out.println("修改密码失败");
            } finally {
                try {
                    if (httpClient != null) {
                        httpClient.close();
                    }
                } catch (IOException e) {
                    System.out.println("修改密码client关闭失败");
                }
                try {
                    if (updateResponse != null) {
                        updateResponse.close();
                    }
                } catch (IOException e) {
                    System.out.println("修改密码response关闭失败");
                }
            }
        }
        return result;
    }

    public JSONObject queryClasses(String xnm, String xqm) {
        JSONObject result = new JSONObject().put("code", FAIL_CODE);
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(QUERY_CLASS_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("xnm", xnm));
        queryParams.add(new BasicNameValuePair("xqm", xqm));
        queryParams.add(new BasicNameValuePair("kzlx", "ck"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("课表格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("获取课表失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("查询课表client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("查询课表response关闭失败");
            }
        }
        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            JSONArray items = tempJson.getJSONArray("sjkList");
            JSONArray tempArray = new JSONArray();
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();
                //temp.put("khfsmc", all.getString("khfsmc"));
                temp.put("qsjsz", all.getString("qsjsz"));
                temp.put("jsxm", all.getString("jsxm"));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("sjkcgs", all.getString("sjkcgs"));
                temp.put("kclx", "实践");
                tempArray.put(temp);
            }
            result.put("sjkList", tempArray);
            items = tempJson.getJSONArray("kbList");

            tempArray = new JSONArray();
            for (int week = 0; week < 7; week++) {
                JSONArray classes = new JSONArray();
                classes.put(new JSONObject()
                        .put("jcs", "1")
                        .put("length", "12")
                        .put("kcmc", ""));
                tempArray.put(classes);
            }

            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();

                String sectionStr = all.getString("jcs");
                String dayStr = all.getString("xqj");

                int sectionStart = Integer.parseInt(sectionStr.substring(0, sectionStr.indexOf("-")));
                int sectionEnd = Integer.parseInt(sectionStr.substring(sectionStr.indexOf("-") + 1));
                int sectionLength = sectionEnd - sectionStart + 1;
                int dayIndex = Integer.parseInt(dayStr) - 1;

                JSONArray classes = tempArray.getJSONArray(dayIndex);
                JSONObject emptyClass = classes.getJSONObject(classes.length() - 1);
                int emptyStart = Integer.parseInt(emptyClass.getString("jcs"));
                int emptyLength = sectionStart - emptyStart;
                if (emptyLength == 0) {
                    classes.remove(classes.length() - 1);
                } else {
                    emptyClass.put("length", String.valueOf(emptyLength));
                }

                temp.put("khfsmc", all.getString("khfsmc"));
                temp.put("kch", all.getString("kch"));
                temp.put("cdmc", all.getString("cdmc"));
                temp.put("xqj", dayStr);
                temp.put("jcs", String.valueOf(sectionStart));
                temp.put("row", String.valueOf(sectionLength));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("xm", all.getString("xm"));
                temp.put("kclx", "理论");
                String flag = all.getString("zcd");
                if (flag.contains("单")) {
                    temp.put("flag", "1");
                } else if (flag.contains("双")) {
                    temp.put("flag", "2");
                } else {
                    temp.put("flag", "0");
                }
                tempArray.getJSONArray(dayIndex).put(temp);
            }
            result.put("kbList", tempArray);
            result.put("code", SUCCESS_CODE);
        }
        return result;
    }

    public JSONArray queryExamInfo(String xnm, String xqm) {
        JSONArray result = new JSONArray().put(new JSONObject().put("code", FAIL_CODE));
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(QUERY_EXAM_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("xnm", xnm));
        queryParams.add(new BasicNameValuePair("xqm", xqm));
        queryParams.add(new BasicNameValuePair("ksmcdmb_id", ""));
        queryParams.add(new BasicNameValuePair("kch", ""));
        queryParams.add(new BasicNameValuePair("kc", ""));
        queryParams.add(new BasicNameValuePair("ksrq", ""));
        queryParams.add(new BasicNameValuePair("kkbm_id", ""));
        queryParams.add(new BasicNameValuePair("_search", "false"));
        queryParams.add(new BasicNameValuePair("queryModel.showCount", "30"));
        queryParams.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        queryParams.add(new BasicNameValuePair("queryModel.sortName:", ""));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder:", "2"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("考试信息格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("获取考试信息失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("查询考试信息client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("查询考试信息response关闭失败");
            }
        }
        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            result = new JSONArray().put(new JSONObject().put("code", SUCCESS_CODE));
            JSONArray items = tempJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();
                //temp.put("khfsmc", all.getString("khfsmc"));
                temp.put("ksmc", all.getString("ksmc"));
                temp.put("kssj", all.getString("kssj"));
                temp.put("kch", all.getString("kch"));
                temp.put("cdmc", all.getString("cdmc"));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("xf", all.getString("xf"));
                result.put(temp);
            }
        }
        return result;
    }

    public JSONArray queryScore(String xnm, String xqm) {
        JSONArray result = new JSONArray().put(new JSONObject().put("code", FAIL_CODE));
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(QUERY_SCORE_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("xnm", xnm));
        queryParams.add(new BasicNameValuePair("xqm", xqm));
        queryParams.add(new BasicNameValuePair("_search", "false"));
        queryParams.add(new BasicNameValuePair("queryModel.showCount", "30"));
        queryParams.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        queryParams.add(new BasicNameValuePair("queryModel.sortName:", ""));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("成绩格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("获取成绩失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("查询成绩client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("查询成绩response关闭失败");
            }
        }
        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            result = new JSONArray().put(new JSONObject().put("code", SUCCESS_CODE));
            JSONArray items = tempJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();
                //temp.put("khfsmc", all.getString("khfsmc"));
                temp.put("xfjd", all.getString("xfjd"));
                temp.put("kch", all.getString("kch"));
                temp.put("tjrxm", all.getString("tjrxm"));
                temp.put("jd", all.getString("jd"));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("cj", all.getString("cj"));
                temp.put("xf", all.getString("xf"));
                result.put(temp);
            }
        }
        return result;
    }

    public JSONArray queryMessage(String flag) {
        JSONArray result = new JSONArray().put(new JSONObject().put("code", FAIL_CODE));
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(QUERY_MESSAGE_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = new ArrayList<>();
        queryParams.add(new BasicNameValuePair("sfyy", flag));
        queryParams.add(new BasicNameValuePair("flag", "1"));
        queryParams.add(new BasicNameValuePair("_search", "false"));
        queryParams.add(new BasicNameValuePair("queryModel.showCount", "30"));
        queryParams.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        queryParams.add(new BasicNameValuePair("queryModel.sortName:", "cjsj"));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder", "desc"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("消息格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("消息成绩失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("消息成绩client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("消息成绩response关闭失败");
            }
        }

        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            result = new JSONArray().put(new JSONObject().put("code", SUCCESS_CODE));
            JSONArray items = tempJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                if (all.getString("cjsj").startsWith(CURRENT_YEAR)) {
                    JSONObject temp = new JSONObject();
                    temp.put("xxbt", all.getString("xxbt"));
                    temp.put("xxbtjc", all.getString("xxbtjc"));
                    temp.put("xxnr", all.getString("xxnr"));
                    result.put(temp);
                }
            }
        }
        return result;
    }

    public JSONArray preCourses() {
        JSONArray result = new JSONArray().put(new JSONObject().put("code", FAIL_CODE));
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(PRE_COURSES_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = queryCoursesParam();
        queryParams.add(new BasicNameValuePair("kkbm_id", ""));
        queryParams.add(new BasicNameValuePair("kclbdm", ""));
        queryParams.add(new BasicNameValuePair("kcdmmc", ""));
        queryParams.add(new BasicNameValuePair("jsghxm", ""));
        queryParams.add(new BasicNameValuePair("doType", "query"));
        queryParams.add(new BasicNameValuePair("_search", "false"));
        queryParams.add(new BasicNameValuePair("queryModel.showCount", "20"));
        queryParams.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        queryParams.add(new BasicNameValuePair("queryModel.sortName", "kch"));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        queryParams.add(new BasicNameValuePair("time", "0"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("预选课格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("预选课请求失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("预选课client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("预选课response关闭失败");
            }
        }

        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            result = new JSONArray().put(new JSONObject().put("code", SUCCESS_CODE));
            JSONArray items = tempJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();
                temp.put("kclbmc", all.getString("kclbmc"));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("kcyxb_id", all.getString("kcyxb_id"));
                temp.put("xf", all.getString("xf"));
                temp.put("xsxxxx", all.getString("xsxxxx"));
                temp.put("yxrs", all.getString("yxrs"));
                temp.put("zxs", all.getString("zxs"));
                result.put(temp);
            }
        }
        return result;
    }

    public JSONArray selectedCourses() {
        JSONArray result = new JSONArray().put(new JSONObject().put("code", FAIL_CODE));
        JSONObject tempJson = new JSONObject().put("code", FAIL_CODE);
        CloseableHttpClient client = getHttpClientWithCookies();
        HttpPost queryPost = new HttpPost(SELECTED_COURSES_URL);
        setHeader(queryPost);
        List<NameValuePair> queryParams = new ArrayList<>();
        ;
        queryParams.add(new BasicNameValuePair("_search", "false"));
        queryParams.add(new BasicNameValuePair("queryModel.showCount", "20"));
        queryParams.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        queryParams.add(new BasicNameValuePair("queryModel.sortName", ""));
        queryParams.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        queryParams.add(new BasicNameValuePair("time", "0"));
        CloseableHttpResponse queryResponse = null;
        try {
            queryPost.setEntity(new UrlEncodedFormEntity(queryParams, "utf8"));
            queryResponse = client.execute(queryPost);
            if (String.valueOf(queryResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(queryResponse.getEntity(), "utf-8");
                try {
                    tempJson = new JSONObject(emJson);
                    tempJson.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("预选课格式化失败");
                }
            }
        } catch (Exception e) {
            System.out.println("预选课请求失败");
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                System.out.println("预选课client关闭失败");
            }
            try {
                if (queryResponse != null) {
                    queryResponse.close();
                }
            } catch (IOException e) {
                System.out.println("预选课response关闭失败");
            }
        }

        if (tempJson.getString("code").equals(SUCCESS_CODE)) {
            result = new JSONArray().put(new JSONObject().put("code", SUCCESS_CODE));
            JSONArray items = tempJson.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject all = items.getJSONObject(i);
                JSONObject temp = new JSONObject();
                temp.put("kclbmc", all.getString("kclbmc"));
                temp.put("kcmc", all.getString("kcmc"));
                temp.put("xf", all.getString("xf"));
                temp.put("kcyxb_id", all.getString("kcyxb_id"));
                result.put(temp);
            }
        }
        return result;
    }

    public JSONObject selectCourses(String kcyxb_id) {
        JSONObject result = new JSONObject("code", FAIL_CODE);

        HttpPost selectPost = new HttpPost(SELECT_COURSES_URL);
        setHeader(selectPost);
        List<NameValuePair> selectParam = new ArrayList<>();
        getHttpClientWithCookies();
        selectParam.add(new BasicNameValuePair("kcyxb_id", kcyxb_id));
        selectParam.add(new BasicNameValuePair("xh_id", (String) session.getAttribute("username")));

        CloseableHttpResponse selectResponse;
        try {
            selectPost.setEntity(new UrlEncodedFormEntity(selectParam, "utf8"));
            selectResponse = httpClient.execute(selectPost);
            if (String.valueOf(selectResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(selectResponse.getEntity(), "utf-8");
                try {
                    result = new JSONObject(emJson);
                    result.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("选课信息失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public JSONObject unselectCourses(String xsyxb_id) {
        JSONObject result = new JSONObject("code", FAIL_CODE);
        HttpPost unselectPost = new HttpPost(UNSELECT_COURSES_URL);
        setHeader(unselectPost);
        List<NameValuePair> unselectParam = new ArrayList<>();
        getHttpClientWithCookies();
        unselectParam.add(new BasicNameValuePair("xsyxb_id", xsyxb_id));
        unselectParam.add(new BasicNameValuePair("xh_id", (String) session.getAttribute("username")));
        CloseableHttpResponse unselectResponse;
        try {
            unselectPost.setEntity(new UrlEncodedFormEntity(unselectParam, "utf8"));
            unselectResponse = httpClient.execute(unselectPost);
            if (String.valueOf(unselectResponse.getStatusLine().getStatusCode()).equals(SUCCESS_CODE)) {
                String emJson = EntityUtils.toString(unselectResponse.getEntity(), "utf-8");
                try {
                    result = new JSONObject(emJson);
                    result.put("code", SUCCESS_CODE);
                } catch (JSONException e) {
                    System.out.println("选课信息失败");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
