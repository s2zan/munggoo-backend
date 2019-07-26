package com.mashup.munggoo.quiz;

import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.dto.Result;
import com.mashup.munggoo.quiz.quizgenerator.Word;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    private ReqAnswerDto reqAnswerDto;
    private Result result;
    private List<Result> resultList;
    private ScoreDto scoreDto;
    private Quiz quiz;
    private Word word;

    @Before
    public void setUp(){
        word = new Word(1L, 1L, 1L, "Test");
        quiz = Quiz.from(word);

        reqAnswerDto = new ReqAnswerDto("test");
        resultList = new ArrayList<>();
        result = new Result(reqAnswerDto, quiz);
        resultList.add(result);
        scoreDto = new ScoreDto(resultList);
    }

    @Test
    public void constructReqResultDto() {
        assertThat(reqAnswerDto.getUserAnswer()).isEqualTo("test");
    }

    @Test
    public void constructAnswerDto() {
        assertThat(result.getMark()).isEqualTo(1);
        assertThat(result.getRealAnswer()).isEqualTo("Test");
        assertThat(result.getUserAnswer()).isEqualTo("test");
    }

    @Test
    public void constructScoreDto() {
        assertThat(scoreDto.getPerfectScore()).isEqualTo(1);
        assertThat(scoreDto.getScore()).isEqualTo(1);
    }

}
