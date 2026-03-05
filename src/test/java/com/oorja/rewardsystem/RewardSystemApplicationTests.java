package com.oorja.rewardsystem;

import com.oorja.rewardsystem.dto.AnswerRequest;
import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.AnswerRepository;
import com.oorja.rewardsystem.repository.CustomerRepository;
import com.oorja.rewardsystem.repository.QuestionRepository;
import com.oorja.rewardsystem.service.RewardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RewardSystemApplicationTests {

	@Autowired
	private RewardService rewardService;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	private Customer customer;
	private Question question;

	@BeforeEach
	void setup() {
		answerRepository.deleteAll();
		customerRepository.deleteAll();
		questionRepository.deleteAll();

		customer = customerRepository.save(new Customer());

		question = new Question();
		question.setQuestionText("Capital of France?");
		question.setCorrectAnswer("Paris");

		question = questionRepository.save(question);
	}

	@Test
	void correctAnswerAddsPoints() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("Paris");

		Customer updated = rewardService.submitAnswer(customer.getId(), request);

		assertEquals(10, updated.getRewardPoints());
	}

	@Test
	void incorrectAnswerDeductsPoints() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("London");

		Customer updated = rewardService.submitAnswer(customer.getId(), request);

		assertEquals(-5, updated.getRewardPoints());
	}

	@Test
	void pointsShouldNotExceedMaximum() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("Paris");

		// submit correct answer multiple times
		for(int i = 0; i < 10; i++) {
			rewardService.submitAnswer(customer.getId(), request);
		}

		Customer updated = customerRepository.findById(customer.getId()).get();

		assertTrue(updated.getRewardPoints() <= 30);
	}

	@Test
	void invalidCustomerThrowsException() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("Paris");

		assertThrows(RuntimeException.class, () ->
			rewardService.submitAnswer(999L, request)
		);
	}

	@Test
	void invalidQuestionThrowsException() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(999L);
		request.setAnswer("Paris");

		assertThrows(RuntimeException.class, () ->
			rewardService.submitAnswer(customer.getId(), request)
		);
	}
}
