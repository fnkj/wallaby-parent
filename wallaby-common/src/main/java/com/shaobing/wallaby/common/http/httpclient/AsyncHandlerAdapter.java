package com.shaobing.wallaby.common.http.httpclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shaobing.wallaby.common.logger.LoggerUtils;

/**
 * 回调接口适配器
 *
 * @author luyb@servyou.com.cn
 * @version $$: AsyncHandlerAdapter.java v 0.1 2016/11/29 0:23 luyb Exp $$
 */
public abstract class AsyncHandlerAdapter implements  IAsyncHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncHandlerAdapter.class);

    @Override
    public void failed(Exception ex) {
        LoggerUtils.error(LOGGER, ex, ex.getMessage());
    }

    @Override
    public void cancelled() {
        LoggerUtils.warn(LOGGER, "取消改请求");
    }
}
