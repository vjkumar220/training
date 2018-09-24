package com.oodles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TrainningProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainningProjectApplication.class, args);
	}
}
