package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.BsonNull;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embeddable;
import java.util.List;

@Data
@Document
//@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Metrics{
        private double temperature;
        private double humidity;
        private double windSpeed;
}
