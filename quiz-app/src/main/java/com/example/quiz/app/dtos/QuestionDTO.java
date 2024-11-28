package com.example.quiz.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String text;
    private List<String> options;
}
