package ru.naumov.restweatherservice.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.naumov.restweatherservice.dto.MeasurementDTO;
import ru.naumov.restweatherservice.models.Sensor;
import ru.naumov.restweatherservice.repository.SensorsRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MeasurementDTOValidator implements Validator {
    private final SensorsRepository sensorsRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        Optional<Sensor> sensor = sensorsRepository.findByName(measurementDTO.getSensor().getName());
        if (sensor.isEmpty())
            errors.rejectValue(MeasurementDTO.Fields.sensor, "", "Sensor with name  " + measurementDTO.getSensor().getName() + " does not exist");
    }
}
