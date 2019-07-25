package com.mashup.munggoo.quiz.dto;

import com.mashup.munggoo.highlight.Highlight;
import com.mashup.munggoo.highlight.HighlightType;
import lombok.Getter;

@Getter
public class HighlightForQuizDto {
    private Long fileId;
    private Long startIndex;
    private Long endIndex;
    private String content;
    private Boolean isImportant;
    private HighlightType type;

    public HighlightForQuizDto(Highlight highlight) {
        fileId = highlight.getFileId();
        startIndex = highlight.getStartIndex();
        endIndex = highlight.getEndIndex();
        content = highlight.getContent();
        isImportant = highlight.getIsImportant();
        type = highlight.getType();
    }
}
