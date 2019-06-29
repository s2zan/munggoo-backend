package com.mashup.munggoo.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_key", unique = true)
    private String deviceKey;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Device(DeviceDto deviceDto) {
        this.deviceKey = deviceDto.getDeviceKey();
    }

    public static Device from(DeviceDto deviceDto) {
        return new Device(deviceDto);
    }
}
