package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.Flashcard;
import com.guia.bandeiras.dto.QuizQuestion;
import com.guia.bandeiras.service.TrainingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/training")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class TrainingController {
    
    private final TrainingService trainingService;
    
    @GetMapping("/quiz")
    public ResponseEntity<List<QuizQuestion>> getQuizQuestions() {
        log.info("GET /api/training/quiz");
        return ResponseEntity.ok(trainingService.getQuizQuestions());
    }
    
    @GetMapping("/flashcards")
    public ResponseEntity<List<Flashcard>> getFlashcards() {
        log.info("GET /api/training/flashcards");
        return ResponseEntity.ok(trainingService.getFlashcards());
    }
}
