package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.GlossaryTerm;
import com.guia.bandeiras.service.GlossaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/glossary")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class GlossaryController {
    
    private final GlossaryService glossaryService;
    
    @GetMapping
    public ResponseEntity<List<GlossaryTerm>> getAllTerms() {
        log.info("GET /api/glossary");
        return ResponseEntity.ok(glossaryService.getAllTerms());
    }
    
    @GetMapping("/letter/{letter}")
    public ResponseEntity<List<GlossaryTerm>> getTermsByLetter(@PathVariable String letter) {
        log.info("GET /api/glossary/letter/{}", letter);
        return ResponseEntity.ok(glossaryService.getTermsByLetter(letter));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<GlossaryTerm>> searchTerms(@RequestParam String query) {
        log.info("GET /api/glossary/search?query={}", query);
        return ResponseEntity.ok(glossaryService.searchTerms(query));
    }
}
