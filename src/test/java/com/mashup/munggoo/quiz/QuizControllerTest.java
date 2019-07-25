package com.mashup.munggoo.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.ReqHighlightDto;
import com.mashup.munggoo.quiz.controller.QuizController;
import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.dto.*;
import com.mashup.munggoo.quiz.service.HighlightForQuizService;
import com.mashup.munggoo.quiz.service.QuizService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@WebMvcTest(QuizController.class)
public class QuizControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;
    private HighlightForQuizService highlightForQuizService;

    private List<Highlight> highlights;
    private List<Quiz> quizzes;
    private Long fileId = 1L;

    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        highlights = new ArrayList<>();
        highlights.add(new Highlight(fileId, new ReqHighlightDto(0L,4L,"종합 병원", 0)));
        highlights.add(new Highlight(fileId, new ReqHighlightDto(5L,9L,"호두과자", 0)));
        quizzes = new ArrayList<>();
        quizzes.add(new Quiz(new QuizDto(1L, 0L, 4L, "종합 병원")));
        quizzes.add(new Quiz(new QuizDto(1L, 5L, 9L, "호두과자")));

        objectMapper = new ObjectMapper();
    }

    @Test
    public void createQuiz() throws Exception {
        when(highlightForQuizService.getHighlights(any())).thenReturn(highlights);
        when(quizService.getQuiz(any())).thenReturn(quizzes);
        mockMvc.perform(get("/v1/devices/{device-id}/files/{file-id}/quiz", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].startIndex").value(highlights.get(0).getStartIndex()))
                .andExpect(jsonPath("$.[0].endIndex").value(highlights.get(0).getEndIndex()))
                .andExpect(jsonPath("$.[1].startIndex").value(highlights.get(1).getStartIndex()))
                .andExpect(jsonPath("$.[1].endIndex").value(highlights.get(1).getEndIndex()))
                .andDo(print());

    }

    @Test
    public void retakeQuiz() throws Exception{
        when(quizService.getQuiz(any())).thenReturn(quizzes);
        mockMvc.perform(get("/v1/devices/{device-id}/files/{file-id}/quiz/re", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].startIndex").value(highlights.get(0).getStartIndex()))
                .andExpect(jsonPath("$.[0].endIndex").value(highlights.get(0).getEndIndex()))
                .andExpect(jsonPath("$.[1].startIndex").value(highlights.get(1).getStartIndex()))
                .andExpect(jsonPath("$.[1].endIndex").value(highlights.get(1).getEndIndex()))
                .andDo(print());
    }

    @Test
    public void quizResult() throws Exception {
        QuizDto quizDto = new QuizDto(1L, 1L, 1L, "Test");
        Quiz quiz = Quiz.from(quizDto);

        ReqAnswerDto reqAnswerDto = new ReqAnswerDto("test");
        List<ReqAnswerDto> reqAnswerDtos = new ArrayList<>();
        reqAnswerDtos.add(reqAnswerDto);

        List<Result> resultList = new ArrayList<>();
        Result result = new Result(reqAnswerDto, quiz);
        resultList.add(result);
        ScoreDto scoreDto = new ScoreDto(resultList);

        when(quizService.marking(any(), any())).thenReturn(scoreDto);

        mockMvc.perform(post("/v1/devices/{device-id}/files/{file-id}/quiz", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(reqAnswerDtos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.score").value(1))
                .andExpect(jsonPath("$.perfectScore").value(1))
                .andExpect(jsonPath("$.result.[0].userAnswer").value(reqAnswerDtos.get(0).getUserAnswer()))
                .andExpect(jsonPath("$.result.[0].realAnswer").value(quizDto.getContent()))
                .andExpect(jsonPath("$.result.[0].mark").value(1))
                .andDo(print());
    }
}