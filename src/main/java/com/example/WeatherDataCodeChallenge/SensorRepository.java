package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {
    List<Sensor> findAllBySensorIdOrderByLocalDateTimeDesc(String sensorId);

//    @Query("SELECT c.cityName, s.sensorId FROM SENSOR c JOIN c.")
//    public String getJoinInformation();
}
