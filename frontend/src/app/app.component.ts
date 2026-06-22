import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router) {}

  navigateToFaq() {
    this.router.navigate(['/faq']);
  }

  navigateToHome() {
    this.router.navigate(['/home']);
  }
}
