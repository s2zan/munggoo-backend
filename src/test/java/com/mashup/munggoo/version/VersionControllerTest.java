package com.mashup.munggoo.version;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(VersionController.class)
public class VersionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VersionService versionService;

    private Version version;

    private ResVersionDto resVersionDto;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        version = new Version("1.0.0", "link");
        resVersionDto = new ResVersionDto(version);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getVersion() throws Exception {
        when(versionService.getVersion()).thenReturn(resVersionDto);
        mockMvc.perform(get("/version", "test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.version").value(version.getVersion()))
                .andExpect(jsonPath("$.link").value(version.getLink()))
                .andDo(print());
    }
}
