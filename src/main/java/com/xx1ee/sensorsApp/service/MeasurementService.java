package com.xx1ee.sensorsApp.service;

import com.xx1ee.sensorsApp.model.Measurement;
import com.xx1ee.sensorsApp.repos.MeasurementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }
    @Transactional
    public void save(Measurement measurement ){
        measurement.setDate(LocalDate.now());
        var sensor = sensorService.findByName(measurement.getSensor().getName());
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
        if (sensor.getMeasurementList() == null) {
            sensor.setMeasurementList(new ArrayList<>());
        }
        sensor.getMeasurementList().add(measurement);
    }
    @Transactional
    public List<Measurement> get() {
        return measurementRepository.findAll();
    }
    @Transactional
    public Integer getCount() {
        return measurementRepository.findMeasurementsByRainingIsTrue().size();
    }

}
