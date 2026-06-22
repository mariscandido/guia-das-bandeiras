import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

export interface Notification {
  id: number;
  message: string;
  type: 'info' | 'success' | 'warning' | 'error';
  timestamp: Date;
  read: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private notifications = new BehaviorSubject<Notification[]>([]);
  notifications$ = this.notifications.asObservable();
  private unreadCount = new BehaviorSubject<number>(0);
  unreadCount$ = this.unreadCount.asObservable();

  constructor() {
    this.loadNotifications();
    this.simulateManualUpdates();
  }

  private loadNotifications(): void {
    const saved = localStorage.getItem('notifications');
    if (saved) {
      this.notifications.next(JSON.parse(saved));
      this.updateUnreadCount();
    }
  }

  private saveNotifications(): void {
    localStorage.setItem('notifications', JSON.stringify(this.notifications.value));
  }

  private updateUnreadCount(): void {
    const unread = this.notifications.value.filter(n => !n.read).length;
    this.unreadCount.next(unread);
  }

  addNotification(message: string, type: 'info' | 'success' | 'warning' | 'error' = 'info'): void {
    const notification: Notification = {
      id: Date.now(),
      message,
      type,
      timestamp: new Date(),
      read: false
    };
    
    const current = this.notifications.value;
    this.notifications.next([notification, ...current].slice(0, 50));
    this.saveNotifications();
    this.updateUnreadCount();
  }

  markAsRead(id: number): void {
    const current = this.notifications.value.map(n => 
      n.id === id ? { ...n, read: true } : n
    );
    this.notifications.next(current);
    this.saveNotifications();
    this.updateUnreadCount();
  }

  markAllAsRead(): void {
    const current = this.notifications.value.map(n => ({ ...n, read: true }));
    this.notifications.next(current);
    this.saveNotifications();
    this.updateUnreadCount();
  }

  clearAll(): void {
    this.notifications.next([]);
    this.saveNotifications();
    this.updateUnreadCount();
  }

  private simulateManualUpdates(): void {
    setTimeout(() => {
      this.addNotification('Novo manual da Visa disponível: Atualização de regras de chargeback', 'info');
    }, 5000);

    setTimeout(() => {
      this.addNotification('MasterCard: Novas diretrizes de segurança implementadas', 'warning');
    }, 15000);
  }
}
