package com.mashup.munggoo.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> saveDevice(@RequestBody ReqDeviceDto reqDeviceDto) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.save(reqDeviceDto));
    }
}
