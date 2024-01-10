package com.xx1ee.sensorsApp.model;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementResponse {
    Double value;
    Boolean raining;
    LocalDate date;
    String sensorName;
}
