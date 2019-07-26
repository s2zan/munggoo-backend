package com.mashup.munggoo.highlight;

import lombok.Getter;

import java.util.List;

@Getter
public class HighlightsDto {
    List<Highlight> highlights;

    public HighlightsDto(List<Highlight> highlights) {
        this.highlights = highlights;
    }
}
