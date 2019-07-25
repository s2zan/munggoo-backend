package com.mashup.munggoo.quiz.service;

import com.mashup.munggoo.exception.NotFoundException;
import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HighlightForQuizService {
    private final HighlightRepository highlightRepository;

    public List<Highlight> getHighlights(Long fileId) {
        List<Highlight> highlights = highlightRepository.findByFileId(fileId);
        if (highlights.isEmpty()) {
            throw new NotFoundException("Highlight Does Not Exist.");
        }
        return highlights;
    }

}
