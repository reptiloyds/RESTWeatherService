package ru.naumov.restweatherservice.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.naumov.restweatherservice.dto.SensorDTO;
import ru.naumov.restweatherservice.models.Sensor;
import ru.naumov.restweatherservice.services.SensorsService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SensorDTOValidator implements Validator {
    private final SensorsService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        Optional<Sensor> sameNameSensor = service.findByName(sensorDTO.getName());
        if (sameNameSensor.isPresent())
            errors.rejectValue(SensorDTO.Fields.name, "", "Sensor with name " + sensorDTO.getName() + " already exists");
    }
}
