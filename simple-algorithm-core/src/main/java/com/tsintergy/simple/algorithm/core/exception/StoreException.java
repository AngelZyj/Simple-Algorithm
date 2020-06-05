package com.tsintergy.simple.algorithm.core.exception;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/5/31 09:33
 */
public class StoreException extends Exception {

    public StoreException() {
    }

    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreException(Throwable cause) {
        super(cause);
    }

    public StoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
