package com.xx1ee.sensorsApp.controller;

import com.xx1ee.sensorsApp.model.Measurement;
import com.xx1ee.sensorsApp.model.MeasurementResponse;
import com.xx1ee.sensorsApp.service.MeasurementService;
import com.xx1ee.sensorsApp.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping
    public List<MeasurementResponse> get() {
        var l = measurementService.get();
        List<MeasurementResponse> measurementResponses = new ArrayList<>();
        if (l.isEmpty()) {
            return measurementResponses;
        }
        for (var m : l) {
            measurementResponses.add(MeasurementResponse.builder()
                            .date(m.getDate())
                            .raining(m.getRaining())
                            .sensorName(m.getSensor().getName())
                            .value(m.getValue())
                    .build());
        }
        return measurementResponses;
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
    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> count() {
        return new ResponseEntity<>(measurementService.getCount(), HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        return new ResponseEntity<>(new SensorErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
