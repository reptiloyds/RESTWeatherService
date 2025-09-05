package ru.naumov.restweatherservice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@FieldNameConstants
public class MeasurementDTO {
    @DecimalMin(value = "-100.0", message = "Value should be greater or equal than -100.0")
    @DecimalMax(value = "100.0", message = "Value should be less or equal than 100.0")
    @NotNull
    private Float value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;
}
