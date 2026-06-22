import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router) {}

  navigateToHome() {
    this.router.navigate(['/home']);
  }

  navigateToFaq() {
    this.router.navigate(['/faq']);
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

  navigateToAbout() {
    this.router.navigate(['/about']);
  }
}
