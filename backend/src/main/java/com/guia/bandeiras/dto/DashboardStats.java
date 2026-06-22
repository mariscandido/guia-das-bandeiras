package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStats {
    private Long totalSearches;
    private Long uniqueUsers;
    private Double avgResponseTime;
    private Map<String, Long> searchesByBrand;
    private Map<String, Long> topSearches;
    private Map<String, Long> searchTrends;
}
