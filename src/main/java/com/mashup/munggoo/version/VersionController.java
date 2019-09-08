package com.mashup.munggoo.version;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/version")
public class VersionController {
    private final VersionService versionService;

    @GetMapping
    public ResponseEntity<ResVersionDto> getVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(versionService.getVersion());
    }
}
