package com.mashup.munggoo.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Device(ReqDeviceDto reqDeviceDto) {
        this.deviceKey = reqDeviceDto.getDeviceKey();
    }

    public static Device from(ReqDeviceDto reqDeviceDto) {
        return new Device(reqDeviceDto);
    }
}
