package com.mashup.munggoo.quiz.dto;

import com.mashup.munggoo.quiz.domain.Quiz;
import lombok.Getter;

@Getter
public class ResQuiz {
    private Long startIndex;
    private Long endIndex;

    public ResQuiz(Quiz quiz){
        startIndex = quiz.getStartIndex();
        endIndex = quiz.getEndIndex();
    }
}
