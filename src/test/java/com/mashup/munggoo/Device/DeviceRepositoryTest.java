package com.mashup.munggoo.Device;

import com.mashup.munggoo.device.Device;
import com.mashup.munggoo.device.DeviceRepository;
import com.mashup.munggoo.device.ReqDeviceDto;
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
        assertThat(deviceRepository.findByDeviceKey(device.getDeviceKey())).isEqualTo(savedDevice);
    }
}
