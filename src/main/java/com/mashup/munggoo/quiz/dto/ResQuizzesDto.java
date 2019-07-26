package com.mashup.munggoo.quiz.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResQuizzesDto {
    List<ResQuizDto> quizzes;

    public ResQuizzesDto(List<ResQuizDto> quizzes){
        this.quizzes = quizzes;
    }
}
