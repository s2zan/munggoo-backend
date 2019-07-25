package com.mashup.munggoo.quiz.controller;

import com.mashup.munggoo.quiz.service.HighlightForQuizService;
import com.mashup.munggoo.quiz.service.QuizService;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ResQuizDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/devices/{device-id}/files/{file-id}/quiz")
public class QuizController {
    private final QuizService quizService;
    private final HighlightForQuizService highlightForQuizService;

    @GetMapping
    public ResponseEntity<List<ResQuizDto>> createQuiz(@PathVariable(value="device-id") Long deviceId,
                                                       @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.createQuiz(fileId).stream().map(ResQuizDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/re")
    public ResponseEntity<List<ResQuizDto>> retakeQuiz(@PathVariable(value="device-id") Long deviceId,
                                                       @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuiz(fileId).stream().map(ResQuizDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ScoreDto> quizResult(@PathVariable(value="device-id") Long deviceId,
                                               @PathVariable(value="file-id") Long fileId,
                                               @RequestBody List<ReqAnswerDto> reqAnswerDtos){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.marking(fileId, reqAnswerDtos));
    }

}
