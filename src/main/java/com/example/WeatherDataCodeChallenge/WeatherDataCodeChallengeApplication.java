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
		};
	}
}
