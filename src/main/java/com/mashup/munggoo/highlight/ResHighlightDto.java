package com.mashup.munggoo.highlight;

import lombok.Getter;

@Getter
public class ResHighlightDto {
    private Long id;
    private Long startIndex;
    private Long endIndex;
    private String content;
    private Boolean isImportant;

    public ResHighlightDto(Highlight highlight) {
        id = highlight.getId();
        startIndex = highlight.getStartIndex();
        endIndex = highlight.getEndIndex();
        content = highlight.getContent();
        isImportant = highlight.getIsImportant();
    }
}
