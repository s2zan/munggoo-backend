package com.mashup.munggoo.highlight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqHighlightsDto {
    private List<ReqHighlightDto> highlights;
}
