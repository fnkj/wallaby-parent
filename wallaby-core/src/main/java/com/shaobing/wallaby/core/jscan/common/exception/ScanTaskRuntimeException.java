package com.shaobing.wallaby.core.jscan.common.exception;

/**
 * 运行任务异常
 *
 * @author luyb@servyou.com.cn
 * @version $$: ScanTaskRuntimeException.java v 0.1 2016/12/1 12:37 luyb Exp $$
 */
public class ScanTaskRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -1946050788445324801L;

    public ScanTaskRuntimeException() {
        super();
    }

    public ScanTaskRuntimeException(String message) {
        super(message);
    }

    public ScanTaskRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScanTaskRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ScanTaskRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
