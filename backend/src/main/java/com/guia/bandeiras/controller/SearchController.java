package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.SearchRequest;
import com.guia.bandeiras.dto.SearchResponse;
import com.guia.bandeiras.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "${cors.allowed-origins}")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    @Cacheable(value = "searchResults", key = "#query + '-' + (#cardBrand != null ? #cardBrand : 'all')")
    public ResponseEntity<SearchResponse> search(
            @RequestParam String query,
            @RequestParam(required = false) String cardBrand) {
        
        log.info("Search request received - query: {}, cardBrand: {}", query, cardBrand);
        
        SearchRequest request = new SearchRequest();
        request.setQuery(query);
        request.setCardBrand(cardBrand);
        
        SearchResponse response = searchService.search(request);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/faq")
    public ResponseEntity<?> getFaq() {
        log.info("FAQ request received");
        return ResponseEntity.ok(searchService.getFaq());
    }
}
