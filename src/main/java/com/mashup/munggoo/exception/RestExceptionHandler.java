package com.mashup.munggoo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorModel> handledExceptionHandler(BaseException baseException) throws RuntimeException {
        ErrorModel errorModel = baseException.errorModel;
        log.error("Rest api error: {}", errorModel.getMsg());
        switch (errorModel.getCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
            case 404:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
            case 409:
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errorModel);
            default:
                throw new RuntimeException();
        }
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> unhandledExceptionHandler(RuntimeException exception) {
        log.error("unhandledException occur : {}", exception.getMessage());
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorModel.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .msg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .build());
    }
}
