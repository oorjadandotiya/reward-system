package com.oorja.rewardsystem;

import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final QuestionRepository questionRepository;

    public DataLoader(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void run(String... args) {

        if (questionRepository.count() == 0) {

            questionRepository.save(new Question("Capital of France?", "Paris"));
            questionRepository.save(new Question("2 + 2?", "4"));
            questionRepository.save(new Question("Capital of Germany?", "Berlin"));
            questionRepository.save(new Question("5 x 3?", "15"));
            questionRepository.save(new Question("Sun rises from?", "East"));
        }
    }
}