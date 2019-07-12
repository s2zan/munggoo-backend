package com.mashup.munggoo.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResQuizDto {
    private Long startIndex;
    private Long endIndex;

    public ResQuizDto(Quiz quiz){
        startIndex = quiz.getStartIndex();
        endIndex = quiz.getEndIndex();
    }
}
