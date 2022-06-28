package com.td.smartschool.customer.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author K8lyN
 * Http 发送请求
 */


public class HttpClientUtil {


    /**
     *
     */
    public static JSONObject requestByPostMethod(String url, JSONObject param, JSONObject headers) {
        JSONObject result = new JSONObject();
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            Iterator iterator = param.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                params.add(new BasicNameValuePair(key, param.getString(key)));
            }
            uri = new URIBuilder(url).setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        //System.out.println(uri);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        Iterator keys = headers.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            httpPost.setHeader(key, headers.getString(key));
        }
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            result.put("status", "" + response.getStatusLine().getStatusCode());
            if (responseEntity != null) {
                String relResponse = EntityUtils.toString(responseEntity);
                try {
                    result.put("result", new JSONObject(relResponse));
                } catch (JSONException e) {
                    result.put("result", relResponse);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 模拟发送url Get 请求
     *
     * @param url
     * @return
     */
    public static JSONObject requestByGetMethod(String url, JSONObject headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringBuilder entityStringBuilder = null;
        try {
            HttpGet get = new HttpGet(url);
            Iterator keys = headers.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                get.setHeader(key, headers.getString(key));
            }
            CloseableHttpResponse httpResponse = null;
            httpResponse = httpClient.execute(get);
            try {
                HttpEntity entity = httpResponse.getEntity();
                entityStringBuilder = new StringBuilder();
                if (null != entity) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"), 8 * 1024);
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JSONObject result = new JSONObject(entityStringBuilder.toString());
        result.put("status", "200");
        return result;
    }

}
