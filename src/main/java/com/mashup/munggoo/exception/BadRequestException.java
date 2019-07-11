package com.mashup.munggoo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class BadRequestException extends BaseException {
    public BadRequestException(String msg) {
        this(HttpStatus.BAD_REQUEST.value(), msg);
    }

    public BadRequestException(Integer code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
