package ru.naumov.restweatherservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumov.restweatherservice.models.Measurement;
import ru.naumov.restweatherservice.repository.MeasurementsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementsRepository repository;

    @Transactional
    public void add(Measurement measurement) {
        repository.save(measurement);
    }

    public List<Measurement> findAll() {
        return repository.findAll();
    }

    public List<Measurement> findByRainingIs(boolean raining) {
        return repository.findAllByRainingIs(raining);
    }
}
