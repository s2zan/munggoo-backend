package com.mashup.munggoo.device;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> saveDevice(@RequestBody ReqDeviceDto reqDeviceDto) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.save(reqDeviceDto));
    }

    @GetMapping("/{device-key}")
    public ResponseEntity<ResDeviceIdDto> getDeviceId(@PathVariable(value="device-key") String deviceKey) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDeviceId(deviceKey));
    }
}
