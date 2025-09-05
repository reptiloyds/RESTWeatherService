package ru.naumov.restweatherservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.restweatherservice.models.Measurement;

import java.util.List;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Long> {
    public List<Measurement> findAllByRainingIs(boolean raining);
}
