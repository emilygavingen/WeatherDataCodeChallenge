package com.example.WeatherDataCodeChallenge;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface MetricsRepository extends MongoRepository<Metrics, String> {

}
