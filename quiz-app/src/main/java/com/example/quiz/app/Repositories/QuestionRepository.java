package com.example.quiz.app.Repositories;

import com.example.quiz.app.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
