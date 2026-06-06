package com.ChrisXin.exception;

public class IsEmptyException extends RuntimeException{
    public IsEmptyException() {
        super();
    }
    public IsEmptyException(String message) {
        super(message);
    }
    public IsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
    public IsEmptyException(Throwable cause) {
        super(cause);
    }
}
