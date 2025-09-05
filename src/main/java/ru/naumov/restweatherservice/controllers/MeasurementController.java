package ru.naumov.restweatherservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.naumov.restweatherservice.dto.MeasurementDTO;
import ru.naumov.restweatherservice.dto.MeasurementTransferConverter;
import ru.naumov.restweatherservice.errorresponses.MeasurementErrorResponse;
import ru.naumov.restweatherservice.exceptions.MeasurementCreationFailedException;
import ru.naumov.restweatherservice.models.Measurement;
import ru.naumov.restweatherservice.services.MeasurementService;
import ru.naumov.restweatherservice.validators.MeasurementDTOValidator;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService service;
    private final MeasurementTransferConverter transferConverter;
    private final MeasurementDTOValidator validator;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        validator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder exceptionMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                exceptionMessage.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; ");

            throw new MeasurementCreationFailedException(exceptionMessage.toString());
        }

        service.add(transferConverter.convertToMeasurement(measurementDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        List<Measurement> measurements = service.findAll();
        return measurements.stream()
                .map(transferConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return service.findByRainingIs(true).size();
    }

    @ExceptionHandler()
    public ResponseEntity<MeasurementErrorResponse> handleCreationFailedException(MeasurementCreationFailedException exception) {
        MeasurementErrorResponse errorResponse = new MeasurementErrorResponse(exception.getMessage(), Instant.now());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
