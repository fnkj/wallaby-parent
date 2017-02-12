package com.shaobing.wallaby.common.http.exception;

/**
 * 请求头异常
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 9:01
 */
public class HttpHeaderException extends RuntimeException{

    public HttpHeaderException() {
        super();
    }

    public HttpHeaderException(String message) {
        super(message);
    }

    public HttpHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpHeaderException(Throwable cause) {
        super(cause);
    }
}
