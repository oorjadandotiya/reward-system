package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.dto.AnswerRequest;
import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import com.oorja.rewardsystem.service.RewardService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class RewardController {

    private final RewardService service;
    private final QuestionRepository questionRepository;

    public RewardController(RewardService service, QuestionRepository questionRepository) {
        this.service = service;
        this.questionRepository = questionRepository;
    }

    @PostMapping
    public Customer createCustomer() {
        return service.createCustomer();
    }

    @PostMapping("/{customerId}/answer")
    public Customer submitAnswer(@PathVariable Long customerId,
        @RequestBody AnswerRequest request) {
        return service.submitAnswer(customerId, request);
    }

    @PostMapping("/questions")
    public Question createQuestion() {
        return questionRepository.save(
            new Question("What is the capital of France?", "Paris")
        );
    }


    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.getAllCustomers();
    }
}