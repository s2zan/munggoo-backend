package com.mashup.munggoo.device;

import com.mashup.munggoo.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public Device save(ReqDeviceDto reqDeviceDto) {
        Device device = deviceRepository.findByDeviceKey(reqDeviceDto.getDeviceKey());
        if (device != null) {
            throw new ConflictException("Duplicated Device.");
        }
        return deviceRepository.save(Device.from(reqDeviceDto));
    }
}
