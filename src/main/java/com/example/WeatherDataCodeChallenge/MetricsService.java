package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MetricsService {

    private final MetricsRepository metricsRepository;

    public List<Metrics> getAllMetrics() {
        return metricsRepository.findAll();
    }
}
