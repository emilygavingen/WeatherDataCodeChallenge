package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Embeddable;

@Data
@Document
@Embeddable
@AllArgsConstructor
public class Metrics {

    private double temperature;
    private double humidity;
    private double windSpeed;

    public Metrics() {}

    public static double getTotalTemp(double[] tempArray){
        double sum = 0;
        for (double value : tempArray) {
            sum += value;
        }
        return sum;
    }

    public static double findAverageTemp(double[] tempArray) {
        double sum = getTotalTemp(tempArray);
        return sum / tempArray.length;
    }



//    public static double getTotalHumidity(double[] humArray){
//        double sum = 0;
//        for (double value : humArray) {
//            sum += value;
//        }
//        return sum;
//    }
//
//    public static double findAverageHumidity(double[] humArray) {
//        double sum = getTotalHumidity(humArray);
//        return sum / humArray.length;
//    }
//
//    public static double getTotalWindSpeed(double[] windSpeedArray){
//        double sum = 0;
//        for (double value : windSpeedArray) {
//            sum += value;
//        }
//        return sum;
//    }
//
//    public static double findAverageWindSpeed(double[] windSpeedArray) {
//        double sum = getTotalWindSpeed(windSpeedArray);
//        return sum / windSpeedArray.length;
//    }
}
