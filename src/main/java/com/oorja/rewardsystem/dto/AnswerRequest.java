package com.oorja.rewardsystem.dto;

public class AnswerRequest {
    private Long questionId;
    private String answer;

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}