package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {
    List<Sensor> findAllBySensorIdOrderByLocalDateTimeDesc(String sensorId);
}
