package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.DashboardStats;
import com.guia.bandeiras.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class DashboardController {
    
    private final DashboardService dashboardService;
    
    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getStats() {
        log.info("GET /api/dashboard/stats");
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }
}
