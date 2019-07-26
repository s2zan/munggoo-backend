package com.mashup.munggoo.quiz.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResQuizzesDto {
    List<ResQuiz> quizzes;

    public ResQuizzesDto(List<ResQuiz> quizzes){
        this.quizzes = quizzes;
    }
}
