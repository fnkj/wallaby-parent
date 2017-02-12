package com.shaobing.wallaby.core.http;

/**
 * AsyncClientHttpExchangeFutureCallback
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-22 22:27
 */

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * This example demonstrates a fully asynchronous execution of multiple HTTP exchanges
 * where the result of an individual operation is reported using a callback interface.
 */
public class AsyncClientHttpExchangeFutureCallback {

    public static void main(final String[] args) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000)
            .setConnectTimeout(3000).build();
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
            .setDefaultRequestConfig(requestConfig).build();
        try {
            httpclient.start();
            final HttpGet[] requests = new HttpGet[] { new HttpGet("http://www.zjut.edu.cn")};
            final CountDownLatch latch = new CountDownLatch(requests.length);
            for (final HttpGet request : requests) {
                httpclient.execute(request, new FutureCallback<HttpResponse>() {

                    @Override
                    public void completed(final HttpResponse response) {
                        latch.countDown();

                        HttpEntity httpEntity = response.getEntity();
                        if (httpEntity != null) {
                            String body = null;
                            try {
                                body = EntityUtils.toString(httpEntity, "UTF-8");
                                System.out.println(body);
                            } catch (IOException e) {
                            }
                        }
                    }

                    public void failed(final Exception ex) {
                        latch.countDown();
                    }

                    public void cancelled() {
                        latch.countDown();
                    }

                });
            }
            latch.await();
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }

}