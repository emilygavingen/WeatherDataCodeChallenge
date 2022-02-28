package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    //Dub key error
    public Sensor addNewSensor(Sensor sensor){
        return sensorRepository.insert(sensor);
    }

    public Sensor findById(String id) {
        Sensor sensor = getAllSensors().stream().filter(t -> id.equals(t.getSensorId())).findFirst().orElse(null);
        return sensor;
    }

    public boolean exists(String id) {
        return sensorRepository.existsById(id);
    }

    //
//    public Sensor createNewVersion(Sensor sensor) {
//        return sensorRepository.
//    }
}
