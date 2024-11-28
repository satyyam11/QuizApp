package com.example.quiz.app.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private int totalAttempts;
    private int correctAnswers;
    private double score;

    // Custom constructor for initializing with a user
    public QuizPerformance(User user) {
        this.user = user;
        this.totalAttempts = 0;
        this.correctAnswers = 0;
        this.score = 0.0;
    }

    // Method to update the score based on the correct answers and total attempts
    public void updateScore() {
        if (totalAttempts > 0) {
            this.score = (double) correctAnswers / totalAttempts * 100;
        }
    }
}
