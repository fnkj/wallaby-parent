package com.shaobing.wallaby.common.http.utils;


import com.shaobing.wallaby.common.http.exception.HttpHeaderException;

/**
 * 自定义请求头header
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 9:00
 */
public interface CustomHttpHeader {
    void checkField() throws HttpHeaderException;
}
