package com.xo.exception;

/**
 * 给用户看的异常信息
 */
public class MessageException extends RuntimeException {
    public MessageException(String message) {
        super(message);
    }
}
