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

    public Sensor addNewSensor(Sensor sensor){
        return sensorRepository.save(sensor);
    }

    public Sensor findById(String id) {
        Sensor sensor = getAllSensors().stream().filter(t -> id.equals(t.getSensorId())).findFirst().orElse(null);
        return sensor;
    }

    //Think this is doing the same as AddNewSensor -- check
    public Sensor createNewVersion (Sensor sensor){
        return sensorRepository.insert(sensor);
    }

    public List<Sensor> findByCityNameAndByStartTimeIsGreaterThan(String cityName, LocalDateTime start) {
        return sensorRepository.findByCityNameAndLocalDateTimeIsGreaterThan(cityName, start);
    }

    public double getAverageMetrics() {
        return sensorRepository.getAverageMetrics();
    }
}