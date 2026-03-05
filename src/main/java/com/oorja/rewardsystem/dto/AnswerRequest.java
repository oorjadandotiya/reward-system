package com.oorja.rewardsystem.dto;


/**
 * Data Transfer Object (DTO) used to represent a customer's answer submission.
 *
 * This class is used when a client sends an HTTP request containing
 * the ID of the question being answered and the customer's answer.
 */

public class AnswerRequest {

    /**
     * The ID of the question being answered.
     */
    private Long questionId;

    /**
     * The answer provided by the customer.
     */
    private String answer;


    /**
     * Returns the ID of the question being answered.
     *
     * @return the question ID
     */
    public Long getQuestionId() {
        return questionId;
    }

    /**
     * Returns the answer provided by the customer.
     *
     * @return the customer's answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the ID of the question being answered.
     *
     * @param questionId the question ID
     */
    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    /**
     * Sets the answer provided by the customer.
     *
     * @param answer the customer's answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}