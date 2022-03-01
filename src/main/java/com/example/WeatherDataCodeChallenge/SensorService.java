package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorService {

    private static final Logger log = LoggerFactory.getLogger(SensorController.class);
    private final SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor addNewSensor(Sensor sensor){
        return sensorRepository.insert(sensor);
    }

    public Sensor findById(String id) {
        Sensor sensor = getAllSensors().stream().filter(t -> id.equals(t.getSensorId())).findFirst().orElse(null);
        return sensor;
    }

    public boolean exists(String sensorId) {
        return sensorRepository.existsById(sensorId);
    }

    public Sensor getBySensorId(String sensorId) {
        Sensor sensor = sensorRepository.findBySensorId(sensorId);
        if (sensor == null) {
            log.info("Sensor ID number {} has not been registered", sensorId);
            throw new IllegalStateException("Index is out of bounds");
        } else {
            return sensor;
        }
    }

    public Sensor createNewVersion (Sensor sensor){
        return sensorRepository.insert(sensor);
    }
}
