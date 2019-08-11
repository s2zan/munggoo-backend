package com.mashup.munggoo.quiz;

import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightRepository;
import com.mashup.munggoo.highlight.ReqHighlightDto;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ResQuizDto;
import com.mashup.munggoo.quiz.repository.QuizRepository;
import com.mashup.munggoo.quiz.service.HighlightForQuizService;
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
    private HighlightForQuizService highlightForQuizService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private HighlightRepository highlightRepository;



    private List<ReqHighlightDto> reqHighlightDtos;
    private List<Highlight> highlights;

    private Long fileId = 1L;

    @Before
    public void setUp() {
        quizRepository.deleteAll();
        highlightRepository.deleteAll();

        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "종합 병원", Boolean.TRUE));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "호두과자", Boolean.FALSE));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        highlightRepository.saveAll(highlights);
    }


    @Test
    public void getHighlights(){
        highlights = highlightForQuizService.getHighlights(fileId);

        assertThat(highlights.size()).isEqualTo(reqHighlightDtos.size());
        assertThat(highlights.get(0).getStartIndex()).isEqualTo(reqHighlightDtos.get(0).getStartIndex());
        assertThat(highlights.get(0).getContent()).isEqualTo(reqHighlightDtos.get(0).getContent());
        assertThat(highlights.get(0).getIsImportant()).isEqualTo(Boolean.TRUE);
        assertThat(highlights.get(1).getStartIndex()).isEqualTo(reqHighlightDtos.get(1).getStartIndex());
        assertThat(highlights.get(1).getContent()).isEqualTo(reqHighlightDtos.get(1).getContent());
        assertThat(highlights.get(1).getIsImportant()).isEqualTo(Boolean.FALSE);

    }

    @Test
    public void createQuiz(){
        List<ResQuizDto> quizzes = quizService.createQuiz(fileId);

        assertThat(quizzes.size()).isEqualTo(2);
    }

    @Test(expected = NotFoundException.class)
    public void createQuizWithEmptyHighlight(){
        quizService.createQuiz(fileId + 1);
    }

    @Test(expected = NotFoundException.class)
    public void createQuizButNotGenerated(){
        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "*? #$@", Boolean.TRUE));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId + 1, reqHighlightDto)).collect(Collectors.toList());
        highlightRepository.saveAll(highlights);

        quizService.createQuiz(fileId + 1);
    }

    @Test
    public void marking(){
        List<ResQuizDto> quizzes = quizService.createQuiz(fileId);

        List<ReqAnswerDto> reqAnswerDtos = new ArrayList<>();
        reqAnswerDtos.add(new ReqAnswerDto("종합 병원"));
        reqAnswerDtos.add(new ReqAnswerDto("안녕"));

        quizService.marking(fileId, reqAnswerDtos);
    }

    @Test
    public void getQuiz(){
        List<ResQuizDto> quizzes = quizService.createQuiz(fileId);

        assertThat(quizService.getQuiz(fileId).size()).isEqualTo(2);
    }

    @Test(expected = NotFoundException.class)
    public void getEmptyQuiz() {
        quizService.getQuiz(fileId);
    }
}