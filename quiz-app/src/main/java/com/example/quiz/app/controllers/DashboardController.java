package com.example.quiz.app.controllers;

import com.example.quiz.app.dtos.PerformanceDTO;
import com.example.quiz.app.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final QuizService quizService;

    // Get user performance data
    @GetMapping("/{userId}")
    public ResponseEntity<PerformanceDTO> getUserPerformance(@PathVariable Long userId) {
        PerformanceDTO performance = quizService.getUserPerformance(userId);
        return ResponseEntity.ok(performance);
    }
}
