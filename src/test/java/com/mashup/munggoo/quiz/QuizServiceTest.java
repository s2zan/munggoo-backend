package com.mashup.munggoo.quiz;

import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightRepository;
import com.mashup.munggoo.highlight.ReqHighlightDto;
import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.dto.QuizDto;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.repository.QuizRepository;
import com.mashup.munggoo.quiz.service.QuizService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuizServiceTest {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private HighlightRepository highlightRepository;


    private List<ReqHighlightDto> reqHighlightDtos;
    private List<Highlight> highlights;

    private List<Quiz> quizzes;
    private List<QuizDto> quizDtos;

    private Long fileId = 1L;

    @Before
    public void setUp() {
        quizRepository.deleteAll();
        highlightRepository.deleteAll();
    }


    @Test
    public void getHighlights(){
        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "안녕", 1));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "hello", 0));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        highlights = highlightRepository.saveAll(highlights);

        assertThat(highlights.size()).isEqualTo(reqHighlightDtos.size());
        assertThat(highlights.get(0).getStartIndex()).isEqualTo(reqHighlightDtos.get(0).getStartIndex());
        assertThat(highlights.get(0).getContent()).isEqualTo(reqHighlightDtos.get(0).getContent());
        assertThat(highlights.get(0).getIsImportant()).isEqualTo(Boolean.TRUE);
        assertThat(highlights.get(1).getStartIndex()).isEqualTo(reqHighlightDtos.get(1).getStartIndex());
        assertThat(highlights.get(1).getContent()).isEqualTo(reqHighlightDtos.get(1).getContent());
        assertThat(highlights.get(1).getIsImportant()).isEqualTo(Boolean.FALSE);

    }

    @Test
    public void createAndGetQuiz(){
        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "종합 병원", 1));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "호두과자", 0));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        highlights = highlightRepository.saveAll(highlights);
        List<Quiz> quizzes = quizService.createQuiz(fileId);

        assertThat(quizzes.size()).isEqualTo(2);
        assertThat(quizService.getQuiz(fileId).size()).isEqualTo(quizzes.size());
    }

    @Test
    public void marking(){

        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "hello", 1));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "안녕", 0));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        highlights = highlightRepository.saveAll(highlights);
        List<Quiz> quizzes = quizService.createQuiz(fileId);

        List<ReqAnswerDto> reqAnswerDtos = new ArrayList<>();
        reqAnswerDtos.add(new ReqAnswerDto("Hello"));
        reqAnswerDtos.add(new ReqAnswerDto("안녕"));

        quizService.marking(fileId, reqAnswerDtos);
    }

    @Test(expected = NotFoundException.class)
    public void getEmptyQuiz() {
        quizService.getQuiz(fileId);
    }
}