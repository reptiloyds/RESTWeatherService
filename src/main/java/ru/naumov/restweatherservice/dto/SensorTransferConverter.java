package ru.naumov.restweatherservice.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.naumov.restweatherservice.models.Sensor;
import ru.naumov.restweatherservice.services.MeasurementService;
import ru.naumov.restweatherservice.services.SensorsService;

@Service
@RequiredArgsConstructor
public class SensorTransferConverter {
    private final ModelMapper modelMapper;
    private final SensorsService service;

    public SensorDTO convertToDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
