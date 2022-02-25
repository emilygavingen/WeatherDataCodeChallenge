package com.example.WeatherDataCodeChallenge;

import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class WeatherDataCodeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataCodeChallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SensorRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			LocalDateTime DateTime = LocalDateTime.now();
			Metrics metrics = new Metrics(
					25,
					70,
					20,
					DateTime
			);
			String cityName = "Cork";
			String countryName = "Ireland";
			Sensor sensor = new Sensor(
					countryName,
					cityName,
					metrics
			);

//			Query queryOne = new Query();
//			queryOne.addCriteria(Criteria.where("id").is(Id));
//			List<Sensor> sensors = mongoTemplate.find(queryOne, Sensor.class);
//
//			if(sensors.size() > 1)
//			{
//				throw new IllegalStateException("Found many sensors with city name " + cityName);
//			}
//			if(sensors.isEmpty())
//			{
//				System.out.println("Inserting new sensor data." + sensor);
//				repository.insert(sensor);
//			}
//			else
//			{
//				System.out.println("city name or ID number incorrect.");
//			}
		};
	}
}
