package com.mashup.munggoo.device;

import lombok.Getter;

@Getter
public class ResDeviceIdDto {
    private Long id;

    public ResDeviceIdDto(Device device){
        this.id = device.getId();
    }
}
