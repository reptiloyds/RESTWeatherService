package ru.naumov.restweatherservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "measurements")
@NoArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    @DecimalMin(value = "-100.0", message = "Value should be greater or equal than -100.0")
    @DecimalMax(value = "100.0", message = "Value should be less or equal than 100.0")
    @NotNull
    private Float value;

    @Column(name = "raining")
    @NotNull
    private Boolean raining;

    @ManyToOne()
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
}
