import { Component, AfterViewInit, ElementRef, ViewChild } from '@angular/core';
import { Chart } from 'chart.js/auto';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements AfterViewInit {
  @ViewChild('searchesByBrandChart') searchesByBrandChart!: ElementRef;
  @ViewChild('topSearchesChart') topSearchesChart!: ElementRef;
  @ViewChild('searchTrendsChart') searchTrendsChart!: ElementRef;

  stats = {
    totalSearches: 1247,
    uniqueUsers: 342,
    avgResponseTime: 0.8
  };

  ngAfterViewInit(): void {
    this.initSearchesByBrandChart();
    this.initTopSearchesChart();
    this.initSearchTrendsChart();
  }

  private initSearchesByBrandChart(): void {
    const ctx = this.searchesByBrandChart.nativeElement.getContext('2d');
    new Chart(ctx, {
      type: 'doughnut',
      data: {
        labels: ['Visa', 'MasterCard', 'American Express'],
        datasets: [{
          data: [45, 35, 20],
          backgroundColor: ['#1a1f71', '#eb001b', '#006fcf'],
          borderWidth: 0
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          }
        }
      }
    });
  }

  private initTopSearchesChart(): void {
    const ctx = this.topSearchesChart.nativeElement.getContext('2d');
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: ['Chargeback', 'MCC', 'Autorização', 'Fraude', '3D Secure'],
        datasets: [{
          label: 'Número de buscas',
          data: [234, 189, 156, 134, 98],
          backgroundColor: '#444444',
          borderRadius: 8
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          }
        },
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }

  private initSearchTrendsChart(): void {
    const ctx = this.searchTrendsChart.nativeElement.getContext('2d');
    new Chart(ctx, {
      type: 'line',
      data: {
        labels: ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb', 'Dom'],
        datasets: [{
          label: 'Buscas',
          data: [120, 145, 132, 168, 189, 95, 87],
          borderColor: '#444444',
          backgroundColor: 'rgba(68, 68, 68, 0.1)',
          fill: true,
          tension: 0.4
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false
          }
        },
        scales: {
          y: {
            beginAtZero: true
          }
        }
      }
    });
  }
}
