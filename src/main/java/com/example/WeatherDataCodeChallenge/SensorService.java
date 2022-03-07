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

    public Sensor createNewVersion (Sensor sensor){
        return sensorRepository.insert(sensor);
    }

    public List<Sensor> bySensorId(String sensorId){
        return sensorRepository.findAllBySensorIdOrderByLocalDateTimeDesc(sensorId);
    }

    public List<Sensor> findAllByCityName(String cityName) {
        //Filter by cityName
        return sensorRepository.findByCityName(cityName);
    }

    public List<Sensor> findAllByTimeGreaterThan(LocalDateTime start) {
        return sensorRepository.findByLocalDateTimeIsGreaterThan(start);
    }

    public double avg() {
        return sensorRepository.avg();
    }

}