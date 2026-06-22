package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ExcelExportService {
    
    public byte[] exportSearchResultsToExcel(String query, List<SearchResponse.SearchResult> results) {
        log.info("Exporting search results to Excel for query: {}", query);
        
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            
            Sheet sheet = workbook.createSheet("Resultados de Busca");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Bandeira", "Título", "Excerto", "Seção", "URL"};
            
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }
            
            // Create data rows
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setWrapText(true);
            
            int rowNum = 1;
            for (SearchResponse.SearchResult result : results) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0).setCellValue(result.getCardBrand());
                row.createCell(1).setCellValue(result.getTitle());
                row.createCell(2).setCellValue(result.getExcerpt());
                row.createCell(3).setCellValue(result.getSection());
                row.createCell(4).setCellValue(result.getSourceUrl());
                
                // Apply wrap text to cells
                for (int i = 0; i < 5; i++) {
                    row.getCell(i).setCellStyle(dataStyle);
                }
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Set column widths
            sheet.setColumnWidth(0, 5000);  // Bandeira
            sheet.setColumnWidth(1, 8000);  // Título
            sheet.setColumnWidth(2, 20000); // Excerto
            sheet.setColumnWidth(3, 4000);  // Seção
            sheet.setColumnWidth(4, 15000); // URL
            
            workbook.write(outputStream);
            
            log.info("Excel export completed successfully");
            return outputStream.toByteArray();
            
        } catch (IOException e) {
            log.error("Error exporting to Excel", e);
            throw new RuntimeException("Failed to export to Excel", e);
        }
    }
}
