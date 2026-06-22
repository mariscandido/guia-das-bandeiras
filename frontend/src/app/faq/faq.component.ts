import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface FaqItem {
  question: string;
  answer: string;
  category: string;
}

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit {
  faqItems: FaqItem[] = [];
  loading: boolean = true;
  error: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadFaq();
  }

  loadFaq() {
    this.http.get<FaqItem[]>('/api/faq').subscribe({
      next: (items: FaqItem[]) => {
        this.faqItems = items;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Erro ao carregar FAQ. Tente novamente mais tarde.';
        this.loading = false;
        console.error('Error loading FAQ:', err);
      }
    });
  }

  getCategories(): string[] {
    return [...new Set(this.faqItems.map(item => item.category))];
  }

  getItemsByCategory(category: string): FaqItem[] {
    return this.faqItems.filter(item => item.category === category);
  }
}
