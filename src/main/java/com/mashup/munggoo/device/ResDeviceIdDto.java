package com.mashup.munggoo.device;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ResDeviceIdDto {
    private Long id;

    public ResDeviceIdDto(Device device){
        this.id = device.getId();
    }
}
