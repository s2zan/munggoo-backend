package com.mashup.munggoo.quiz;

import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final HighlightRepository highlightRepository;


    public List<HighlightForQuizDto> getHighlights(Long fileId) {
        List<Highlight> highlights = highlightRepository.findByFileId(fileId);
        if (highlights.isEmpty()) {
            throw new NotFoundException("Highlight Does Not Exist.");
        }
        return highlights.stream().map(HighlightForQuizDto::new).collect(Collectors.toList());
    }

    @Transactional
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

}
