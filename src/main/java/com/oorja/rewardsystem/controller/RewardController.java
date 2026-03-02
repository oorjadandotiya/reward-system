package com.oorja.rewardsystem.controller;

import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.service.RewardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class RewardController {

    private final RewardService service;

    public RewardController(RewardService service) {
        this.service = service;
    }

    @PostMapping
    public Customer createCustomer() {
        return service.createCustomer();
    }

    @PostMapping("/{id}/activity")
    public Customer updatePoints(@PathVariable Long id,
        @RequestParam boolean correct) {
        return service.updatePoints(id, correct);
    }
}