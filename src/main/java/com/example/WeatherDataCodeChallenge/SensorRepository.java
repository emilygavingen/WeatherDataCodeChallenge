package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

    //Tests for average
//    @Aggregation(pipeline = {"{$group: {_id:"$cityName", avgMetrics:{$avg: "$metrics" }}}" })
//    @Aggregation(pipeline = {$group: {_id:"$countryName"}})

    //@Aggregation(pipeline = "{'cityName': $cityName}, {'avgMetrics': {$avg: $metrics}}")
    List<Sensor> findByCityNameAndLocalDateTimeIsGreaterThan(String cityName, LocalDateTime start);

    @Query("SELECT AVG(u.Metrics.temperature) from Sensor u")
    double getAverageMetrics();


    db.event.aggregate([     { $unwind: "$participantList" },
    { $group: { _id: "$_id", value: { $max: "$participantList.attendDataFrom" } } } ]);

}
