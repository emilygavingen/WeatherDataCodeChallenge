package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SensorService {

        private final SensorRepository sensorRepository;

        public List<Sensor> getAllSensors() {
                return sensorRepository.findAll();
        }

        public Sensor addNewSensor(Sensor sensor) {
                return sensorRepository.save(sensor);
        }

        public Sensor findById(String id) {
                Sensor sensor = getAllSensors().stream().filter(t -> id.equals(t.getSensorId())).findFirst().orElse(null);
                return sensor;
        }

        public Averages findByCityNameAndByStartTimeIsGreaterThan(String cityName, LocalDateTime start) {

                List<Sensor> sensors = sensorRepository.findByCityNameAndLocalDateTimeIsGreaterThan(cityName, start);
                double totalTemp = 0;
                double totalHumidity = 0;
                double totalWindSpeed = 0;

                for(Sensor sensor : sensors){
                        totalTemp += sensor.getMetrics().getTemperature();
                        totalHumidity += sensor.getMetrics().getHumidity();
                        totalWindSpeed += sensor.getMetrics().getWindSpeed();
                }
                double avgTemp = totalTemp / sensors.size();
                double avgHumidity = totalHumidity / sensors.size();
                double avgWindSpeed = totalWindSpeed / sensors.size();

                sensorRepository.findByCityNameAndLocalDateTimeIsGreaterThan(cityName, start);

                return getAverages(avgHumidity, avgTemp, avgWindSpeed);
        }

        public Averages findByStartTime(LocalDateTime start) {

                List<Sensor> sensors = sensorRepository.findByLocalDateTimeIsGreaterThan(start);
                double totalTemp = 0;
                double totalHumidity = 0;
                double totalWindSpeed = 0;

                for(Sensor sensor : sensors){
                        totalTemp += sensor.getMetrics().getTemperature();
                        totalHumidity += sensor.getMetrics().getHumidity();
                        totalWindSpeed += sensor.getMetrics().getWindSpeed();
                }
                double avgTemp = totalTemp / sensors.size();
                double avgHumidity = totalHumidity / sensors.size();
                double avgWindSpeed = totalWindSpeed / sensors.size();

                sensorRepository.findByLocalDateTimeIsGreaterThan(start);

                return getAverages(avgHumidity, avgTemp, avgWindSpeed);
        }

        public Averages getAverages(double avgHumidity, double avgTemp, double avgWindSpeed) {

                Averages averages = new Averages();

                averages.setAvgHumidity(avgHumidity);
                averages.setAvgTemp(avgTemp);
                averages.setAvgWindSpeed(avgWindSpeed);

                return averages;
        }
}
