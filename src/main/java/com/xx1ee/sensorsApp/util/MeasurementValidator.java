package com.xx1ee.sensorsApp.util;

import com.xx1ee.sensorsApp.model.Measurement;
import com.xx1ee.sensorsApp.service.MeasurementService;
import com.xx1ee.sensorsApp.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;
    private final SensorService sensorService;
    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (sensorService.findByName(measurement.getSensor().getName()) == null) {
            errors.rejectValue("sensor", "", "Данный сенсор не зарегестрирован");
        }
    }
}
