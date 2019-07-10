package com.mashup.munggoo.device;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByDeviceKey(String deviceKey);
}
