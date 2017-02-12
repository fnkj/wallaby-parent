package com.shaobing.wallaby.common.http.httpclient;

import org.apache.http.HttpResponse;

/**
 * FutureTask异步回调接口
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 21:38
 */
public interface IAsyncHandler {

    /**
     * 处理正常时，执行该方法
     */
    void completed(String url, HttpResponse response);

    /**
     * 处理异常时，执行该方法
     */
    void failed(Exception ex);

    /**
     * 处理取消时，执行该方法
     */
    void cancelled();

}
