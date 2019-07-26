package com.mashup.munggoo.quiz.controller;

import com.mashup.munggoo.quiz.dto.ReqAnswersDto;
import com.mashup.munggoo.quiz.dto.ResQuizzesDto;
import com.mashup.munggoo.quiz.service.HighlightForQuizService;
import com.mashup.munggoo.quiz.service.QuizService;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/devices/{device-id}/files/{file-id}/quiz")
public class QuizController {
    private final QuizService quizService;
    private final HighlightForQuizService highlightForQuizService;

    @GetMapping
    public ResponseEntity<ResQuizzesDto> createQuiz(@PathVariable(value="device-id") Long deviceId,
                                                    @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResQuizzesDto(quizService.createQuiz(fileId)));
    }

    @GetMapping("/re")
    public ResponseEntity<ResQuizzesDto> retakeQuiz(@PathVariable(value="device-id") Long deviceId,
                                                    @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(new ResQuizzesDto(quizService.getQuiz(fileId)));
    }

    @PostMapping
    public ResponseEntity<ScoreDto> quizResult(@PathVariable(value="device-id") Long deviceId,
                                               @PathVariable(value="file-id") Long fileId,
                                               @RequestBody ReqAnswersDto reqAnswersDto){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.marking(fileId, reqAnswersDto.getAnswers()));
    }

}
