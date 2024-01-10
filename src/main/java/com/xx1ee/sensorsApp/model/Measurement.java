package com.xx1ee.sensorsApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "measurement")
@Entity
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotNull(message = "Value не может быть пустой")
    @Min(-100)
    @Max(100)
    Double value;
    @NotNull(message = "Raining не может быть пустым")
    Boolean raining;
    LocalDate date;
    @NotNull(message = "Sensor не может быть пустыь")
    @ManyToOne
    @JoinColumn(name = "sensor")
    Sensor sensor;
}
