package com.example.server1.exceprions;

import java.io.Serializable;

public class AbstractException extends RuntimeException implements Serializable {

    private ExceptionInfo exceptionInfo;

    public AbstractException(String message, String details) {
        exceptionInfo = new ExceptionInfo(message, details);
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

}
