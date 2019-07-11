package com.mashup.munggoo.highlight;

import com.mashup.munggoo.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HighlightService {
    private final HighlightRepository highlightRepository;

    public List<Highlight> save(Long fileId, List<ReqHighlightDto> reqHighlightDtos) {
        if (reqHighlightDtos.isEmpty()) {
            throw new BadRequestException("Request Body Is Empty.");
        }
        return highlightRepository.saveAll(reqHighlightDtos.stream()
                .map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto))
                .collect(Collectors.toList()));
    }
}
