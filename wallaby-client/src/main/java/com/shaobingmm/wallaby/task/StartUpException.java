package com.shaobingmm.wallaby.task;

/**
 * 启动异常
 *
 * @author luyb@servyou.com.cn
 * @version $Id: StartUpException.java v 0.1 2017/1/9 20:57 luyb Exp $$
 */
public class StartUpException extends RuntimeException {

    public StartUpException() {
        super();
    }

    public StartUpException(String message) {
        super(message);
    }

    public StartUpException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartUpException(Throwable cause) {
        super(cause);
    }

    protected StartUpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
