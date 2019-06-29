package com.mashup.munggoo.highlight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HighlightDto {
    private Long fileId;
    private Long startIndex;
    private Long endIndex;
    private String content;
    private int type;
    private int priority;
}
