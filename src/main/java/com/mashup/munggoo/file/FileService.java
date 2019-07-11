package com.mashup.munggoo.file;

import com.mashup.munggoo.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FileService {
    private final FileRepository fileRepository;

    public File save(Long deviceId, ReqFileDto reqFileDto) {
        if (isFileName(reqFileDto.getName())) {
            throw new ConflictException("Duplicated File Name.");
        }
        return fileRepository.save(File.from(deviceId, reqFileDto));
    }

    private Boolean isFileName(String name) {
        return fileRepository.existsByName(name);
    }
}
