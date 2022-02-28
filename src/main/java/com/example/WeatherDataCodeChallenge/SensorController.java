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

    // Creates new sensor if sensorId number doesn't already exist or is null.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor registerNewSensor (@RequestBody Sensor sensor){
        if(sensor.getSensorId() == null)
        {
            return sensorService.addNewSensor(sensor);
        }
        else if(!sensorService.exists(sensor.getSensorId()))
        {
            return sensorService.addNewSensor(sensor);
        }
        else{
            log.info("Sensor ID number taken");
            throw new IllegalStateException("Index is out of bounds");
        }
    }

    public Sensor registerNewMetrics (@RequestBody Sensor sensor){
        return sensorService.addNewSensor(sensor);
    }

    // Need to create new instance of sensorId/cityName/countryName with updated metricId/metrics/time
    @PostMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor updateSensor(@RequestBody Metrics metrics, @PathVariable String id){
        if(!sensorService.exists(id)) {
            log.info("Sensor ID number {} has not been registered", id);
            throw new IllegalStateException("Index is out of bounds");
        }

        else {
            log.info("Sensor ID is: {}", id);
            //Find sensor to insert metrics into
            Sensor sensor = sensorService.findById(id);
            //Checks to see if inital sensor object is populated, if not, it fills it
            //If metrics are empty
            if(sensor.getMetrics() == null){
                //Sets time
                metrics.setLocalDateTime(LocalDateTime.now());
                //Sets metrics
                sensor.setMetrics(metrics);
                //Creates metrics object with id, metrics and time
                metricsService.createNewVersion(metrics);
                return sensorService.addNewSensor(sensor);
            }
            //If metrics are full, create a new version with updated metrics
            else{
                metrics.setLocalDateTime(LocalDateTime.now());
                sensor.setMetrics(metrics);
                metricsService.createNewVersion(metrics);
                return sensorService.addNewSensor(sensor);
                //return sensorService.createNewVersion(sensor);
            }
        }
    }

    //Search for sensor by id
    @RequestMapping(value = "/sensorId/{id}")
    public Sensor getSensorById(@PathVariable String id) {
        return sensorService.findById(id);
    }

    //Search by metric ID
    @RequestMapping(value = "/sensorId/{sensorId}/metricId/{metricId}")
    public Sensor getMetricById(@PathVariable String sensorId, @PathVariable String metricId) {
       //sensorService.findById(sensorId);
       return sensorService.findById(metricId);
    }
}