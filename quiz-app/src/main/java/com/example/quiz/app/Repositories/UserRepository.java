package com.example.quiz.app.Repositories;

import com.example.quiz.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
