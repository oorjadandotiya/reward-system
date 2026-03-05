package com.oorja.rewardsystem;

import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component responsible for loading initial data into the database
 * when the application starts.
 *
 * This class implements CommandLineRunner, which allows code to run
 * automatically after the Spring Boot application has finished starting.
 * It checks whether any questions exist in the database and inserts
 * a predefined set of quiz questions if the database is empty.
 */
@Component
public class DataLoader implements CommandLineRunner {

    /**
     * Repository used to perform database operations on Question entities.
     */
    private final QuestionRepository questionRepository;

    /**
     * Constructs a DataLoader with the required QuestionRepository.
     *
     * @param questionRepository repository used to store and retrieve questions
     */
    public DataLoader(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Method executed automatically when the application starts.
     *
     * If no questions are present in the database, this method populates
     * the database with a predefined list of quiz questions and answers.
     *
     * @param args command-line arguments passed to the application
     */
    @Override
    public void run(String... args) {

        if (questionRepository.count() == 0) {

            questionRepository.save(new Question("What is the capital of France?", "Paris"));
            questionRepository.save(new Question("What is the answer to the arithmetic equation: 2 + 2? (Input a number)", "4"));
            questionRepository.save(new Question("What is the capital of Germany?", "Berlin"));
            questionRepository.save(new Question("What is the answer to the arithmetic equation: 5 x 3? (Input a number)", "15"));
            questionRepository.save(new Question("What direction does the sun rise from?", "East"));
            questionRepository.save(new Question("What is the capital of the United Kingdom?", "London"));
            questionRepository.save(new Question("How many days are there in a leap year? (Input a number)", "366"));
            questionRepository.save(new Question("How many sides does a hexagon have? (Input a number)", "6"));
            questionRepository.save(new Question("What is the smallest prime number? (Input a number)", "2"));
            questionRepository.save(new Question("What direction does the sun set?", "West"));
            questionRepository.save(new Question("Which planet is known as the Red Planet?", "Mars"));
            questionRepository.save(new Question("How many continents are there on Earth? (Input a number)", "7"));
            questionRepository.save(new Question("Which organ pumps blood throughout the human body?", "Heart"));
            questionRepository.save(new Question("Which country is famous for the Eiffel Tower?", "France"));
            questionRepository.save(new Question("How many hours are there in a day? (Input a number)", "24"));
        }


    }
}