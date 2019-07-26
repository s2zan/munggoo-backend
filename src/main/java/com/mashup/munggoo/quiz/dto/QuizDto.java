package com.mashup.munggoo.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {

    private Long fileId;

    private Long startIndex;

    private Long endIndex;

    private String content;
}
