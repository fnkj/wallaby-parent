package com.shaobingmm.wallaby.crawler.exception;

/**
 * @author luyb@servyou.com.cn
 * @version $Id: FetcherTaskExceptino.java v 0.1 2017/1/30 0:14 luyb Exp $$
 */
public class FetcherTaskException extends RuntimeException {

    public FetcherTaskException() {
        super();
    }

    public FetcherTaskException(String message) {
        super(message);
    }

    public FetcherTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public FetcherTaskException(Throwable cause) {
        super(cause);
    }
}
