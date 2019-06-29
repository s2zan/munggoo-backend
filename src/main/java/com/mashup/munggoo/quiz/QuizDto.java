package com.mashup.munggoo.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuizDto {
    private Long fileId;
    private Long startIndex;
    private Long endIndex;
    private String content;
}
