package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embeddable;

@Data
@Document
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {

    private double temperature;
    private double humidity;
    private double windSpeed;

}
