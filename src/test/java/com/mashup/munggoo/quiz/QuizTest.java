package com.mashup.munggoo.quiz;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizTest {
    private Quiz quiz;
    private QuizDto quizDto;
    private ResQuizDto resQuizDto;

    @Before
    public void setUp(){
        quizDto = new QuizDto(1L, 1L, 1L, "content");
        quiz = Quiz.from(quizDto);
        resQuizDto = new ResQuizDto(quiz);
    }

    @Test
    public void constructQuiz() {
        assertThat(quiz.getContent()).isEqualTo("content");
    }

    @Test
    public void constructResQuizDto() {
        assertThat(resQuizDto.getEndIndex()).isEqualTo(1);
    }
}
