package com.mashup.munggoo.file;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(nullable = false)
    private String name;

    public File(Long deviceId, ReqFileDto reqFileDto) {
        this.deviceId = deviceId;
        this.name = reqFileDto.getName();
    }

    public static File from(Long deviceId, ReqFileDto reqFileDto) {
        return new File(deviceId, reqFileDto);
    }
}
