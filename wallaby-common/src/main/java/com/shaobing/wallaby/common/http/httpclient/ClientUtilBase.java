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

        HttpRequestBase httpRequest;
        switch (httpMethods) {
            case GET:
                httpRequest = new HttpGet(url);
            case POST:
                httpRequest = new HttpPost(url);
            case PUT:
                httpRequest = new HttpPut(url);
            case DELETE:
                httpRequest = new HttpDelete(url);
            case HEAD:
                httpRequest = new HttpHead(url);
            case OPTIONS:
                httpRequest = new HttpOptions(url);
            case TRACE:
                httpRequest = new HttpTrace(url);
            case PATCH:
                httpRequest = new HttpPatch(url);
            default:
                httpRequest = new HttpGet(url);

        }
        return httpRequest;
    }

}
