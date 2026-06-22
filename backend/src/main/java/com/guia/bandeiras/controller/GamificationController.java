package com.guia.bandeiras.controller;

import com.guia.bandeiras.entity.User;
import com.guia.bandeiras.service.GamificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/gamification")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class GamificationController {
    
    private final GamificationService gamificationService;
    
    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderboardEntry>> getLeaderboard() {
        log.info("GET /api/gamification/leaderboard");
        
        List<User> users = gamificationService.getLeaderboard();
        
        List<LeaderboardEntry> leaderboard = users.stream()
                .map(user -> new LeaderboardEntry(
                    user.getUsername(),
                    user.getPoints(),
                    user.getRank(),
                    gamificationService.getRankTitle(user.getRank())
                ))
                .limit(10)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(leaderboard);
    }
    
    @GetMapping("/user-stats")
    public ResponseEntity<UserStats> getUserStats(Authentication authentication) {
        log.info("GET /api/gamification/user-stats - User: {}", authentication.getName());
        
        // In a real implementation, you would fetch the user from the repository
        // For now, return simulated data
        UserStats stats = new UserStats(
            authentication.getName(),
            150,
            3,
            "Prata",
            25
        );
        
        return ResponseEntity.ok(stats);
    }
    
    public record LeaderboardEntry(String username, Integer points, Integer rank, String rankTitle) {}
    public record UserStats(String username, Integer points, Integer rank, String rankTitle, Integer position) {}
}
