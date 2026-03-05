package com.oorja.rewardsystem.repository;

import com.oorja.rewardsystem.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository interface for performing database operations on Answer entities.
 *
 * This interface extends JpaRepository, which provides built-in methods
 * for common CRUD operations such as saving, retrieving, updating,
 * and deleting Answer records.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {}
