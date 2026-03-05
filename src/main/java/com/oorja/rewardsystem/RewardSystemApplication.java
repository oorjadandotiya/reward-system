package com.oorja.rewardsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Reward System Spring Boot application.
 *
 * This class bootstraps the application using Spring Boot.
 * The @SpringBootApplication annotation enables auto-configuration,
 * component scanning, and configuration support.
 */
@SpringBootApplication
public class RewardSystemApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * This method launches the embedded server and initializes
	 * the Spring application context.
	 *
	 * @param args command-line arguments passed when the application starts
	 */
	public static void main(String[] args) {
		SpringApplication.run(RewardSystemApplication.class, args);
	}

}
