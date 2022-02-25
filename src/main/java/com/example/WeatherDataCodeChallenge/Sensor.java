package com.example.WeatherDataCodeChallenge;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;

@Data
@Document
public class Sensor {

    @Id
    private String sensorId;
    private String countryName;
    private String cityName;
    @Embedded
    private Metrics metrics;

    public Sensor(String countryName, String cityName, Metrics metrics) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.metrics = metrics;
    }
}
