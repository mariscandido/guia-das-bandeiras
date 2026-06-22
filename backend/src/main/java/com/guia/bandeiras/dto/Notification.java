package com.guia.bandeiras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long id;
    private String message;
    private String type;
    private Boolean read;
    private LocalDateTime timestamp;
}
