package com.oorja.rewardsystem.service;

import com.oorja.rewardsystem.dto.AnswerRequest;
import com.oorja.rewardsystem.entity.Answer;
import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.AnswerRepository;
import com.oorja.rewardsystem.repository.CustomerRepository;
import com.oorja.rewardsystem.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    private static final int MAX_POINTS = 1000;
    private static final int REWARD_POINTS = 10;
    private static final int PENALTY_POINTS = 5;

    private final CustomerRepository customerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    public RewardService(CustomerRepository customerRepository,
        QuestionRepository questionRepository,
        AnswerRepository answerRepository) {
        this.customerRepository = customerRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Customer createCustomer() {
        return customerRepository.save(new Customer());
    }

    @Transactional
    public Customer updatePoints(Long id, boolean correct) {

        Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        int points = customer.getRewardPoints();

        if (correct) {
            points = Math.min(MAX_POINTS, points + REWARD_POINTS);
        } else {
            points = Math.max(0, points - PENALTY_POINTS);
        }

        customer.setRewardPoints(points);

        return customer;
    }

    @Transactional
    public Customer submitAnswer(Long customerId, AnswerRequest request) {

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        Question question = questionRepository.findById(request.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect =
            question.getCorrectAnswer().equalsIgnoreCase(request.getAnswer());

        int points = customer.getRewardPoints();

        if (isCorrect) {
            points = Math.min(MAX_POINTS, points + REWARD_POINTS);
        } else {
            points = Math.max(0, points - PENALTY_POINTS);
        }

        customer.setRewardPoints(points);

        Answer answer = new Answer(
            request.getAnswer(),
            isCorrect,
            customer,
            question
        );

        answerRepository.save(answer);

        return customer;   // 👈 IMPORTANT
    }


}