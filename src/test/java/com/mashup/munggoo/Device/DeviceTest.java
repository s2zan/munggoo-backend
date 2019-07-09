package com.mashup.munggoo.Device;

import com.mashup.munggoo.device.Device;
import com.mashup.munggoo.device.ReqDeviceDto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeviceTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Device device;

    private ReqDeviceDto reqDeviceDto;

    @Before
    public void setUp() {
        reqDeviceDto = new ReqDeviceDto("qwer1234");
        device = Device.from(reqDeviceDto);
    }

    @Test
    public void saveDevice() {
        Device savedDevice = this.testEntityManager.persistAndFlush(device);
        assertThat(savedDevice.getDeviceKey()).isEqualTo("qwer1234");
    }
}
