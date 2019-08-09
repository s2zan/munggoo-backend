package com.mashup.munggoo.device;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
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
        given(deviceRepository.existsByDeviceKey(reqDeviceDto.getDeviceKey())).willReturn(Boolean.FALSE);
        given(deviceRepository.save(any())).willReturn(Device.from(reqDeviceDto));
        assertThat(deviceService.save(reqDeviceDto).getDeviceKey()).isEqualTo(reqDeviceDto.getDeviceKey());
    }

    @Test(expected = ConflictException.class)
    public void saveDuplicatedDevice() {
        given(deviceRepository.existsByDeviceKey(any())).willReturn(Boolean.TRUE);
        deviceService.save(reqDeviceDto);
    }

    @Test
    public void getDeviceId() {
        given(deviceRepository.findByDeviceKey(any())).willReturn(Device.from(reqDeviceDto));
        assertThat(deviceService.getDeviceId("test").equals(new ResDeviceIdDto(Device.from(reqDeviceDto))));
    }

    @Test(expected = NotFoundException.class)
    public void getDeviceIdDoesNotExist() {
        given(deviceRepository.findByDeviceKey(any())).willReturn(null);
        deviceService.getDeviceId("test");
    }
}
