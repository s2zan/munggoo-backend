package com.mashup.munggoo.file;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    Boolean existsByName(String name);
}
