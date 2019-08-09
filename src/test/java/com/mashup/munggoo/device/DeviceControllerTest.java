package com.mashup.munggoo.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    private Device device;

    private ResDeviceIdDto resDeviceIdDto;

    private ReqDeviceDto reqDeviceDto;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        reqDeviceDto = new ReqDeviceDto("qwer1234");
        device = Device.from(reqDeviceDto);
        resDeviceIdDto = new ResDeviceIdDto(device);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void saveDevice() throws Exception {
        when(deviceService.save(any())).thenReturn(device);
        mockMvc.perform(post("/v1/devices")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reqDeviceDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andExpect(jsonPath("$.deviceKey").value(reqDeviceDto.getDeviceKey()))
                .andExpect(jsonPath("$.createdAt").hasJsonPath())
                .andDo(print());
    }

    @Test
    public void saveDuplicatedDevice() throws Exception {
        when(deviceService.save(any())).thenThrow(new ConflictException("Duplicated Device."));
        mockMvc.perform(post("/v1/devices")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(objectMapper.writeValueAsString(reqDeviceDto)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.code").value("409"))
                .andExpect(jsonPath("$.msg").value("Duplicated Device."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }



    @Test
    public void getDeviceId() throws Exception {
        when(deviceService.getDeviceId(any())).thenReturn(resDeviceIdDto);
        mockMvc.perform(get("/v1/devices/{device-key}", "test")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(reqDeviceDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(device.getId()))
                .andDo(print());
    }

    @Test
    public void getDeviceIdDoesNotExist() throws Exception {
        when(deviceService.getDeviceId(any())).thenThrow(new NotFoundException("Device Does Not Exist."));
        mockMvc.perform(get("/v1/devices/{device-key}", "test")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(reqDeviceDto)))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(jsonPath("$.msg").value("Device Does Not Exist."))
                .andExpect(jsonPath("$.timestamp").exists())
                .andDo(print());
    }
}
