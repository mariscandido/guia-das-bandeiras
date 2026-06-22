import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { ResultsListComponent, SearchResult } from '../results-list/results-list.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  results: SearchResult[] = [];
  loading: boolean = false;
  query: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSearch(query: string) {
    this.query = query;
    this.loading = true;
    this.results = [];

    this.http.get<any>(`/api/search?query=${encodeURIComponent(query)}`).subscribe({
      next: (response: any) => {
        this.results = response.results || [];
        this.loading = false;
      },
      error: (err: any) => {
        console.error('Search error:', err);
        this.loading = false;
      }
    });
  }

  navigateToGlossary() {
    this.router.navigate(['/glossary']);
  }

  navigateToDashboard() {
    this.router.navigate(['/dashboard']);
  }

  navigateToTraining() {
    this.router.navigate(['/training']);
  }
}
