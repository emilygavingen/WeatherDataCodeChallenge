package com.example.WeatherDataCodeChallenge;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sensors")
@AllArgsConstructor
public class SensorController {

    private static final Logger log = LoggerFactory.getLogger(SensorController.class);

    private final SensorService sensorService;

    @GetMapping
    public List<Sensor> fetchAllSensors() {
        return sensorService.getAllSensors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sensor registerNewSensor (@RequestBody Sensor sensor){
        return sensorService.addNewSensor(sensor);
    }


    //Need to add the time to this POST method (i.e. When metrics are added, time is added with them)
    @PostMapping(path = "/{id}/readings")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateSensor(@RequestBody Metrics metrics, @PathVariable String id){
        if(id.isEmpty() ) {
            log.info("Sensor ID number {} does not exist.", id);
            throw new IllegalStateException("Index is out of bounds");
        }

        else {
            log.info("Sensor ID is: {}", id);
            log.info("Metrics {}", metrics);
            Sensor sensor = sensorService.findById(id);
            sensor.setMetrics(metrics);
            //sensor.setCreatedTime(LocalDateTime.now());
            sensorService.save(sensor);
        }
    }

    //Search for sensor by id
    @RequestMapping(value = "/getSensorById/{id}")
    public Sensor getSensorById(@PathVariable String id) {
        return sensorService.findById(id);
    }

    @RequestMapping(value = "/getSensorByCityName/{cityName}")
    public Sensor getSensorByCityName(@PathVariable String cityName) {
        return sensorService.findByCityName(cityName);
    }
}