import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  features = [
    {
      icon: 'search',
      title: 'Busca Unificada',
      description: 'Pesquise em múltiplas bandeiras ao mesmo tempo'
    },
    {
      icon: 'menu_book',
      title: 'Glossário Completo',
      description: 'Termos técnicos explicados de forma simples'
    },
    {
      icon: 'help',
      title: 'FAQ Inteligente',
      description: 'Perguntas frequentes organizadas por categoria'
    },
    {
      icon: 'analytics',
      title: 'Dashboard',
      description: 'Estatísticas de uso e tendências'
    },
    {
      icon: 'school',
      title: 'Área de Treinamento',
      description: 'Quizzes e flashcards para aprendizado'
    },
    {
      icon: 'picture_as_pdf',
      title: 'Exportação PDF',
      description: 'Exporte resultados para treinamento'
    }
  ];

  team = [
    {
      name: 'Guia das Bandeiras',
      role: 'Projeto Open Source',
      description: 'Plataforma desenvolvida para facilitar o acesso a informações oficiais sobre processamento de cartões.'
    }
  ];

  version = '2.0.0';
}
