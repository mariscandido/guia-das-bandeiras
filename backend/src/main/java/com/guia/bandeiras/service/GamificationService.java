package com.guia.bandeiras.service;

import com.guia.bandeiras.entity.User;
import com.guia.bandeiras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GamificationService {
    
    private final UserRepository userRepository;
    
    public void awardPoints(Long userId, Integer points) {
        log.info("Awarding {} points to user {}", points, userId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        user.setPoints(user.getPoints() + points);
        updateUserRank(user);
        
        userRepository.save(user);
        
        log.info("User {} now has {} points, rank {}", userId, user.getPoints(), user.getRank());
    }
    
    private void updateUserRank(User user) {
        Integer points = user.getPoints();
        Integer newRank;
        
        if (points >= 1000) {
            newRank = 5; // Platinum
        } else if (points >= 500) {
            newRank = 4; // Gold
        } else if (points >= 250) {
            newRank = 3; // Silver
        } else if (points >= 100) {
            newRank = 2; // Bronze
        } else {
            newRank = 1; // Novice
        }
        
        if (newRank > user.getRank()) {
            log.info("User {} ranked up from {} to {}", user.getUsername(), user.getRank(), newRank);
            user.setRank(newRank);
        }
    }
    
    public List<User> getLeaderboard() {
        log.info("Fetching leaderboard");
        return userRepository.findAllByOrderByPointsDescRankAsc();
    }
    
    public String getRankTitle(Integer rank) {
        return switch (rank) {
            case 1 -> "Novato";
            case 2 -> "Bronze";
            case 3 -> "Prata";
            case 4 -> "Ouro";
            case 5 -> "Platina";
            default -> "Novato";
        };
    }
}
