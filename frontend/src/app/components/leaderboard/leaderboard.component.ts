import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface LeaderboardEntry {
  username: string;
  points: number;
  rank: number;
  rankTitle: string;
}

export interface UserStats {
  username: string;
  points: number;
  rank: number;
  rankTitle: string;
  position: number;
}

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {
  leaderboard: LeaderboardEntry[] = [];
  userStats: UserStats | null = null;
  loading = true;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadLeaderboard();
    this.loadUserStats();
  }

  loadLeaderboard(): void {
    this.http.get<LeaderboardEntry[]>('/api/gamification/leaderboard').subscribe({
      next: (data) => {
        this.leaderboard = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading leaderboard:', err);
        this.loading = false;
      }
    });
  }

  loadUserStats(): void {
    this.http.get<UserStats>('/api/gamification/user-stats').subscribe({
      next: (data) => {
        this.userStats = data;
      },
      error: (err) => {
        console.error('Error loading user stats:', err);
      }
    });
  }

  getRankIcon(rank: number): string {
    const icons = {
      1: 'star_border',
      2: 'emoji_events',
      3: 'workspace_premium',
      4: 'military_tech',
      5: 'diamond'
    };
    return icons[rank as keyof typeof icons] || 'star_border';
  }

  getRankColor(rank: number): string {
    const colors = {
      1: '#9e9e9e',
      2: '#cd7f32',
      3: '#c0c0c0',
      4: '#ffd700',
      5: '#e5e4e2'
    };
    return colors[rank as keyof typeof colors] || '#9e9e9e';
  }
}
