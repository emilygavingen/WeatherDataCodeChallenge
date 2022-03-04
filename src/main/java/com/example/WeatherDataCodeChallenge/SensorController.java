package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/sensors")
@AllArgsConstructor
public class SensorController {

    private static final Logger log = LoggerFactory.getLogger(SensorController.class);
    private final SensorService sensorService;

    //Gets all sensor objects
    @GetMapping
    public List<Sensor> fetchAllSensors() {
        return sensorService.getAllSensors();
    }

    // Creates new sensor if sensorId number doesn't already exist or is null.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor registerNewSensor(@RequestBody Sensor sensor) {
        if (sensor.getSensorId() == null) {
            String newSensorId = UUID.randomUUID().toString();
            sensor.setSensorId(newSensorId);
            return sensorService.addNewSensor(sensor);
        }
        else if(sensorService.findById(sensor.getSensorId()) == null){
            return sensorService.addNewSensor(sensor);
        }
        else {
            log.info("Sensor ID number taken");
            throw new IllegalStateException("Index is out of bounds");
        }
    }

    //Used when adding metrics values to a sensor
    @PostMapping(path = "/{sensorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor updateSensor(@RequestBody Metrics metrics, @PathVariable String sensorId) {

            //Find sensor to insert metrics into
            Sensor sensor = sensorService.findById(sensorId);
            if (sensor == null) {
                log.info("Sensor ID number {} has not been registered", sensorId);
                throw new IllegalStateException("Index is out of bounds");
            }
            else {
                //Checks to see if initial sensor object is populated, if not, it fills it
                if (sensor.getMetrics() == null) {
                    sensor.setLocalDateTime(LocalDateTime.now());
                    sensor.setMetrics(metrics);
                    return sensorService.addNewSensor(sensor);
                }
                //If metrics are full, create a new version with updated metrics
                else {
                    sensor.setLocalDateTime(LocalDateTime.now());
                    sensor.setMetrics(metrics);
                    //creates new random metricId value
                    String newMetricId = UUID.randomUUID().toString();
                    sensor.setMetricId(newMetricId);
                    sensorService.createNewVersion(sensor);
                    return sensorService.addNewSensor(sensor);
                }
        }
    }

    @RequestMapping("/bySensorId/{sensorId}")
    public List<Sensor> bySensorId(@PathVariable(value = "sensorId") String sensorId){
        log.info("{}", sensorService.bySensorId(sensorId)); //Returns all objects
        log.info("{}", sensorService.bySensorId(sensorId).stream().count()); //Returns how many objects there are
        log.info("{}", sensorService.bySensorId(sensorId));
        return sensorService.bySensorId(sensorId);
    }

    //Need to query
    //Where
    //What metrics (start with them all)

    @RequestMapping("/bySensorId/{sensorId}/average")
    public List<Sensor> bySensorIdAverage(
            @PathVariable ("sensorId") String sensorId,
            @RequestParam (required = false) String cityName
            //@RequestParam (required = false) String
            ){
        return sensorService.getSensorByCityName(sensorId, cityName);
    }
}