package com.xx1ee.sensorsApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Value не может быть пустой")
    @Min(-100)
    @Max(100)
    Float value;
    @NotBlank(message = "Raining не может быть пустым")
    Boolean raining;
    @NotBlank(message = "Sensor не может быть пустыь")
    @ManyToOne
    Sensor sensor;
    LocalDate date;
}
