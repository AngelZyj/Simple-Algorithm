package com.tsintergy.simple.algorithm.core.exception;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/5/31 09:33
 */
public class ProcessException extends Exception {

    public ProcessException() {
    }

    public ProcessException(String message) {
        super(message);
    }

    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcessException(Throwable cause) {
        super(cause);
    }

    public ProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
