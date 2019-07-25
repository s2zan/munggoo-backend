package com.mashup.munggoo.quiz;

import com.mashup.munggoo.quiz.dto.QuizDto;
import com.mashup.munggoo.quiz.quizGenerator.Quiz;
import com.mashup.munggoo.quiz.repository.QuizRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuizRepositoryTest {
    @Autowired
    private QuizRepository quizRepository;

    private List<Quiz> savedQuiz;

    private QuizDto quizDto;

    @Before
    public void setUp(){
        List<Quiz> quizList = new ArrayList<>();

        quizDto = new QuizDto(1L,1L,1L,"content");
        quizList.add(Quiz.from(quizDto));

        quizDto = new QuizDto(2L,2L,2L,"content2");
        quizList.add(Quiz.from(quizDto));

        savedQuiz = quizRepository.saveAll(quizList);
    }

    @Test
    public void existQuizzesByFileId() {
        assertThat(quizRepository.existsQuizzesByFileId(1L)).isEqualTo(true);
    }

    @Test
    public void deleteQuizzesByFileId() {
        quizRepository.deleteQuizzesByFileId(1L);
        assertThat(quizRepository.existsQuizzesByFileId(1L)).isEqualTo(false);
    }
}