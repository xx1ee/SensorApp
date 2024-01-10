package com.xx1ee.sensorsApp.controller;

import com.xx1ee.sensorsApp.dto.SensorDto;
import com.xx1ee.sensorsApp.model.Sensor;
import com.xx1ee.sensorsApp.service.SensorService;
import com.xx1ee.sensorsApp.util.SensorErrorResponse;
import com.xx1ee.sensorsApp.util.SensorNotCreatedException;
import com.xx1ee.sensorsApp.util.SensorValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }
    @GetMapping
    public List<Sensor> get() {
        return sensorService.findAll();
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> regSensor(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) {
        sensorValidator.validate(sensorDto, bindingResult);
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
        sensorService.saveSensor(sensorDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        return new ResponseEntity<>(new SensorErrorResponse(e.getMessage(), System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}
