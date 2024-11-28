package com.example.quiz.app.services;

import com.example.quiz.app.dtos.PerformanceDTO;
import com.example.quiz.app.dtos.QuestionDTO;
import com.example.quiz.app.models.Question;
import com.example.quiz.app.models.QuizPerformance;
import com.example.quiz.app.models.User;
import com.example.quiz.app.Repositories.QuestionRepository;
import com.example.quiz.app.Repositories.QuizPerformanceRepository;
import com.example.quiz.app.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuestionRepository questionRepository;
    private final QuizPerformanceRepository performanceRepository;
    private final UserRepository userRepository;

    public QuestionDTO getRandomQuestion(Long userId) {
        validateUser(userId);
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new IllegalStateException("No questions available");
        }
        Question question = questions.get(new Random().nextInt(questions.size()));
        return new QuestionDTO(question.getId(), question.getText(), question.getOptions());
    }

    public boolean validateAnswer(Long userId, Long questionId, String selectedAnswer) {
        validateUser(userId);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));

        boolean isCorrect = question.getCorrectAnswer().equalsIgnoreCase(selectedAnswer);
        updatePerformance(userId, isCorrect);
        return isCorrect;
    }

    public void endQuiz(Long userId) {
        validateUser(userId);
    }

    @Transactional
    public Question createQuestion(String question, List<String> options, String correctAnswer) {
        Question newQuestion = new Question(question, options, correctAnswer);
        return questionRepository.save(newQuestion);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public void updateQuestion(Long id, String question, List<String> options, String correctAnswer) {
        Question questionToUpdate = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));

        // Correct setter method for updating text
        questionToUpdate.setText(question);  // Use setText instead of setQuestion
        questionToUpdate.setOptions(options);
        questionToUpdate.setCorrectAnswer(correctAnswer);

        questionRepository.save(questionToUpdate);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public PerformanceDTO getUserPerformance(Long userId) {
        validateUser(userId);
        QuizPerformance performance = performanceRepository.findByUser_Id(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new PerformanceDTO(performance.getTotalAttempts(), performance.getCorrectAnswers(), performance.getScore());
    }

    private void validateUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }
    }

    private void updatePerformance(Long userId, boolean isCorrect) {
        QuizPerformance performance = performanceRepository.findByUser_Id(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId).orElseThrow();
                    return new QuizPerformance(user);
                });

        performance.setTotalAttempts(performance.getTotalAttempts() + 1);
        if (isCorrect) {
            performance.setCorrectAnswers(performance.getCorrectAnswers() + 1);
        }
        performance.setScore((double) performance.getCorrectAnswers() / performance.getTotalAttempts() * 100);
        performanceRepository.save(performance);
    }
}
