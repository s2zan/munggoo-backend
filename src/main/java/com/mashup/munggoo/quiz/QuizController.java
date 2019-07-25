package com.mashup.munggoo.quiz;

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

    @GetMapping
    public ResponseEntity<List<ResQuizDto>> createQuiz(@PathVariable(value="device-id") Long deviceId,
                                           @PathVariable(value="file-id") Long fileId){
        List<HighlightForQuizDto> highlightList = quizService.getHighlights(fileId);

        quizService.delete(fileId);

        List<Quiz> result = quizService.save(QuizGenerator.generateQuizSet(highlightList));

        return ResponseEntity.status(HttpStatus.OK).body(result.stream().map(ResQuizDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/re")
    public ResponseEntity<List<ResQuizDto>> retakeQuiz(@PathVariable(value="device-id") Long deviceId,
                                                       @PathVariable(value="file-id") Long fileId){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getQuiz(fileId).stream().map(ResQuizDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ScoreDto> quizResult(@PathVariable(value="device-id") Long deviceId,
                                               @PathVariable(value="file-id") Long fileId,
                                               @RequestBody List<ReqResultDto> reqResultDtos){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.marking(fileId, reqResultDtos));
    }

}
