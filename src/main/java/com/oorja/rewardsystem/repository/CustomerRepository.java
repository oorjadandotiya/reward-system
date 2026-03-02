package com.oorja.rewardsystem.repository;

import com.oorja.rewardsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}