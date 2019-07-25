package com.mashup.munggoo.quiz;

import com.mashup.munggoo.quiz.dto.AnswerDto;
import com.mashup.munggoo.quiz.dto.QuizDto;
import com.mashup.munggoo.quiz.dto.ReqResultDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import com.mashup.munggoo.quiz.quizGenerator.Quiz;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    private ReqResultDto reqResultDto;
    private AnswerDto answerDto;
    private List<AnswerDto> answerDtoList;
    private ScoreDto scoreDto;
    private Quiz quiz;
    private QuizDto quizDto;

    @Before
    public void setUp(){
        quizDto = new QuizDto(1L, 1L, 1L, "Test");
        quiz = Quiz.from(quizDto);

        reqResultDto = new ReqResultDto("test");
        answerDtoList = new ArrayList<>();
        answerDto = new AnswerDto(reqResultDto, quiz);
        answerDtoList.add(answerDto);
        scoreDto = new ScoreDto(answerDtoList);
    }

    @Test
    public void constructReqResultDto() {
        assertThat(reqResultDto.getUserAnswer()).isEqualTo("test");
    }

    @Test
    public void constructAnswerDto() {
        assertThat(answerDto.getMark()).isEqualTo(1);
        assertThat(answerDto.getRealAnswer()).isEqualTo("Test");
        assertThat(answerDto.getUserAnswer()).isEqualTo("test");
    }

    @Test
    public void constructScoreDto() {
        assertThat(scoreDto.getPerfectScore()).isEqualTo(1);
        assertThat(scoreDto.getScore()).isEqualTo(1);
    }

}
