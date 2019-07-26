package com.mashup.munggoo.quiz.quizgenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Word {

    private Long fileId;

    private Long startIndex;

    private Long endIndex;

    private String content;
}
