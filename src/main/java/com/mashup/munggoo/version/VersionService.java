package com.mashup.munggoo.version;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VersionService {
    private final VersionRepository versionRepository;

    public ResVersionDto getVersion() {
        Version version = versionRepository.findAll().get(0);
        return new ResVersionDto(version);
    }
}
