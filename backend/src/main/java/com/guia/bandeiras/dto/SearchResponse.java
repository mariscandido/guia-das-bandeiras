package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    
    private String query;
    private List<SearchResult> results;
    private int totalResults;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchResult {
        private String cardBrand;
        private String title;
        private String excerpt;
        private String sourceUrl;
        private String section;
    }
}
