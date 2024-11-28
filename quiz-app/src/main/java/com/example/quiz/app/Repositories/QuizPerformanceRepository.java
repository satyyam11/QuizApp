package com.example.quiz.app.Repositories;

import com.example.quiz.app.models.QuizPerformance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizPerformanceRepository extends JpaRepository<QuizPerformance, Long> {
    Optional<QuizPerformance> findByUser_Id(Long userId);
}
