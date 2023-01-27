package com.ssafy.youandi.exception;

public class DuplicationUserNameException extends RuntimeException{
    public DuplicationUserNameException() {
    }

    public DuplicationUserNameException(String message) {
        super(message);
    }

    public DuplicationUserNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicationUserNameException(Throwable cause) {
        super(cause);
    }

    public DuplicationUserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
