package com.mashup.munggoo.quiz.controller;

import com.mashup.munggoo.quiz.service.HighlightForQuizService;
import com.mashup.munggoo.quiz.service.QuizService;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ResQuiz;
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
    public ResponseEntity<List<ResQuiz>> createQuiz(@PathVariable(value="device-id") Long deviceId,
                                                    @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.createQuiz(fileId));
    }

    @GetMapping("/re")
    public ResponseEntity<List<ResQuiz>> retakeQuiz(@PathVariable(value="device-id") Long deviceId,
                                                    @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuiz(fileId));
    }

    @PostMapping
    public ResponseEntity<ScoreDto> quizResult(@PathVariable(value="device-id") Long deviceId,
                                               @PathVariable(value="file-id") Long fileId,
                                               @RequestBody List<ReqAnswerDto> reqAnswerDtos){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.marking(fileId, reqAnswerDtos));
    }

}
