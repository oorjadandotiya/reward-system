package com.oorja.rewardsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupMessage implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("\n--------------------------------");
        System.out.println(" Reward Quiz Application Started");
        System.out.println(" Open the application by clicking on:");
        System.out.println(" http://localhost:8080");
    }
}