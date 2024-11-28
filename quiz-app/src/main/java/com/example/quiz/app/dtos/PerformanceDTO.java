package com.example.quiz.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PerformanceDTO {
    private int totalAttempts;
    private int correctAnswers;
    private double score;
}
