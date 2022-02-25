package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetricsService {

    @Autowired
    private final MetricsRepository metricsRepository;

    public Metrics createNewVersion (Metrics sensor){
        return metricsRepository.insert(sensor);
    }
}
