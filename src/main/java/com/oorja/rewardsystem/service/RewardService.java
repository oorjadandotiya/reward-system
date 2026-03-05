package com.oorja.rewardsystem.service;

import com.oorja.rewardsystem.dto.AnswerRequest;
import com.oorja.rewardsystem.entity.Answer;
import com.oorja.rewardsystem.entity.Customer;
import com.oorja.rewardsystem.entity.Question;
import com.oorja.rewardsystem.repository.AnswerRepository;
import com.oorja.rewardsystem.repository.CustomerRepository;
import com.oorja.rewardsystem.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 * Service class responsible for handling the business logic of the reward system.
 *
 * This service manages customer creation, answer submissions, reward point
 * calculations, question retrieval, and final score processing.
 */
@Service
public class RewardService {


    /**
     * Maximum number of points a customer can accumulate.
     */
    private static final int MAX_POINTS = 30;

    /**
     * Number of points awarded for a correct answer.
     */
    private static final int REWARD_POINTS = 10;

    /**
     * Number of points deducted for an incorrect answer.
     */
    private static final int PENALTY_POINTS = 5;

    /**
     * Repository used to perform database operations on Customer entities.
     */
    private final CustomerRepository customerRepository;

    /**
     * Repository used to perform database operations on Question entities.
     */
    private final QuestionRepository questionRepository;

    /**
     * Repository used to perform database operations on Answer entities.
     */
    private final AnswerRepository answerRepository;


    /**
     * Constructs a RewardService with the required repositories.
     *
     * @param customerRepository repository for Customer entities
     * @param questionRepository repository for Question entities
     * @param answerRepository repository for Answer entities
     */
    public RewardService(CustomerRepository customerRepository,
        QuestionRepository questionRepository,
        AnswerRepository answerRepository) {
        this.customerRepository = customerRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    /**
     * Creates a new customer and saves it to the database.
     *
     * @return the newly created Customer
     */
    public Customer createCustomer() {
        return customerRepository.save(new Customer());
    }

    /**
     * Updates the reward points of a customer based on whether
     * the submitted answer is correct or incorrect.
     *
     * Correct answers add reward points up to the maximum limit,
     * while incorrect answers deduct penalty points.
     *
     * @param customer the customer whose points are being updated
     * @param correct whether the submitted answer is correct
     */
    private void updateCustomerPoints(Customer customer, boolean correct) {

        int points = customer.getRewardPoints();

        if (correct) {
            points = Math.min(MAX_POINTS, points + REWARD_POINTS);
        } else {
            points = points - PENALTY_POINTS;
        }

        customer.setRewardPoints(points);
    }

    /**
     * Processes a customer's submitted answer.
     *
     * The method retrieves the customer and question from the database,
     * checks whether the submitted answer is correct, updates the customer's
     * reward points, and stores the answer in the database.
     *
     * This method runs within a transaction to ensure that all database
     * operations complete successfully as a single unit.
     *
     * @param customerId the ID of the customer submitting the answer
     * @param request the request containing the question ID and the submitted answer
     * @return the updated Customer after processing the answer
     */
    @Transactional
    public Customer submitAnswer(Long customerId, AnswerRequest request) {

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        Question question = questionRepository.findById(request.getQuestionId())
            .orElseThrow(() -> new RuntimeException("Question not found"));

        boolean isCorrect =
            question.getCorrectAnswer().equalsIgnoreCase(request.getAnswer());

        updateCustomerPoints(customer, isCorrect);

        Answer answer = new Answer(
            request.getAnswer(),
            isCorrect,
            customer,
            question
        );

        answerRepository.save(answer);

        return customer;
    }

    /**
     * Retrieves a random set of quiz questions.
     *
     * The method fetches all questions from the database,
     * shuffles them randomly, and returns a limited subset.
     *
     * @return a list containing up to five randomly selected questions
     */
    public List<Question> getRandomQuestions() {

        List<Question> allQuestions = questionRepository.findAll();

        Collections.shuffle(allQuestions);

        return allQuestions.stream()
            .limit(5)
            .toList();
    }

    /**
     * Retrieves all customers stored in the system.
     *
     * @return a list of all Customer entities
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Finalizes the quiz score for a customer.
     *
     * If the customer's reward points are negative, the score
     * is reset to zero before saving the final value.
     *
     * @param customerId the ID of the customer whose score is being finalized
     * @return the updated Customer with the finalized score
     */
    public Customer finalizeScore(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (customer.getRewardPoints() < 0) {
            customer.setRewardPoints(0);
        }

        return customerRepository.save(customer);
    }
}