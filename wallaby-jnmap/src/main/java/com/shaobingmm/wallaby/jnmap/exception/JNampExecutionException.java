package com.shaobingmm.wallaby.jnmap.exception;

/**
 * JNmap执行异常
 *
 * @author luyb@servyou.com.cn
 * @version $Id: JNampExecutionException.java v 0.1 2017/1/15 21:49 luyb Exp $$
 */
public class JNampExecutionException extends RuntimeException {

    public JNampExecutionException() {
        super();
    }

    public JNampExecutionException(String message) {
        super(message);
    }

    public JNampExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JNampExecutionException(Throwable cause) {
        super(cause);
    }
}
