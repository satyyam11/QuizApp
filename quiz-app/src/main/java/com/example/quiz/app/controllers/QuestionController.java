package com.example.quiz.app.controllers;

import com.example.quiz.app.models.Question;
import com.example.quiz.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuizService quizService;

    // Create a new question
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question newQuestion) {
        Question createdQuestion = quizService.createQuestion(newQuestion.getText(), newQuestion.getOptions(), newQuestion.getCorrectAnswer());
        return ResponseEntity.ok(createdQuestion);
    }

    // Get all questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = quizService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Get a specific question by ID
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return quizService.getQuestionById(id)
                .map(question -> ResponseEntity.ok(question))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing question
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        quizService.updateQuestion(id, updatedQuestion.getText(), updatedQuestion.getOptions(), updatedQuestion.getCorrectAnswer());
        return ResponseEntity.ok(updatedQuestion);
    }

    // Delete a question
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        quizService.deleteQuestion(id);
        return ResponseEntity.ok("Question deleted successfully");
    }
}
