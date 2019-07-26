package com.mashup.munggoo.quiz;

import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.quizgenerator.Word;
import com.mashup.munggoo.quiz.dto.ResQuizDto;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizTest {
    private Quiz quiz;
    private Word word;
    private ResQuizDto resQuizDto;

    @Before
    public void setUp(){
        word = new Word(1L, 1L, 1L, "content");
        quiz = Quiz.from(word);
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
