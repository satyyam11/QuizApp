package com.example.quiz.app.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_performance")
@Data
@NoArgsConstructor
public class QuizPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private int totalAttempts = 0;
    private int correctAnswers = 0;
    private double score = 0.0;
    
    public QuizPerformance(User user) {
        this.user = user;
    }
}
