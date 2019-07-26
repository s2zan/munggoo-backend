package com.mashup.munggoo.highlight;

import lombok.Getter;

import java.util.List;

@Getter
public class ResHighlightsDto {
    private List<ResHighlightDto> highlights;

    public ResHighlightsDto(List<ResHighlightDto> highlights) {
        this.highlights = highlights;
    }
}
