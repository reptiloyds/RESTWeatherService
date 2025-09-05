package ru.naumov.restweatherservice.exceptions;

public class SensorCreationFailedException extends RuntimeException {
  public SensorCreationFailedException(String message) {
    super(message);
  }
}
