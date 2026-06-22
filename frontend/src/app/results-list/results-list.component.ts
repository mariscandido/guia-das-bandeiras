import { Component, Input } from '@angular/core';

export interface SearchResult {
  cardBrand: string;
  title: string;
  excerpt: string;
  sourceUrl: string;
  section: string;
}

@Component({
  selector: 'app-results-list',
  templateUrl: './results-list.component.html',
  styleUrls: ['./results-list.component.css']
})
export class ResultsListComponent {
  @Input() results: SearchResult[] = [];
  @Input() loading: boolean = false;
  @Input() query: string = '';

  getBrandColor(brand: string): string {
    const brandLower = brand.toLowerCase();
    switch(brandLower) {
      case 'visa':
        return '#1a1f71';
      case 'mastercard':
        return '#eb001b';
      case 'american express':
      case 'amex':
        return '#006fcf';
      default:
        return '#666';
    }
  }
}
