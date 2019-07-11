package com.mashup.munggoo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class NotFoundException extends BaseException {
    public NotFoundException(String msg) {
        this(HttpStatus.NOT_FOUND.value(), msg);
    }

    public NotFoundException(Integer code, String msg) {
        super(ErrorModel.builder()
                .code(code)
                .msg(msg)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
