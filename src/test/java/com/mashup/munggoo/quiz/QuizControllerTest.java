package com.mashup.munggoo.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.ReqHighlightDto;
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

    private List<HighlightForQuizDto> highlightForQuizDtos;
    private List<Quiz> quizzes;
    private Long fileId = 1L;

    private ObjectMapper objectMapper;

    @Before
    public void setUp(){
        highlightForQuizDtos = new ArrayList<>();
        highlightForQuizDtos.add(new HighlightForQuizDto(new Highlight(fileId, new ReqHighlightDto(0L,4L,"종합 병원", 0))));
        highlightForQuizDtos.add(new HighlightForQuizDto(new Highlight(fileId, new ReqHighlightDto(5L,9L,"호두과자", 0))));
        quizzes = new ArrayList<>();
        quizzes.add(new Quiz(new QuizDto(1L, 0L, 4L, "종합 병원")));
        quizzes.add(new Quiz(new QuizDto(1L, 5L, 9L, "호두과자")));

        objectMapper = new ObjectMapper();
    }

    @Test
    public void createQuiz() throws Exception {
        when(quizService.getHighlights(any())).thenReturn(highlightForQuizDtos);
        when(quizService.save(any())).thenReturn(quizzes);
        mockMvc.perform(get("/v1/devices/{device-id}/files/{file-id}/quiz", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].startIndex").value(highlightForQuizDtos.get(0).getStartIndex()))
                .andExpect(jsonPath("$.[0].endIndex").value(highlightForQuizDtos.get(0).getEndIndex()))
                .andExpect(jsonPath("$.[1].startIndex").value(highlightForQuizDtos.get(1).getStartIndex()))
                .andExpect(jsonPath("$.[1].endIndex").value(highlightForQuizDtos.get(1).getEndIndex()))
                .andDo(print());

    }

    @Test
    public void retakeQuiz() throws Exception{
        when(quizService.getQuiz(any())).thenReturn(quizzes);
        mockMvc.perform(get("/v1/devices/{device-id}/files/{file-id}/quiz/re", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[0].startIndex").value(highlightForQuizDtos.get(0).getStartIndex()))
                .andExpect(jsonPath("$.[0].endIndex").value(highlightForQuizDtos.get(0).getEndIndex()))
                .andExpect(jsonPath("$.[1].startIndex").value(highlightForQuizDtos.get(1).getStartIndex()))
                .andExpect(jsonPath("$.[1].endIndex").value(highlightForQuizDtos.get(1).getEndIndex()))
                .andDo(print());
    }

    @Test
    public void quizResult() throws Exception {
        QuizDto quizDto = new QuizDto(1L, 1L, 1L, "Test");
        Quiz quiz = Quiz.from(quizDto);

        ReqResultDto reqResultDto = new ReqResultDto("test");
        List<ReqResultDto> reqResultDtos = new ArrayList<>();
        reqResultDtos.add(reqResultDto);

        List<AnswerDto> answerDtoList = new ArrayList<>();
        AnswerDto answerDto = new AnswerDto(reqResultDto, quiz);
        answerDtoList.add(answerDto);
        ScoreDto scoreDto = new ScoreDto(answerDtoList);

        when(quizService.marking(any(), any())).thenReturn(scoreDto);

        mockMvc.perform(post("/v1/devices/{device-id}/files/{file-id}/quiz", 1L, fileId)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(reqResultDtos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.score").value(1))
                .andExpect(jsonPath("$.perfectScore").value(1))
                .andExpect(jsonPath("$.result.[0].userAnswer").value(reqResultDtos.get(0).getUserAnswer()))
                .andExpect(jsonPath("$.result.[0].realAnswer").value(quizDto.getContent()))
                .andExpect(jsonPath("$.result.[0].mark").value(1))
                .andDo(print());
    }
}