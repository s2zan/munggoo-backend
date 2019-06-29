package com.mashup.munggoo.file;

import com.mashup.munggoo.device.Device;
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

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    public File(FileDto fileDto) {
        this.deviceId = fileDto.getDeviceId();
        this.name = fileDto.getName();
    }

    public static File from(FileDto fileDto) {
        return new File(fileDto);
    }
}
