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

    public File(FileDto fileDto) {
        this.deviceId = fileDto.getDeviceId();
        this.name = fileDto.getName();
    }

    public static File from(FileDto fileDto) {
        return new File(fileDto);
    }
}
