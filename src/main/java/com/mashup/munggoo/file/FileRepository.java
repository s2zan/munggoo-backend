package com.mashup.munggoo.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    Boolean existsByName(String name);
    List<File> findByDeviceId(Long deviceId);
}
