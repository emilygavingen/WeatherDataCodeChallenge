package com.example.WeatherDataCodeChallenge;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embedded;

@Data
@Document
public class Sensor {

    private String sensorId;
    private String countryName;
    private String cityName;
    @Id
    private String metricId;
    @Embedded
    private Metrics metrics;
}
