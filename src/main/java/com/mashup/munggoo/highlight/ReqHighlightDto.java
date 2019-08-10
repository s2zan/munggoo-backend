package com.mashup.munggoo.highlight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqHighlightDto {
    private Long startIndex;
    private Long endIndex;
    private String content;
    private Boolean isImportant;

    public HighlightType stringToEnum(String content) {
        return (content.split(" ").length > 1) ? HighlightType.SENTENCE : HighlightType.WORD;
    }
}
