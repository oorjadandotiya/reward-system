package com.oorja.rewardsystem.entity;

import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String submittedAnswer;

    private boolean correct;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Question question;

    public Answer() {}

    public Answer(String submittedAnswer, boolean correct,
        Customer customer, Question question) {
        this.submittedAnswer = submittedAnswer;
        this.correct = correct;
        this.customer = customer;
        this.question = question;
    }
}