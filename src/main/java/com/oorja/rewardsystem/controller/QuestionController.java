package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionRepository repository;

    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return repository.findAll();
    }
}