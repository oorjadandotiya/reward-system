package com.oorja.rewardsystem.service;

import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RewardService {

    private static final int MAX_POINTS = 1000;
    private static final int REWARD_POINTS = 10;
    private static final int PENALTY_POINTS = 5;

    private final CustomerRepository repository;

    public RewardService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createCustomer() {
        return repository.save(new Customer());
    }

    @Transactional
    public Customer updatePoints(Long id, boolean correct) {

        Customer customer = repository.findById(id)
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
}