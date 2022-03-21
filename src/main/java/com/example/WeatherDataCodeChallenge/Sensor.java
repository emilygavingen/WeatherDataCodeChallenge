package com.example.WeatherDataCodeChallenge;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embedded;

@Data
@Document
public class Sensor{
        private String sensorId;
        private String countryName;
        private String cityName;
        @JsonDeserialize(using=LocalDateTimeDeserializer.class)
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private java.time.LocalDateTime localDateTime;
        @Id
        private String metricId;
        //@Embedded
        private Metrics metrics;
}
