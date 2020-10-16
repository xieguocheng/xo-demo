package com.system.exception;

/**
 * 给前端开发人员看的异常信息
 */
public class FrontMessageException extends RuntimeException {
    public FrontMessageException(String message) {
        super(message);
    }
}
