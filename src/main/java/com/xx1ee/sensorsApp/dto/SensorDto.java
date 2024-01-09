package com.xx1ee.sensorsApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SensorDto {
    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 3, max = 30, message = "Имя должно содержать от 3 до 30 символов")
    String name;
}
