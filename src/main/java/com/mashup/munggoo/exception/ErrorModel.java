package com.mashup.munggoo.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorModel {
    private Integer code;
    private String msg;
    private LocalDateTime timestamp;
}
