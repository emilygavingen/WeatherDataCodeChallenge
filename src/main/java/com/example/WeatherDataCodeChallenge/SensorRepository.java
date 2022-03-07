package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    public double avg();

    List<Sensor> findAllBySensorIdOrderByLocalDateTimeDesc(String sensorId);

    //Case sensitive (limitation)
    @Aggregation(pipeline = {"{$group: { sensorId: '', total: {$avg: $metrics }}}" })
    public double avg(double minQuantities);
    List<Sensor> findByCityName(String cityName);

    @Query (value = "{'localDateTime': {$gte: ?0}}")
    List<Sensor> findByLocalDateTimeIsGreaterThan(LocalDateTime start);

    //Mongo Documentation goes here
}
