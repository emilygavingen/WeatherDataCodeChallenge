package com.example.WeatherDataCodeChallenge;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Document
@Embeddable
@AllArgsConstructor
public class Metrics {

    private double temperature;
    private double humidity;
    private double windSpeed;

    public Metrics() {
    }
}
