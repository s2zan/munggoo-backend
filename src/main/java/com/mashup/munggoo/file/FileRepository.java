package com.mashup.munggoo.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    Boolean existsByNameAndDeviceId(String name, Long deviceId);

    List<File> findByDeviceId(Long deviceId);
}
