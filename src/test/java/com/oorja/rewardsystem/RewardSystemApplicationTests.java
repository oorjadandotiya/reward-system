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



/**
 * Integration tests for the Reward System application.
 *
 * This test class verifies the behaviour of the RewardService and
 * related database operations. It ensures that reward points are
 * correctly awarded or deducted based on submitted answers and
 * validates error handling for invalid inputs.
 */
@SpringBootTest
class RewardSystemApplicationTests {

	/**
	 * Service used to process quiz answers and manage reward points.
	 */
	@Autowired
	private RewardService rewardService;

	/**
	 * Repository used to manage Customer entities during testing.
	 */
	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Repository used to manage Question entities during testing.
	 */
	@Autowired
	private QuestionRepository questionRepository;

	/**
	 * Repository used to manage Answer entities during testing.
	 */
	@Autowired
	private AnswerRepository answerRepository;

	private Customer customer;
	private Question question;

	/**
	 * Initializes test data before each test runs.
	 *
	 * This method clears existing database records and creates
	 * a new customer and question to be used in the test cases.
	 */
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

	/**
	 * Tests that submitting a correct answer awards reward points.
	 * The expected result is an increase of 10 points.
	 */
	@Test
	void correctAnswerAddsPoints() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("Paris");

		Customer updated = rewardService.submitAnswer(customer.getId(), request);

		assertEquals(10, updated.getRewardPoints());
	}

	/**
	 * Tests that submitting an incorrect answer deducts reward points.
	 * The expected result is a deduction of 5 points.
	 */
	@Test
	void incorrectAnswerDeductsPoints() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("London");

		Customer updated = rewardService.submitAnswer(customer.getId(), request);

		assertEquals(-5, updated.getRewardPoints());
	}

	/**
	 * Tests that a customer's reward points do not exceed the
	 * defined maximum limit.
	 */
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

	/**
	 * Tests that submitting an answer with an invalid customer ID
	 * results in a RuntimeException.
	 */
	@Test
	void invalidCustomerThrowsException() {

		AnswerRequest request = new AnswerRequest();
		request.setQuestionId(question.getId());
		request.setAnswer("Paris");

		assertThrows(RuntimeException.class, () ->
			rewardService.submitAnswer(999L, request)
		);
	}

	/**
	 * Tests that submitting an answer with an invalid question ID
	 * results in a RuntimeException.
	 */
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
