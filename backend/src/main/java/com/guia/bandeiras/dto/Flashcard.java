package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {
    private String front;
    private String back;
    private String category;
}
