package com.shaobing.wallaby.common.http.exception;

/**
 * HTTP处理异常
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-10 15:16
 */
public class WallabyHttpException extends RuntimeException {

    public WallabyHttpException() {
        super();
    }

    public WallabyHttpException(String message) {
        super(message);
    }

    public WallabyHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public WallabyHttpException(Throwable cause) {
        super(cause);
    }
}
