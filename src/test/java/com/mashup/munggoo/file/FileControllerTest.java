package com.mashup.munggoo.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.munggoo.exception.ConflictException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileService fileService;

    private File file;

    private Long deviceId;

    private ReqFileDto reqFileDto;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        deviceId = 1L;
        reqFileDto = new ReqFileDto("Clean Code");
        file = File.from(deviceId, reqFileDto);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void saveFile() throws Exception {
        when(fileService.save(any(), any())).thenReturn(file);
        mockMvc.perform(post("/v1/devices/{device-id}/files", deviceId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reqFileDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.deviceId").value(deviceId))
                .andExpect(jsonPath("$.name").value(reqFileDto.getName()))
                .andDo(print());
    }

    @Test
    public void saveDuplicatedFileName() throws Exception {
        when(fileService.save(any(), any())).thenThrow(new ConflictException("Duplicated File Name."));
        mockMvc.perform(post("/v1/devices/{device-id}/files", deviceId)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reqFileDto)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.code").value("409"))
                .andExpect(jsonPath("$.msg").value("Duplicated File Name."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }
}
