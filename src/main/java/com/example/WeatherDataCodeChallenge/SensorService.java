package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public List<Sensor> getSensorByCityName(String sensorId, String cityName) {
        //Need to check if sensorId exists, error checking needs to be done here
        Sensor sensor = findById(sensorId);

        return sensorRepository.findAll();

       //return sensorRepository.findAllBySensorIdOrderByLocalDateTimeDesc(sensorId);

//        if(cityName != null & cityName.length() > 0 && !Objects.equals(sensor.getCityName(), cityName))
//        {
//            return sensor.setCityName(cityName);
//        }
    }

//    public List<Sensor> bySensorIdDisplayTemp(String sensorId){
//        return sensorRepository.findAllBySensorId(sensorId);
//    }

//        public List<Sensor> getSumOfTemp() {
//        int sum = getAllSensors().stream().filter(o -> o.getField() > 10).mapToInt(Obj::getField).sum();
//        return sum;
//    }
}
