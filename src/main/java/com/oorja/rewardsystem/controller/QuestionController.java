package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class that handles HTTP requests related to quiz questions.
 * It exposes endpoints for retrieving questions for the quiz.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {


    /**
     * Service used to handle business logic related to customers and rewards.
     */
    private final RewardService rewardService;

    /**
     * Constructs a QuestionController with the RewardService.
     *
     * @param rewardService the service responsible for question business logic
     */
    public QuestionController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    /**
     * Retrieves 5 randomly selected questions for the quiz.
     *
     * This endpoint handles HTTP GET requests to "/questions".
     *
     * @return a list of randomly selected questions
     */
    @GetMapping
    public List<Question> getRandomQuestions() {
        return rewardService.getRandomQuestions();
    }
}