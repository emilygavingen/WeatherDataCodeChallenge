package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SensorRepository extends MongoRepository<Sensor, String> {
    Sensor findBySensorId(String sensorId);
}
