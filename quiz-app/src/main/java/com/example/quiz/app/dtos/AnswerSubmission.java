package com.example.quiz.app.dtos;

import lombok.Data;

@Data
public class AnswerSubmission {
    private Long questionId;
    private String answer;
}
