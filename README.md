# Reward Quiz System

## Overview

The Reward Quiz System is a Spring Boot application that allows customers to answer quiz questions and earn reward points based on their answers.

Customers receive points for correct answers and lose points for incorrect answers. The system also enforces a maximum limit on the number of points a customer can earn.

The application exposes REST APIs and includes a simple web interface for interacting with the quiz.

---

## Features

- Customers can be created and selected from a list
- Customers answer quiz questions through a simple web interface
- Each correct answer awards **10 points**
- Each incorrect answer deducts **5 points**
- Maximum reward points per customer is **30**
- A random set of **5 questions** is selected from a pool of **15 questions**
- Answers submitted by customers are stored in the database
- Customer scores are updated dynamically based on quiz performance

---

## Technologies Used

- Java 21
- Spring Boot
- Spring Data JPA
- H2 In-Memory Database
- Maven
- HTML / JavaScript (simple frontend)

---

## Project Structure

src/main/java/com/oorja/rewardsystem
├── controller # REST API controllers
├── service # Business logic
├── repository # Database access layer
├── entity # JPA entities
├── dto # Data transfer objects
└── RewardSystemApplication

## Requirements

To run this application you will need:

- **Java 21**
- **Maven** (or use the Maven wrapper included in the project)

## Running the Application

### 1. Clone the Repository

git clone https://github.com/oorjadandotiya/reward-system.git

### 2. Run the Application

Using Maven wrapper:

Windows:

mvnw.cmd spring-boot:run

Mac / Linux:

./mvnw spring-boot:run

Alternatively, you can run the application directly from your IDE by running:

RewardSystemApplication.java

### 3. Access the Application

Once the server starts, open a browser and navigate to:

http://localhost:8080


## Author

Oorja Dandotiya


