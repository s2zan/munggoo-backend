package com.mashup.munggoo.device;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTest {
    private Device device;

    private ReqDeviceDto reqDeviceDto;

    @Before
    public void setUp() {
        reqDeviceDto = new ReqDeviceDto("qwer1234");
        device = Device.from(reqDeviceDto);
    }

    @Test
    public void constructDevice() {
        assertThat(device.getDeviceKey()).isEqualTo("qwer1234");
    }
}
