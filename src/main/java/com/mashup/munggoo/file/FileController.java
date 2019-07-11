package com.mashup.munggoo.file;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/devices/{device-id}/files")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<File> saveFile(@PathVariable(value = "device-id") Long deviceId, @RequestBody ReqFileDto reqFileDto) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.save(deviceId, reqFileDto));
    }

    @GetMapping
    public ResponseEntity<List<ResFileDto>> getFiles(@PathVariable(value = "device-id") Long deviceId) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getFiles(deviceId));
    }
}
