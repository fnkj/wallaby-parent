package com.shaobing.wallaby.common.http.utils;

import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.protocol.HttpContext;

/**
 * cookies配置
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-12 1:15
 */
public class HttpCookiesConfig {

    private HttpClientContext httpClientContext;


    public HttpContext build() {
        return new HttpClientContext();
    }


}
