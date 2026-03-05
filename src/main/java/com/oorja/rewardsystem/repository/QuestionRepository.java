package com.oorja.rewardsystem.repository;

import com.oorja.rewardsystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Question entities in the database.
 *
 * This interface extends JpaRepository, which provides built-in methods
 * for common database operations such as saving, retreving, updating,
 * and deleting Question records.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {}