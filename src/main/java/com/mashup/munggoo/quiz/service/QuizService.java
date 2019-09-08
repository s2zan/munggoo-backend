package com.mashup.munggoo.quiz.service;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.dto.ResQuizDto;
import com.mashup.munggoo.quiz.dto.Result;
import com.mashup.munggoo.quiz.dto.ReqAnswerDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import com.mashup.munggoo.quiz.quizgenerator.QuizGenerator;
import com.mashup.munggoo.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final HighlightForQuizService highlightForQuizService;

    @Transactional
    public List<ResQuizDto> createQuiz(Long fileId){
        List<Highlight> highlights = highlightForQuizService.getHighlights(fileId);
        if(highlights.isEmpty()){
            throw new NotFoundException("Highlight Does Not Exist.");
        }

        if(quizAlreadyExist(fileId)){
            quizRepository.deleteQuizzesByFileId(fileId);
        }

        List<Quiz> quizzes= QuizGenerator.generateQuizSet(highlights);

        if(quizzes.isEmpty()){
            throw new NotFoundException("Quiz Does Not Generated.");
        }

        quizzes.sort(Comparator.comparing(Quiz::getStartIndex));
        return quizRepository.saveAll(quizzes).stream().map(ResQuizDto::new).collect(Collectors.toList());
    }

    private boolean quizAlreadyExist(Long fileId){
        return quizRepository.existsQuizzesByFileId(fileId);
    }

    public List<ResQuizDto> getQuiz(Long fileId){
        List<Quiz> quizzes = quizRepository.findByFileIdOrderByStartIndex(fileId);
        if (quizzes.isEmpty()) {
            throw new NotFoundException("Quiz Does Not Exist.");
        }
        return quizzes.stream().map(ResQuizDto::new).collect(Collectors.toList());
    }

    public ScoreDto marking(Long fileId, List<ReqAnswerDto> reqAnswerDtos){
        List<Quiz> quizzes = quizRepository.findByFileIdOrderByStartIndex(fileId);

        if (quizzes.isEmpty()) {
            throw new NotFoundException("Quiz Does Not Exist.");
        }
        if(quizzes.size() != reqAnswerDtos.size()){
            throw new ConflictException("User answer doesn't match with the quiz.");
        }

        List<Result> results = new ArrayList<>();

        for(int i = 0; i< quizzes.size(); i++){
            results.add(new Result(reqAnswerDtos.get(i), quizzes.get(i)));
        }

        return new ScoreDto(results);
    }

}
