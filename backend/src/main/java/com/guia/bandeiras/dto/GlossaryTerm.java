package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlossaryTerm {
    private String term;
    private String definition;
    private String category;
    private String[] relatedTerms;
}
