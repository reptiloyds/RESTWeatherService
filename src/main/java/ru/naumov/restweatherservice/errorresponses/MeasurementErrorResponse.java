package ru.naumov.restweatherservice.errorresponses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class MeasurementErrorResponse {
    private String message;
    private Instant timestamp;
}
