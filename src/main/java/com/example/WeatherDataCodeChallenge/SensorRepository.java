package com.example.WeatherDataCodeChallenge;

import org.bson.BsonNull;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface SensorRepository extends MongoRepository<Sensor, String> {

        //Tests for average
        //@Aggregation(pipeline={"{$group:{_id:"$cityName", avgMetrics:{$avg:"$metrics"}}}"})
        //@Query("SELECT AVG(u.Metrics.temperature)from Sensor u")


        //This query only displays the metrics, comment out for full sensor objects
        //@Query(fields="{'metrics':1,'metricId':0, 'localDateTime':1")
        List<Sensor> findByCityNameAndLocalDateTimeIsGreaterThan(String cityName, LocalDateTime start);

        //@Query(fields="{'metrics':1,'metricId':0, 'localDateTime':1}")
        List<Sensor> findByLocalDateTimeIsGreaterThan(LocalDateTime start);
}
