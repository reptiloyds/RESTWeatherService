package ru.naumov.restweatherservice.exceptions;

public class MeasurementCreationFailedException extends RuntimeException {
    public MeasurementCreationFailedException(String message) {
        super(message);
    }
}
