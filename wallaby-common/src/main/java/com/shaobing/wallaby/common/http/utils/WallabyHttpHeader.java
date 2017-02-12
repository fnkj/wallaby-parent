package com.shaobing.wallaby.common.http.utils;

import com.shaobing.wallaby.common.annotation.CNotNull;
import com.shaobing.wallaby.common.http.exception.HttpHeaderException;

/**
 * Wallaby自定义请求头
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 9:12
 */
public class WallabyHttpHeader implements CustomHttpHeader {

    @CNotNull
    private String token;

    @Override
    public void checkField() throws HttpHeaderException {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("token").append(" : ").append(token);
        return builder.toString();
    }
}
