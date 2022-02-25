package com.example.WeatherDataCodeChallenge;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Metrics {
    private double temperature;
    private double humidity;
    private double windSpeed;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime LocalDateTime;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public java.time.LocalDateTime getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(java.time.LocalDateTime localDateTime) {
        LocalDateTime = localDateTime;
    }
}
