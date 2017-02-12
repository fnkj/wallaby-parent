package com.shaobing.wallaby.core.http;


import com.shaobing.wallaby.common.http.exception.HttpHeaderException;
import com.shaobing.wallaby.common.http.exception.WallabyHttpException;
import com.shaobing.wallaby.common.http.utils.HeaderConstants;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.WallabyHttpHeader;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;
import org.apache.http.protocol.HttpContext;
import org.junit.Test;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步httpclient测试
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 16:12
 */
public class TestHttpAsyncClient {

    @Test
    public void testBasic() throws ExecutionException, InterruptedException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            // Start the client
            httpclient.start();

            // Execute request
            final HttpGet request1 = new HttpGet("http://www.apache.org/");
            Future<HttpResponse> future = httpclient.execute(request1, null);
            // and wait until a response is received
            HttpResponse response1 = future.get();
            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());

            // One most likely would want to use a callback for operation result
            final CountDownLatch latch1 = new CountDownLatch(1);
            final HttpGet request2 = new HttpGet("http://www.apache.org/");
            httpclient.execute(request2, new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response2) {
                    latch1.countDown();
                    System.out
                        .println(request2.getRequestLine() + "->" + response2.getStatusLine());
                }

                public void failed(final Exception ex) {
                    latch1.countDown();
                    System.out.println(request2.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    latch1.countDown();
                    System.out.println(request2.getRequestLine() + " cancelled");
                }

            });
            latch1.await();

            // In real world one most likely would also want to stream
            // request and response body content
            final CountDownLatch latch2 = new CountDownLatch(1);
            final HttpGet request3 = new HttpGet("http://www.apache.org/");
            HttpAsyncRequestProducer producer3 = HttpAsyncMethods.create(request3);
            AsyncCharConsumer<HttpResponse> consumer3 = new AsyncCharConsumer<HttpResponse>() {

                HttpResponse response;

                @Override
                protected void onResponseReceived(final HttpResponse response) {
                    this.response = response;
                }

                @Override
                protected void onCharReceived(final CharBuffer buf,
                                              final IOControl ioctrl) throws IOException {
                    // Do something useful
                }

                @Override
                protected void releaseResources() {
                }

                @Override
                protected HttpResponse buildResult(final HttpContext context) {
                    return this.response;
                }

            };
            httpclient.execute(producer3, consumer3, new FutureCallback<HttpResponse>() {

                public void completed(final HttpResponse response3) {
                    latch2.countDown();
                    System.out
                        .println(request2.getRequestLine() + "->" + response3.getStatusLine());
                }

                public void failed(final Exception ex) {
                    latch2.countDown();
                    System.out.println(request2.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    latch2.countDown();
                    System.out.println(request2.getRequestLine() + " cancelled");
                }

            });
            latch2.await();

        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCustom() {
        final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000)
            .setConnectionRequestTimeout(1000).setSocketTimeout(2000).build();
        final IOReactorConfig config = IOReactorConfig.DEFAULT;
        String url = "https://hc.apache.org/httpcomponents-asyncclient-dev/examples.html";
        final HttpGet httpGet = new HttpGet(url);

        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader(HeaderConstants.ACCEPT,
            "Accept text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"));
        headerList
            .add(new BasicHeader(HeaderConstants.ACCEPT_CHARSET, "GB2312,utf-8;q=0.7,*;q=0.7"));
        headerList.add(new BasicHeader(HeaderConstants.ACCEPT_ENCODING, "gzip, deflate"));
        headerList.add(new BasicHeader(HeaderConstants.CONNECTION, "keep-alive"));
        headerList
            .add(new BasicHeader(HeaderConstants.USER_AGENT, HeaderConstants.DEFAULT_USER_AGENT));

        httpGet.setHeaders(headerList.toArray(new Header[0]));
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientBuilder.create()
            .setDefaultRequestConfig(requestConfig).setDefaultIOReactorConfig(config).build();
    }

    @Test
    public void wallabyHttpAsync() throws HttpHeaderException, WallabyHttpException, IOException,
                                   InterruptedException {
        WallabyHttpHeader wallabyHttpHeader = new WallabyHttpHeader();
        wallabyHttpHeader.setToken(UUID.randomUUID().toString());

        HttpHeaderConfig headerConfig = HttpHeaderConfig.create();
        Header[] headers = headerConfig.customHeader(wallabyHttpHeader)
            .contentType("text/html; charset=UTF-8")
            .userAgent(
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36")
            .build();
        CloseableHttpAsyncClient httpAsyncClient = null;
        String url = "http://www.zjut.edu.cn";
        final CountDownLatch countDownLatch = new CountDownLatch(1);
/*        HttpAsyncClientUtil.execute(httpAsyncClient, url, headers, null, HttpMethods.GET,
            new IAsyncHandler() {
                @Override
                public void completed(HttpResponse response) {
                    countDownLatch.countDown();
                }

                @Override
                public void failed(Exception ex) {
                    System.out.println(ex.toString());
                    countDownLatch.countDown();
                }

                @Override
                public void cancelled() {
                    countDownLatch.countDown();
                }
            });*/
        countDownLatch.await();
        httpAsyncClient.close();

    }
}
