package com.mashup.munggoo.device;

import com.mashup.munggoo.exception.ConflictException;
import com.mashup.munggoo.exception.NotFoundException;
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

    public ResDeviceIdDto getDeviceId(String deviceKey){
        Device device = deviceRepository.findByDeviceKey(deviceKey);
        if(device == null){
            throw new NotFoundException("Device Does Not Exist.");
        }
        return new ResDeviceIdDto(device);
    }

    private boolean isDeviceKey(String deviceKey) {
        return deviceRepository.existsByDeviceKey(deviceKey);
    }
}
