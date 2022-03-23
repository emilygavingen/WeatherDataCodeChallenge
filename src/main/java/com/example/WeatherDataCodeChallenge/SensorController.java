package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/sensors")
@AllArgsConstructor
public class SensorController{

        private final SensorService sensorService;

        //Gets all sensor objects
        @GetMapping
        public List<Sensor>fetchAllSensors(){
                return sensorService.getAllSensors();
                }

        //Creates new sensor if sensorId number doesn't already exist or is null.
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Sensor registerNewSensor(@RequestBody Sensor sensor) {
                //If the user hasn't given a sensor ID, this will generate a key for the sensor
                if (sensor.getSensorId() == null) {
                        String newSensorId = UUID.randomUUID().toString().replace("-", "");
                        sensor.setSensorId(newSensorId);
                        return sensorService.addNewSensor(sensor);
                }
                else if (sensorService.findById(sensor.getSensorId()) == null) {
                        return sensorService.addNewSensor(sensor);
                } else {
                        throw new IllegalStateException("Sensor ID number already exists!");
                }
        }

        //POST for adding metrics values to a sensor or creating a new instance of the sensor ID
        @PostMapping(path = "/{sensorId}")
        @ResponseStatus(HttpStatus.CREATED)
        public Sensor CreateNewSensorInstance(@RequestBody Metrics metrics, @PathVariable String sensorId) {

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
                                String newMetricId = UUID.randomUUID().toString().replace("-", "");
                                sensor.setMetricId(newMetricId);
                                sensorService.addNewSensor(sensor);
                                return sensorService.addNewSensor(sensor);
                        }
                }
        }

        //Method for Query data and returning an average result
        @RequestMapping(value = "/average", method = RequestMethod.GET)
        public Averages getAllByCityNameAndTimePeriod(
                @RequestParam(required = false) String cityName,
                @RequestParam(required = false, defaultValue = "2021-03-03T14:21:28.000") String start) {
                if (cityName == null) {
                        return sensorService.findByStartTime(LocalDateTime.parse(start));
                } else {
                        return sensorService.findByCityNameAndByStartTimeIsGreaterThan(cityName, LocalDateTime.parse(start));
                }
        }
}
