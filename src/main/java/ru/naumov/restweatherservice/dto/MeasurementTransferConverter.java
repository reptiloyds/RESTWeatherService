package ru.naumov.restweatherservice.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.naumov.restweatherservice.models.Measurement;
import ru.naumov.restweatherservice.models.Sensor;
import ru.naumov.restweatherservice.services.SensorsService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MeasurementTransferConverter {
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    public MeasurementDTO convertToDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        Optional<Sensor> sensorOptional = sensorsService.findByName(measurementDTO.getSensor().getName());
        sensorOptional.ifPresent(measurement::setSensor);
        return measurement;
    }
}
