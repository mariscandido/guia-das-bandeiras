package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.SearchResponse;
import com.guia.bandeiras.service.ExcelExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class ExportController {
    
    private final ExcelExportService excelExportService;
    
    @PostMapping("/excel")
    public ResponseEntity<byte[]> exportToExcel(@RequestBody ExportRequest request) {
        log.info("POST /api/export/excel - Query: {}", request.getQuery());
        
        byte[] excelData = excelExportService.exportSearchResultsToExcel(
            request.getQuery(), 
            request.getResults()
        );
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "resultados_busca.xlsx");
        
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }
    
    public static class ExportRequest {
        private String query;
        private List<SearchResponse.SearchResult> results;
        
        public String getQuery() { return query; }
        public void setQuery(String query) { this.query = query; }
        public List<SearchResponse.SearchResult> getResults() { return results; }
        public void setResults(List<SearchResponse.SearchResult> results) { this.results = results; }
    }
}
