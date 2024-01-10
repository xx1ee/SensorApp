package com.xx1ee.sensorsApp.util;

import com.xx1ee.sensorsApp.dto.SensorDto;
import com.xx1ee.sensorsApp.model.Sensor;
import com.xx1ee.sensorsApp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;
        if (sensorService.findByName(sensorDto.getName()) != null) {
            errors.rejectValue("name", "", "Данный человек уже зарегестрирован");
        }
    }
}
