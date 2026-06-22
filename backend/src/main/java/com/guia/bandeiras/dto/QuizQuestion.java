package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestion {
    private String question;
    private String[] options;
    private Integer correctAnswer;
    private String explanation;
}
