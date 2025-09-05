package ru.naumov.restweatherservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@FieldNameConstants
public class SensorDTO {
    @NotEmpty
    @Size(min = 2, max = 30, message = "Name should be in range between 2 and 30 characters")
    private String name;
}
