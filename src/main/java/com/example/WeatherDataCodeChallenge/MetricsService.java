package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MetricsService {

    private final MetricsRepository metricsRepository;

    //Maybe problem here?
    public Metrics createNewVersion (Metrics sensor){
        return metricsRepository.save(sensor);
    }
}
