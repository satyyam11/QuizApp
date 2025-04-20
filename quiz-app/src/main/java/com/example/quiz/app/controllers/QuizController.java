package com.example.quiz.app.controllers;

import com.example.quiz.app.dtos.AnswerSubmission;
import com.example.quiz.app.dtos.PerformanceDTO;
import com.example.quiz.app.dtos.QuestionDTO;
import com.example.quiz.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

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
    // In your QuizController class
    
    @PostMapping("/submit/{userId}")
    public ResponseEntity<Boolean> submitAnswer(
            @PathVariable Long userId,
            @RequestBody Map<String, Object> payload) {
        
        // Add logging to debug the issue
        System.out.println("Received submit request for userId: " + userId);
        System.out.println("Request payload: " + payload);
        
        // Extract questionId and answer from the payload
        Long questionId = null;
        String answer = null;
        
        try {
            // Handle different types of questionId (Integer, Long, String)
            Object questionIdObj = payload.get("questionId");
            if (questionIdObj instanceof Integer) {
                questionId = ((Integer) questionIdObj).longValue();
            } else if (questionIdObj instanceof Long) {
                questionId = (Long) questionIdObj;
            } else if (questionIdObj instanceof String) {
                questionId = Long.parseLong((String) questionIdObj);
            }
            
            answer = (String) payload.get("answer");
        } catch (Exception e) {
            System.out.println("Error parsing request payload: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
        
        // Validate required fields
        if (questionId == null || answer == null) {
            System.out.println("Missing required fields. questionId: " + questionId + ", answer: " + answer);
            return ResponseEntity.badRequest().build();
        }
        
        boolean isCorrect = quizService.validateAnswer(userId, questionId, answer);
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
