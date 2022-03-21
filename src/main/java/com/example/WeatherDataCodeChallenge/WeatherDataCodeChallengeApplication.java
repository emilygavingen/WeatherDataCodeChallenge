package com.example.WeatherDataCodeChallenge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class WeatherDataCodeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherDataCodeChallengeApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(SensorRepository repository, MongoTemplate mongoTemplate){
		return args -> {};
	}
}
