package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.dto.AnswerRequest;
import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import com.oorja.rewardsystem.service.RewardService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for handling HTTP requests related to customers
 * participating in the reward quiz system.
 *
 * This controller provides endpoints for:
 * - Creating a new customer
 * - Submitting answers to quiz questions
 * - Creating questions
 * - Finalising a customer's quiz score
 * - Retrieving all customers
 */
@RestController
@RequestMapping("/customers")
public class RewardController {

    /**
     * Service used to handle business logic related to customers and rewards.
     */
    private final RewardService service;

    /**
     * Repository used to perform database operations on Question entities
     */
    private final QuestionRepository questionRepository;


    /**
     * Constructs a RewardController with the required service and repository.
     *
     * @param service the reward service used for quiz and customer operations
     * @param questionRepository the repository used to manage questions in the database
     */
    public RewardController(RewardService service, QuestionRepository questionRepository) {
        this.service = service;
        this.questionRepository = questionRepository;
    }

    /**
     * Creates a new customer in the system.
     *
     * Endpoint: POST /customers
     *
     * @return the newly created Customer
     */
    @PostMapping
    public Customer createCustomer() {
        return service.createCustomer();
    }

    /**
     * Submits an answer to a question for a specific customer.
     *
     * Endpoint: POST /customers/{customerId}/answer
     *
     * @param customerId the ID of the customer submitting the answer
     * @param request the answer request containing the question and the customers answer
     * @return the updated Customer after processing the submitted answer
     */
    @PostMapping("/{customerId}/answer")
    public Customer submitAnswer(@PathVariable Long customerId,
        @RequestBody AnswerRequest request) {
        return service.submitAnswer(customerId, request);
    }

//    @PostMapping("/questions")
//    public Question createQuestion() {
//        return questionRepository.save(
//            new Question("What is the capital of France?", "Paris")
//        );
//    }

    /**
     * Finalizes the quiz for a specific customer and calculates their final score.
     *
     * Endpoint: POST /customers/{customerId}/finish
     *
     * @param customerId the ID of the customer finishing the quiz
     * @return the updated Customer with their final score
     */
    @PostMapping("/{customerId}/finish")
    public Customer finishQuiz(@PathVariable Long customerId) {
        return service.finalizeScore(customerId);
    }

    /**
     * Retrieves all customers currently stored in the system.
     *
     * Endpoint: GET /customers
     *
     * @return a list of all Customer entities
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }
}