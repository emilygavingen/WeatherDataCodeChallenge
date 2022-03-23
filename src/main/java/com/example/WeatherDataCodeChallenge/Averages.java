package com.example.WeatherDataCodeChallenge;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Averages {
    double avgTemp;
    double avgHumidity;
    double avgWindSpeed;
}
