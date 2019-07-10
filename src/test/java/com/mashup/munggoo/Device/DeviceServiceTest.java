package com.mashup.munggoo.Device;

import com.mashup.munggoo.device.Device;
import com.mashup.munggoo.device.DeviceRepository;
import com.mashup.munggoo.device.DeviceService;
import com.mashup.munggoo.device.ReqDeviceDto;
import com.mashup.munggoo.exception.ConflictException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceServiceTest {
    @Autowired
    private DeviceService deviceService;

    @MockBean
    private DeviceRepository deviceRepository;

    private ReqDeviceDto reqDeviceDto;

    @Before
    public void setUp() {
        reqDeviceDto = new ReqDeviceDto("qwer1234");
    }

    @Test
    public void saveDevice() {
        given(deviceRepository.findByDeviceKey(any())).willReturn(null);
        given(deviceRepository.save(any())).willReturn(Device.from(reqDeviceDto));
        assertThat(deviceService.save(reqDeviceDto).getDeviceKey()).isEqualTo(reqDeviceDto.getDeviceKey());
    }

    @Test(expected = ConflictException.class)
    public void saveDuplicatedDevice() {
        given(deviceRepository.findByDeviceKey(any())).willReturn(Device.from(reqDeviceDto));
        when(deviceRepository.findByDeviceKey(any()).getDeviceKey().equals(reqDeviceDto.getDeviceKey())).thenThrow(new ConflictException());
        deviceService.save(reqDeviceDto);
    }
}
