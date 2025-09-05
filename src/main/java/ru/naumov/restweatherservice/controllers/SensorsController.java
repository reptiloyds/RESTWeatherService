package ru.naumov.restweatherservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.restweatherservice.dto.SensorDTO;
import ru.naumov.restweatherservice.dto.SensorTransferConverter;
import ru.naumov.restweatherservice.errorresponses.SensorErrorResponse;
import ru.naumov.restweatherservice.exceptions.SensorCreationFailedException;
import ru.naumov.restweatherservice.services.SensorsService;
import ru.naumov.restweatherservice.validators.SensorDTOValidator;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorsController {
    private final SensorDTOValidator validator;
    private final SensorsService service;
    private final SensorTransferConverter transferConverter;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        validator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder exceptionMessage = new StringBuilder();
            for (FieldError error : fieldErrors) {
                exceptionMessage
                        .append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorCreationFailedException(exceptionMessage.toString());
        }

        service.save(transferConverter.convertToSensor(sensorDTO));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleCreationFailedException(SensorCreationFailedException exception) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(exception.getMessage(), Instant.now());
        return ResponseEntity.badRequest().body(sensorErrorResponse);
    }
}
