package com.mashup.munggoo.highlight;

import com.mashup.munggoo.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HighlightServiceTest {
    @Autowired
    private HighlightService highlightService;

    @MockBean
    private HighlightRepository highlightRepository;

    private Long fileId;

    private List<ReqHighlightDto> reqHighlightDtos;

    private List<Highlight> highlights;

    @Before
    public void setUp() {
        fileId = 1L;
    }

    @Test
    public void saveHighlights() {
        reqHighlightDtos = new ArrayList<>();
        reqHighlightDtos.add(new ReqHighlightDto(10L, 20L, "안녕", 1));
        reqHighlightDtos.add(new ReqHighlightDto(30L, 40L, "안녕하세요 반갑습니다.", 0));
        highlights = reqHighlightDtos.stream().map(reqHighlightDto -> Highlight.from(fileId, reqHighlightDto)).collect(Collectors.toList());
        given(highlightRepository.saveAll(anyCollection())).willReturn(highlights);
        List<Highlight> savedHighlights = highlightService.save(fileId, reqHighlightDtos);
        assertThat(savedHighlights.size()).isEqualTo(reqHighlightDtos.size());
        assertThat(savedHighlights.get(0).getStartIndex()).isEqualTo(10L);
        assertThat(savedHighlights.get(1).getStartIndex()).isEqualTo(30L);
    }

    @Test(expected = BadRequestException.class)
    public void saveEmptyHighlight() {
        reqHighlightDtos = new ArrayList<>();
        highlightService.save(fileId, reqHighlightDtos);
    }
}
