package com.oorja.rewardsystem.repository;

import com.oorja.rewardsystem.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}