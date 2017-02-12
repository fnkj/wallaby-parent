package com.shaobing.wallaby.core.http;

import org.junit.Assert;
import org.junit.Test;

import com.shaobing.wallaby.common.http.httpclient.HttpClientUtil;
import com.shaobing.wallaby.common.http.utils.HttpConfigContext;
import com.shaobing.wallaby.common.http.utils.HttpHeaderConfig;
import com.shaobing.wallaby.common.http.utils.ResponseWrapper;


/**
 * @author luyb@servyou.com.cn
 * @version $Id: TestHttpsClient.java v 0.1 2017/1/29 23:12 luyb Exp $$
 */
public class TestHttpsClient {

    @Test
    public void testHttps() {
        String url = "https://www.taobao.com";
        HttpConfigContext configContext = HttpConfigContext.create().url(url)
                .headers(HttpHeaderConfig.DEFAULT_HTTP_HEADERS);
        ResponseWrapper responseWrapper = HttpClientUtil.get(configContext);
        Assert.assertTrue(responseWrapper != null);
    }
}
