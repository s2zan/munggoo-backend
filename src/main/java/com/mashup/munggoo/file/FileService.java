package com.mashup.munggoo.file;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<ResFileDto> getFiles(Long deviceId) {
        List<File> files = fileRepository.findByDeviceId(deviceId);
        if (files.isEmpty()) {
            throw new NotFoundException("File Does Not Exist.");
        }
        return files.stream().map(ResFileDto::new).collect(Collectors.toList());
    }

    private Boolean isFileName(String name) {
        return fileRepository.existsByName(name);
    }
}
