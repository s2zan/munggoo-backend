package com.mashup.munggoo.device;

import com.mashup.munggoo.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public Device save(ReqDeviceDto reqDeviceDto) {
        if (isDeviceKey(reqDeviceDto.getDeviceKey())) {
            throw new ConflictException("Duplicated Device.");
        }
        return deviceRepository.save(Device.from(reqDeviceDto));
    }

    private boolean isDeviceKey(String deviceKey) {
        return deviceRepository.existsByDeviceKey(deviceKey);
    }
}
