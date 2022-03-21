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
public class SensorController{

        private static final Logger log = LoggerFactory.getLogger(SensorController.class);
        private final SensorService sensorService;
        private final MetricsService metricsService;

        //Gets all sensor objects
        @GetMapping
        public List<Sensor>fetchAllSensors(){
                return sensorService.getAllSensors();
                }

        //Creates new sensor if sensorId number doesn't already exist or is null.
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Sensor registerNewSensor(@RequestBody Sensor sensor) {
                if (sensor.getSensorId() == null) {
                        String newSensorId = UUID.randomUUID().toString().replace("-", "");
                        sensor.setSensorId(newSensorId);
                        return sensorService.addNewSensor(sensor);
                } else if (sensorService.findById(sensor.getSensorId()) == null) {
                        return sensorService.addNewSensor(sensor);
                } else {
                        throw new IllegalStateException("Sensor ID number already exists!");
                }
        }

        //POST for adding metrics values to a sensor
        @PostMapping(path = "/{sensorId}")
        @ResponseStatus(HttpStatus.CREATED)
        public Sensor updateSensor(@RequestBody Metrics metrics, @PathVariable String sensorId) {

                //Find sensor to insert metrics into
                Sensor sensor = sensorService.findById(sensorId);
                if (sensor == null) {
                        throw new IllegalStateException("Sensor ID has not been registered!");
                } else {
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
                                String newMetricId = UUID.randomUUID().toString().replace("-", "");
                                sensor.setMetricId(newMetricId);
                                sensorService.addNewSensor(sensor);
                                return sensorService.addNewSensor(sensor);
                        }
                }
        }

        @RequestMapping(value = "/query", method = RequestMethod.GET)
        public List<Sensor> getAllByCityNameAndTimePeriod(
                @RequestParam(required = false) String cityName,
                @RequestParam(required = false, defaultValue = "2021-03-03T14:21:28.000") String start) {
                if (cityName == null) {
                        return sensorService.findByStartTime(LocalDateTime.parse(start));
                } else {
                        return sensorService.findByCityNameAndByStartTimeIsGreaterThan(cityName, LocalDateTime.parse(start));
                }
        }

        @RequestMapping(value = "/test", method = RequestMethod.GET)
        public List<Sensor> getAllByMetrics(
                @RequestParam(required = false, defaultValue = "0") int temperature,
                @RequestParam(required = false, defaultValue = "0") int humidity,
                @RequestParam(required = false, defaultValue = "0") int windSpeed) {
                if (temperature == 1) {
                        if (humidity == 1) {
                                if (windSpeed == 1) {
                                        log.info("All metrics selected");
                                        return sensorService.getAllSensors();
                                } else {
                                        log.info("Temp and Humidity");
                                        throw new IllegalStateException("test exception");
                                }
                        } else {
                                if (windSpeed == 1) {
                                        log.info("Temp and WindSpeed");
                                } else {
                                        log.info("Temp");
                                }
                                throw new IllegalStateException("test exception");
                        }
                } else {
                        if (humidity == 1) {
                                if (windSpeed == 1) {
                                        log.info("Humidity and wind Speed");
                                } else {
                                        log.info("Humidity");
                                }
                                throw new IllegalStateException("test exception");
                        } else {
                                if (windSpeed == 1) {
                                        log.info("wind Speed");
                                        throw new IllegalStateException("test exception");
                                } else {
                                        throw new IllegalStateException("You have no metrics selected!");
                                }
                        }
                }
        }
}
