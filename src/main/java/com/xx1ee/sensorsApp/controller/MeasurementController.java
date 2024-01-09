package com.xx1ee.sensorsApp.controller;

import com.xx1ee.sensorsApp.dto.SensorDto;
import com.xx1ee.sensorsApp.entity.Measurement;
import com.xx1ee.sensorsApp.service.MeasurementService;
import com.xx1ee.sensorsApp.service.SensorService;
import com.xx1ee.sensorsApp.util.MeasurementValidator;
import com.xx1ee.sensorsApp.util.SensorNotCreatedException;
import com.xx1ee.sensorsApp.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> regSensor(@RequestBody @Valid Measurement measurement, BindingResult bindingResult) {
        measurementValidator.validate(measurement, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (var result : bindingResult.getFieldErrors()) {
                stringBuilder
                        .append(result.getField())
                        .append(" - ")
                        .append(result.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNotCreatedException(stringBuilder.toString());
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
