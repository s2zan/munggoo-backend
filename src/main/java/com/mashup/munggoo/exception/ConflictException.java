package com.mashup.munggoo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ConflictException extends BaseException {
    public ConflictException() {
        this(HttpStatus.CONFLICT.getReasonPhrase());
    }

    public ConflictException(String msg) {
        this(HttpStatus.CONFLICT.value(), msg);
    }

    public ConflictException(Integer code, String msg)  {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
