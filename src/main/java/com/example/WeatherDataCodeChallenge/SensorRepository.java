package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

        List<Sensor> findByCityNameAndLocalDateTimeIsGreaterThan(String cityName, LocalDateTime start);

        List<Sensor> findByLocalDateTimeIsGreaterThan(LocalDateTime start);
}
