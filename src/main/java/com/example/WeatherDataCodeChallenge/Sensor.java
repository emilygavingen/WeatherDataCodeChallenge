package com.example.WeatherDataCodeChallenge;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.Transient;
import java.time.LocalDateTime;

@Data
@Document
public class Sensor {

    @Id
    private String id;
    private String countryName;
    private String cityName;
    private Metrics metrics;

    public Sensor(String countryName, String cityName, Metrics metrics) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.metrics = metrics;
    }
}
