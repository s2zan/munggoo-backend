package com.mashup.munggoo.device;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceRepositoryTest {
    @Autowired
    private DeviceRepository deviceRepository;

    private Device device;

    private Device savedDevice;

    private ReqDeviceDto reqDeviceDto;

    @Before
    public void setUp() {
        reqDeviceDto = new ReqDeviceDto("qwer1234");
        device = Device.from(reqDeviceDto);
        savedDevice = deviceRepository.save(device);
    }

    @Test
    public void findDeviceByDeviceKey() {
        assertThat(deviceRepository.findByDeviceKey(reqDeviceDto.getDeviceKey())).isEqualTo(savedDevice);
    }

    @Test
    public void existsDeviceByDeviceKey() {
        assertThat(deviceRepository.existsByDeviceKey(reqDeviceDto.getDeviceKey())).isTrue();
    }
}
