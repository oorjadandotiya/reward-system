package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class that handles HTTP requests related to questions.
 * It provides endpoints for retrieving question data from the database.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {

    /**
     * Repository used to interact with the Question table in the database.
     */
    private final QuestionRepository repository;

    /**
     * Constructs a QuestionController with the givem QuestionRepository.
     *
     * @param repository the repository used to perform database operations on Question entities
     */
    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all questions stored in the database.
     *
     * It handles HTTP GET requests sent to the "/questions" endpoint
     * and then it returns a list of all Question objects.
     *
     * @return a list containing all questions
     */
    @GetMapping
    public List<Question> getAllQuestions() {
        return repository.findAll();
    }
}