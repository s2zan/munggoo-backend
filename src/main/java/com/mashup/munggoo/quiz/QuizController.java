package com.mashup.munggoo.quiz;

import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightService;
import com.mashup.munggoo.highlight.ResHighlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

}
