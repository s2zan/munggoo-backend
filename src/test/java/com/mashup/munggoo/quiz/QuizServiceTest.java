package com.mashup.munggoo.quiz;

import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightRepository;
import com.mashup.munggoo.highlight.ReqHighlightDto;
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
    private List<HighlightForQuizDto> highlightForQuizDtos;

    private List<Quiz> quizzes;
    private List<QuizDto> quizDtos;

    private Long fileId = 1L;

    @Before
    public void setUp() {
    }

    @Test
    public void getHighlights(){
        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "안녕", 1));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "안녕하세요 반갑습니다.", 0));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        highlightRepository.saveAll(highlights);
        highlightForQuizDtos = quizService.getHighlights(fileId);
        assertThat(highlightForQuizDtos.size()).isEqualTo(reqHighlightDtos.size());
        assertThat(highlightForQuizDtos.get(0).getStartIndex()).isEqualTo(reqHighlightDtos.get(0).getStartIndex());
        assertThat(highlightForQuizDtos.get(0).getContent()).isEqualTo(reqHighlightDtos.get(0).getContent());
        assertThat(highlightForQuizDtos.get(0).getIsImportant()).isEqualTo(Boolean.TRUE);
        assertThat(highlightForQuizDtos.get(1).getStartIndex()).isEqualTo(reqHighlightDtos.get(1).getStartIndex());
        assertThat(highlightForQuizDtos.get(1).getContent()).isEqualTo(reqHighlightDtos.get(1).getContent());
        assertThat(highlightForQuizDtos.get(1).getIsImportant()).isEqualTo(Boolean.FALSE);

    }

    @Test
    public void save_delete(){
        quizDtos = new ArrayList<>();
        quizDtos.add(new QuizDto(fileId, 1L, 10L, "hello"));
        quizDtos.add(new QuizDto(fileId, 11L, 13L, "안녕"));
        quizzes = quizDtos.stream().map(quizDto -> Quiz.from(quizDto)).collect(Collectors.toList());
        quizService.save(quizzes);
        assertThat(quizRepository.findByFileId(fileId).size()).isEqualTo(2);
        quizService.delete(fileId);
        assertThat(quizRepository.findByFileId(fileId).size()).isEqualTo(0);
    }

}