package com.mashup.munggoo.quiz;

import lombok.Getter;

@Getter
public class ResQuizDto {
    private Long startIndex;
    private Long endIndex;

    public ResQuizDto(Quiz quiz){
        startIndex = quiz.getStartIndex();
        endIndex = quiz.getEndIndex();
    }
}
