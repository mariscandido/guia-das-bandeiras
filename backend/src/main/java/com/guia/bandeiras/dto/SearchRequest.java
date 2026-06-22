package com.guia.bandeiras.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SearchRequest {
    
    @NotBlank(message = "Query cannot be blank")
    private String query;
    
    private String cardBrand; // Optional filter: visa, mastercard, amex
}
