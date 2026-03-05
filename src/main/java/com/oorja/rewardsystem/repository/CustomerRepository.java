package com.oorja.rewardsystem.repository;

import com.oorja.rewardsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Customer entities in the database.
 *
 * This interface extends JpaRepository, which provides built-in methods
 * for common database operations such as saving, retrieving, updating,
 * and deleting Customer records.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}