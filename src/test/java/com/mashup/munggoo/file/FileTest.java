package com.mashup.munggoo.file;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileTest {
    private File file;

    private ReqFileDto reqFileDto;

    @Before
    public void setUp() {
        reqFileDto = new ReqFileDto("Clean Code");
        file = File.from(1L, reqFileDto);
    }

    @Test
    public void constructFile() {
        assertThat(file.getName()).isEqualTo("Clean Code");
    }
}
