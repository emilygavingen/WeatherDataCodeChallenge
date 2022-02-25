package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/sensors")
@AllArgsConstructor
public class SensorController {

    private static final Logger log = LoggerFactory.getLogger(SensorController.class);

    private final SensorService sensorService;
    private final MetricsService metricsService;

    //Gets all sensor objects
    @GetMapping
    public List<Sensor> fetchAllSensors() {
        return sensorService.getAllSensors();
    }

    // Creates new sensor if sensorId number doesn't already exist
    // Found bug, if ID = null, throws exception, need to send back a boolean when finding if ID exists
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor registerNewSensor (@RequestBody Sensor sensor){
//        if(sensorService.exists(sensor.getSensorId()))
//        {
//            log.info("Sensor ID number taken");
//            throw new IllegalStateException("Index is out of bounds");
//        }
//        else{
            return sensorService.addNewSensor(sensor);
//        }
    }

    // Need to create new instance of sensorId/cityName/countryName with updated metrics/time
    @PostMapping(path = "/{id}/readings")
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor updateSensor(@RequestBody Metrics metrics, @PathVariable String id){
        if(!sensorService.exists(id)) {
            log.info("Sensor ID number {} has not been registered", id);
            throw new IllegalStateException("Index is out of bounds");
        }

        else {
            log.info("Sensor ID is: {}", id);
            Sensor sensor = sensorService.findById(id);
            metrics.setLocalDateTime(LocalDateTime.now());
            sensor.setMetrics(metrics);
            //"sensor" object holds exactly what I want to store as a new object here (apart from MetricId)
            //Creates new metric object with new metricId
            metricsService.createNewVersion(metrics);
            log.info("Metrics {}", metrics);
            return sensorService.addNewSensor(sensor);
        }
    }

    //Search for sensor by id
    @RequestMapping(value = "/getSensorById/{id}")
    public Sensor getSensorById(@PathVariable String id) {
        return sensorService.findById(id);
    }
}