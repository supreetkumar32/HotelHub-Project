package com.hotelbooking.projects.HotelHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HotelHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelHubApplication.class, args);
	}

}
