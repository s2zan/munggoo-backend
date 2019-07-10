package com.mashup.munggoo.exception;

public class BaseException extends RuntimeException {
    protected ErrorModel errorModel;

    protected BaseException(ErrorModel errorModel) {
        super(errorModel.getMsg(), null);
        this.errorModel = errorModel;
    }
}
