package com.shaobing.wallaby.common.http.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.*;

import com.shaobing.wallaby.common.http.utils.HttpMethods;


/**
 * @author luyb@servyou.com.cn
 * @version $Id: ClientUtilBase.java v 0.1 2016/12/31 23:38 luyb Exp $$
 */
public abstract class ClientUtilBase {

    /**
     * 创建http请求方法
     *
     * @param url         请求地址
     * @param httpMethods 请求方法类型
     * @return http请求方法对象
     */
    protected static HttpRequestBase createHttpMethod(String url, HttpMethods httpMethods) {
        if (StringUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url地址不能为空!");
        }

        if (httpMethods == null)
            throw new NullPointerException("请求类型httpMethods不能为空!");

        HttpRequestBase httpRequestBase;
        switch (httpMethods) {
            case GET:
                httpRequestBase = new HttpGet(url);
            case POST:
                httpRequestBase = new HttpPost(url);
            case PUT:
                httpRequestBase = new HttpPut(url);
            case DELETE:
                httpRequestBase = new HttpDelete(url);
            case HEAD:
                httpRequestBase = new HttpHead(url);
            case OPTIONS:
                httpRequestBase = new HttpOptions(url);
            case TRACE:
                httpRequestBase = new HttpTrace(url);
            case PATCH:
                httpRequestBase = new HttpPatch(url);
            default:
                httpRequestBase = new HttpGet(url);

        }
        return httpRequestBase;
    }

}
