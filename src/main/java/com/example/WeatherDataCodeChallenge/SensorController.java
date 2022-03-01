package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
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
            return sensorService.addNewSensor(sensor);
        } else if (!sensorService.exists(sensor.getSensorId())) {
            return sensorService.addNewSensor(sensor);
        } else {
            log.info("Sensor ID number taken");
            throw new IllegalStateException("Index is out of bounds");
        }
    }

    // Need to create new instance of sensorId/cityName/countryName with updated metricId/metrics/time
    @PostMapping(path = "/{sensorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor updateSensor(@RequestBody Metrics metrics, @PathVariable String sensorId) {

            sensorService.getBySensorId(sensorId);
            log.info("Sensor ID is: {}", sensorId);
            //Find sensor to insert metrics into
            Sensor sensor = sensorService.findById(sensorId);
            //Checks to see if initial sensor object is populated, if not, it fills it
            if (sensor.getMetrics() == null) {
                metrics.setLocalDateTime(LocalDateTime.now());
                sensor.setMetrics(metrics);
//                String id = UUID.randomUUID().toString();
//                sensor.setMetricId(id);
                return sensorService.addNewSensor(sensor);
            }
            //If metrics are full, create a new version with updated metrics
            else {
                metrics.setLocalDateTime(LocalDateTime.now());
                sensor.setMetrics(metrics);
                String id = UUID.randomUUID().toString();
                sensor.setMetricId(id);
                sensorService.createNewVersion(sensor);
                return sensorService.addNewSensor(sensor);
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

    public class Generator {
        public String generateRandomPassword(int len) {
            String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                    +"lmnopqrstuvwxyz";
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; i++)
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
            return sb.toString();
        }
    }
}