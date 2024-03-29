package com.xx1ee.sensorsApp.service;

import com.xx1ee.sensorsApp.dto.SensorDto;
import com.xx1ee.sensorsApp.model.Sensor;
import com.xx1ee.sensorsApp.repos.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public void saveSensor(SensorDto sensorDto) {
        sensorRepository.save(Sensor.builder()
                        .name(sensorDto.getName())
                .build());
    }
    public Sensor findByName(String name) {
        return sensorRepository.findByName(name);
    }
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }
}
