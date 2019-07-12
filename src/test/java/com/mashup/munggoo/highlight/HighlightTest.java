package com.mashup.munggoo.highlight;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HighlightTest {
    private Highlight highlight;

    private Highlight highlight2;

    private ReqHighlightDto reqHighlightDto;

    private ReqHighlightDto reqHighlightDto2;

    @Before
    public void setUp() {
        reqHighlightDto = new ReqHighlightDto(10L, 20L, "안녕", 1);
        highlight = Highlight.from(1L, reqHighlightDto);
        reqHighlightDto2 = new ReqHighlightDto(30L, 40L, "안녕하세요 반갑습니다.", 0);
        highlight2 = Highlight.from(2L, reqHighlightDto2);
    }

    @Test
    public void constructHighlight() {
        assertThat(highlight.getType()).isEqualTo(HighlightType.WORD);
        assertThat(highlight.getIsImportant()).isEqualTo(Boolean.TRUE);
        assertThat(highlight2.getType()).isEqualTo(HighlightType.SENTENCE);
        assertThat(highlight2.getIsImportant()).isEqualTo(Boolean.FALSE);
    }
}
