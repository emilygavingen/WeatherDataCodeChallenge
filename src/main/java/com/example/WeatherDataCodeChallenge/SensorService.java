package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public List<Sensor> bySensorIdDisplayTemp(String sensorId){
        return sensorRepository.findAllBySensorIdOrderByLocalDateTimeDesc(sensorId);
    }


    //    public List<Sensor> getSumOfTemp() {
//        int sum = getAllSensors().stream().filter(o -> o.getField() > 10).mapToInt(Obj::getField).sum();
//        return sum;
//    }
}
