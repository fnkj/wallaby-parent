package com.shaobing.wallaby.core.http;

/**
 * 异步
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-12 1:15
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.util.List;
import java.util.concurrent.Future;

/**
 * This example demonstrates the use of a local HTTP context populated with
 * custom attributes.
 */
public class AsyncClientCustomContext {

    public final static void main(String[] args) throws Exception {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            // Create a local instance of cookie store
            CookieStore cookieStore = new BasicCookieStore();

            // Create local HTTP context
            HttpClientContext localContext = HttpClientContext.create();
            // Bind custom cookie store to the local context
            localContext.setCookieStore(cookieStore);

            //HttpGet httpget = new HttpGet("http://www.deerkid.com/login.jhtml");
            HttpGet httpget = new HttpGet("https://t.vipkid.com.cn/login");
            System.out.println("Executing request " + httpget.getRequestLine());

            httpclient.start();

            // Pass local context as a parameter
            Future<HttpResponse> future = httpclient.execute(httpget, localContext, null);

            // Please note that it may be unsafe to access HttpContext instance
            // while the request is still being executed

            HttpResponse response = future.get();

            /**
             * HttpEntity其实相当于一个消息实体，内容是http传送的报文（这里可以说是html，css等等文件）。
             * 这里只需要知道它是用来表征一个http报文的实体就行了，用来发送或接收
            * */
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                String body = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(body);
            }

            System.out.println("Response: " + response.getStatusLine());
            List<Cookie> cookies = cookieStore.getCookies();
            for (int i = 0; i < cookies.size(); i++) {
                System.out.println("Local cookie: " + cookies.get(i));
            }
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
    }

}
