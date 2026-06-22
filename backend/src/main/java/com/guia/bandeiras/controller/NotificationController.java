package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.Notification;
import com.guia.bandeiras.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class NotificationController {
    
    private final NotificationService notificationService;
    
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        log.info("GET /api/notifications");
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
    
    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications() {
        log.info("GET /api/notifications/unread");
        return ResponseEntity.ok(notificationService.getUnreadNotifications());
    }
    
    @PostMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        log.info("POST /api/notifications/{}/read", id);
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead() {
        log.info("POST /api/notifications/read-all");
        notificationService.markAllAsRead();
        return ResponseEntity.ok().build();
    }
}
