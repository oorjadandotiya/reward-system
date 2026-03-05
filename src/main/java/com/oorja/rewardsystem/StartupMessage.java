package com.oorja.rewardsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component that prints a startup message when the application begins running.
 *
 * This class implements CommandLineRunner, allowing code to execute
 * automatically after the Spring Boot application has started.
 * It displays a message in the console indicating that the application
 * is running and provides the local URL where it can be accessed.
 */
@Component
public class StartupMessage implements CommandLineRunner {

    /**
     * Method executed automatically after the application starts.
     * It prints a message in the console with the application status
     * and the URL for accessing the application.
     *
     * @param args command-line arguments passed to the application
     */
    @Override
    public void run(String... args) {
        System.out.println("\n--------------------------------");
        System.out.println(" Reward Quiz Application Started");
        System.out.println(" Open the application by clicking on:");
        System.out.println(" http://localhost:8080");
    }
}