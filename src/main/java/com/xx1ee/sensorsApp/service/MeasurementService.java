package com.xx1ee.sensorsApp.service;

import com.xx1ee.sensorsApp.entity.Measurement;
import com.xx1ee.sensorsApp.repos.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    public void save(Measurement measurement ){
        measurement.setDate(LocalDate.now());
        measurementRepository.save(measurement);
    }
}
