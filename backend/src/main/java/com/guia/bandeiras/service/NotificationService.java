package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class NotificationService {
    
    private final List<Notification> notifications = new ArrayList<>();
    
    public NotificationService() {
        // Initialize with some sample notifications
        notifications.add(new Notification(
            1L,
            "Nova atualização no manual de Chargeback da Visa disponível",
            "info",
            false,
            LocalDateTime.now().minusDays(1)
        ));
        notifications.add(new Notification(
            2L,
            "Manual de MCC do MasterCard atualizado com novas categorias",
            "warning",
            false,
            LocalDateTime.now().minusDays(3)
        ));
        notifications.add(new Notification(
            3L,
            "Novas regras de 3D Secure implementadas",
            "success",
            true,
            LocalDateTime.now().minusDays(5)
        ));
    }
    
    public List<Notification> getAllNotifications() {
        log.info("Fetching all notifications");
        return notifications;
    }
    
    public List<Notification> getUnreadNotifications() {
        log.info("Fetching unread notifications");
        return notifications.stream()
                .filter(n -> !n.getRead())
                .toList();
    }
    
    public void markAsRead(Long id) {
        log.info("Marking notification {} as read", id);
        notifications.stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .ifPresent(n -> n.setRead(true));
    }
    
    public void markAllAsRead() {
        log.info("Marking all notifications as read");
        notifications.forEach(n -> n.setRead(true));
    }
    
    public void addNotification(String message, String type) {
        log.info("Adding notification: {} - {}", type, message);
        Long newId = notifications.stream()
                .mapToLong(Notification::getId)
                .max()
                .orElse(0L) + 1;
        
        notifications.add(new Notification(
            newId,
            message,
            type,
            false,
            LocalDateTime.now()
        ));
    }
}
