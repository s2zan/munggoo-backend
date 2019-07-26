package com.mashup.munggoo.quiz;

import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.quizGenerator.Word;
import com.mashup.munggoo.quiz.dto.ResQuiz;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuizTest {
    private Quiz quiz;
    private Word word;
    private ResQuiz resQuiz;

    @Before
    public void setUp(){
        word = new Word(1L, 1L, 1L, "content");
        quiz = Quiz.from(word);
        resQuiz = new ResQuiz(quiz);
    }

    @Test
    public void constructQuiz() {
        assertThat(quiz.getContent()).isEqualTo("content");
    }

    @Test
    public void constructResQuizDto() {
        assertThat(resQuiz.getEndIndex()).isEqualTo(1);
    }
}
