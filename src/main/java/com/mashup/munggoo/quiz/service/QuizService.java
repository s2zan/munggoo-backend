package com.mashup.munggoo.quiz.service;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.quiz.domain.Quiz;
import com.mashup.munggoo.quiz.dto.AnswerDto;
import com.mashup.munggoo.quiz.dto.ReqResultDto;
import com.mashup.munggoo.quiz.dto.ScoreDto;
import com.mashup.munggoo.quiz.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public void delete(Long fileId){
        if(quizAlreadyExist(fileId)){
            quizRepository.deleteQuizzesByFileId(fileId);
        }
    }
    private boolean quizAlreadyExist(Long fileId){
        return quizRepository.existsQuizzesByFileId(fileId);
    }

    public List<Quiz> save(List<Quiz> quizList){
        return quizRepository.saveAll(quizList);
    }

    public List<Quiz> getQuiz(Long fileId){
        List<Quiz> quizzes = quizRepository.findByFileIdOrderByStartIndex(fileId);
        if (quizzes.isEmpty()) {
            throw new NotFoundException("Quiz Does Not Exist.");
        }
        return quizzes;
    }

    public ScoreDto marking(Long fileId, List<ReqResultDto> reqResultDtos){
        List<Quiz> quizzes = quizRepository.findByFileIdOrderByStartIndex(fileId);

        if (quizzes.isEmpty()) {
            throw new NotFoundException("Quiz Does Not Exist.");
        }
        if(quizzes.size() != reqResultDtos.size()){
            throw new ConflictException("User answer doesn't match with the quiz.");
        }

        List<AnswerDto> answerDtos = new ArrayList<>();

        for(int i = 0; i< quizzes.size(); i++){
            answerDtos.add(new AnswerDto(reqResultDtos.get(i), quizzes.get(i)));
        }

        return new ScoreDto(answerDtos);
    }

}
