package com.shaobing.wallaby.core.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * CSRF测试POC
 *
 * @author luyb@servyou.com.cn
 * @version $$: TestCsrf.java v 0.1 2016/11/29 13:23 luyb Exp $$
 */
public class TestCsrf {

    private List<Cookie> getCookies() {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            CookieStore cookieStore = new BasicCookieStore();
            HttpClientContext httpContext = HttpClientContext.create();
            httpContext.setCookieStore(cookieStore);

            HttpPost httpPost = new HttpPost(
                "http://218.13.4.18:9080/xqy-portal-web/manage/login/login");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("userName", "13204020965"));
            nvps.add(new BasicNameValuePair("password", "5690dddfa28ae085d23518a035707282"));
            nvps.add(new BasicNameValuePair("rememberMe", "true"));

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println("Executing request " + httpPost.getRequestLine());

            httpclient.start();
            Future<HttpResponse> future = httpclient.execute(httpPost, httpContext, null);
            HttpResponse response = future.get();
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String body = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(body);
            }

            System.out.println("Response: " + response.getStatusLine());
            return cookieStore.getCookies();

        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpclient != null)
                    httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Test
    public void testQueryCustomer() {
        //第一次登陆时获取
        final List<Cookie> cookies = getCookies();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            CookieStore cookieStore = new BasicCookieStore();
            String csrfToken = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookieStore.addCookie(cookie);
                    if ("xsrf-token".equals(cookie.getName())) {
                        csrfToken = cookie.getValue();
                    }
                }
            }
            HttpClientContext httpContext = HttpClientContext.create();
            httpContext.setCookieStore(cookieStore);

            HttpPost httpPost = new HttpPost(
                    "http://218.13.4.18:9080/xqy-portal-web/manage/customer/queryCustomers");

            //伪装成AJAX请求
            httpPost.setHeader("xsrf-token", csrfToken);
            httpPost.setHeader("X-Requested-With", "XMLHttpRequest");

            //构造reqBody
            HashMap<String, String> reqParams = new HashMap<>();
            reqParams.put("accountId", "31");
            reqParams.put("searchType", "OWN");
            reqParams.put("pageSize", "25");

            StringEntity entity = new StringEntity(JSON.toJSONString(reqParams), "UTF-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            System.out.println("Executing request " + httpPost.getRequestLine());

            httpclient.start();
            Future<HttpResponse> future = httpclient.execute(httpPost, httpContext, null);
            HttpResponse response = future.get();
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String body = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(body);
            }
            System.out.println("Response: " + response.getStatusLine());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpclient != null)
                    httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testPutSession() {
        //第一次登陆时获取
        final List<Cookie> cookies = getCookies();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            CookieStore cookieStore = new BasicCookieStore();
            String csrfToken = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookieStore.addCookie(cookie);
                    if ("xsrf-token".equals(cookie.getName())) {
                        csrfToken = cookie.getValue();
                    }
                }
            }


            HttpClientContext httpContext = HttpClientContext.create();
            httpContext.setCookieStore(cookieStore);

            HttpPut httpPut = new HttpPut(
                    "http://218.13.4.18:9080/xqy-portal-web/finance/account/session/accountSet");

            //httpGet.setHeader("Cookie", sb.toString());
            //伪装成AJAX请求
            httpPut.setHeader("xsrf-token", csrfToken);
            httpPut.setHeader("X-Requested-With", "XMLHttpRequest");

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("accountSetId", "191"));
            nvps.add(new BasicNameValuePair("customerName", "1924"));
            nvps.add(new BasicNameValuePair("customerShortName", "1924"));
            nvps.add(new BasicNameValuePair("customerId", "2429"));
            try {
                httpPut.setEntity(new UrlEncodedFormEntity(nvps));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            System.out.println("Executing request " + httpPut.getRequestLine());

            httpclient.start();
            Future<HttpResponse> future = httpclient.execute(httpPut, httpContext, null);
            HttpResponse response = future.get();
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String body = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(body);
            }
            System.out.println("Response: " + response.getStatusLine());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpclient != null)
                    httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testListCustomer() {
        //第一次登陆时获取
        final List<Cookie> cookies = getCookies();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            CookieStore cookieStore = new BasicCookieStore();
            String csrfToken = "";
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookieStore.addCookie(cookie);
                    if ("xsrf-token".equals(cookie.getName())) {
                        csrfToken = cookie.getValue();
                    }
                }
            }
            HttpClientContext httpContext = HttpClientContext.create();
            httpContext.setCookieStore(cookieStore);

            HttpGet httpGet = new HttpGet(
                    "http://127.0.0.1:8082/xqy-portal-web/tax/import/customer/list/31");

            //伪装成AJAX请求
            httpGet.setHeader("xsrf-token", csrfToken);
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");

            System.out.println("Executing request " + httpGet.getRequestLine());

            httpclient.start();
            Future<HttpResponse> future = httpclient.execute(httpGet, httpContext, null);
            HttpResponse response = future.get();
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String body = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(body);
            }
            System.out.println("Response: " + response.getStatusLine());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (httpclient != null)
                    httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
