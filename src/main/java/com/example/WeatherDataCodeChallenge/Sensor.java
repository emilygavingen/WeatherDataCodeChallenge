package com.example.WeatherDataCodeChallenge;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embedded;
import javax.persistence.NamedQuery;
import java.time.LocalDateTime;

@Data
@Document
// @NamedQuery(name = "Sensor.findBySensorId", query = "SELECT s FROM Sensor s WHERE LOWER(s.last")
public class Sensor {

    private String sensorId;
    private String countryName;
    private String cityName;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private java.time.LocalDateTime LocalDateTime;
    @Id
    private String metricId;
    @Embedded
    private Metrics metrics;

}
