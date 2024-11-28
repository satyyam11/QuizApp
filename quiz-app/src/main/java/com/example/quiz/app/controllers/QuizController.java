package com.example.quiz.app.controllers;

import com.example.quiz.app.dtos.AnswerSubmission;
import com.example.quiz.app.dtos.PerformanceDTO;
import com.example.quiz.app.dtos.QuestionDTO;
import com.example.quiz.app.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // Get a random question
    @PostMapping("/take/{userId}")
    public ResponseEntity<QuestionDTO> getRandomQuestion(@PathVariable Long userId) {
        QuestionDTO questionDTO = quizService.getRandomQuestion(userId);
        return ResponseEntity.ok(questionDTO);
    }

    // Submit an answer and validate it
    @PostMapping("/submit/{userId}")
    public ResponseEntity<?> submitAnswer(@PathVariable Long userId, @RequestBody AnswerSubmission answerSubmission) {
        boolean isCorrect = quizService.validateAnswer(userId, answerSubmission.getQuestionId(), answerSubmission.getAnswer());
        return ResponseEntity.ok(isCorrect);
    }

    // End the quiz and show performance
    @PostMapping("/end/{userId}")
    public ResponseEntity<?> endQuiz(@PathVariable Long userId) {
        quizService.endQuiz(userId);
        PerformanceDTO performance = quizService.getUserPerformance(userId);
        return ResponseEntity.ok(performance);
    }
}
