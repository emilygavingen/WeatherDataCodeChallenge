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

//    public Sensor addMetrics(String id, Metrics metrics, LocalDateTime) {
//        return sensorRepository.save(id, metrics, LocalDateTime);
//    }

    public Sensor findById(String id) {
        Sensor sensor = getAllSensors().stream().filter(t -> id.equals(t.getId())).findFirst().orElse(null);
        return sensor;
    }

    public Sensor findByCityName(String cityName) {
        Sensor sensor = getAllSensors().stream().filter(t -> cityName.equals(t.getCityName())).findFirst().orElse(null);
        return sensor;
    }

    public void save(Sensor sensor) {
    }
}
