package com.shaobing.wallaby.core.http.j2ee;

import com.shaobing.wallaby.common.http.httpclient.HttpAsyncClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.UserAgents;
import com.shaobing.wallaby.core.jscan.common.PreambleInfo;
import com.shaobing.wallaby.core.jscan.common.ScanIssue;
import com.shaobing.wallaby.core.jscan.tomcat.TomcatWeakPasswordScanTask;
import com.shaobing.wallaby.core.jscan.tomcat.WeakPasswordAsyncHandler;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.http.Header;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.Test;

import java.util.List;

/**
 * @author luyb@servyou.com.cn
 * @version $$: TestTomcat.java v 0.1 2016/11/27 23:05 luyb Exp $$
 */
public class TestTomcat {

    @Test
    public void testManagerHtml() throws InterruptedException {
        String url = "http://127.0.0.1:8080/manager/html";
        Header[] headers = HttpHeaderConfig.create().userAgent(UserAgents.CHROME_USERAGENTS)
                .connection("keep-alive").pragma("no-cache").build();
        BasicCookieStore cookieStore = new BasicCookieStore();
        HttpClientContext httpClientContext = new HttpClientContext();
        httpClientContext.setCookieStore(cookieStore);
        HttpConfigContext configContext = HttpConfigContext.create().url(url).headers(headers)
                .httpContext(httpClientContext).asyncHandler(new WeakPasswordAsyncHandler());
        HttpAsyncClientUtil.get(configContext);
        while (true) {
            Thread.sleep(10000);
        }
    }


    @Test
    public void testTomcatSync() {
        TomcatWeakPasswordScanTask tomcatWeakPasswordScanTask = new TomcatWeakPasswordScanTask();
        String url = "http://127.0.0.1:8080/manager/html";
        PreambleInfo preambleInfo = new PreambleInfo();
        List<ScanIssue> issues = tomcatWeakPasswordScanTask.execute(url, preambleInfo, null);

        System.out.println(
                ToStringBuilder.reflectionToString(issues, ToStringStyle.SHORT_PREFIX_STYLE));

    }
}
