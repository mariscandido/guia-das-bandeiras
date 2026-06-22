import { Component } from '@angular/core';

export interface QuizQuestion {
  question: string;
  options: string[];
  correctAnswer: number;
  explanation: string;
}

export interface Flashcard {
  front: string;
  back: string;
  category: string;
}

@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css']
})
export class TrainingComponent {
  activeTab = 'quiz';
  
  // Quiz data
  currentQuestionIndex = 0;
  selectedAnswer: number | null = null;
  showExplanation = false;
  score = 0;
  quizCompleted = false;

  quizzes: QuizQuestion[] = [
    {
      question: 'O que é chargeback?',
      options: [
        'Um tipo de cartão de crédito',
        'Processo de devolução de uma transação',
        'Taxa cobrada pelo banco',
        'Método de pagamento online'
      ],
      correctAnswer: 1,
      explanation: 'Chargeback é o processo de devolução de uma transação ao cartão de crédito do cliente, permitindo contestar cobranças não reconhecidas.'
    },
    {
      question: 'Quantos dígitos tem o MCC?',
      options: ['2 dígitos', '3 dígitos', '4 dígitos', '5 dígitos'],
      correctAnswer: 2,
      explanation: 'MCC (Merchant Category Code) é um código de quatro dígitos usado para classificar estabelecimentos comerciais.'
    },
    {
      question: 'O que significa 3D Secure?',
      options: [
        'Três cartões seguros',
        'Protocolo de segurança para transações online',
        'Tipo de cartão premium',
        'Sistema de criptografia'
      ],
      correctAnswer: 1,
      explanation: '3D Secure é um protocolo de segurança para transações online que adiciona uma camada extra de autenticação.'
    },
    {
      question: 'Qual é o prazo padrão para iniciar um chargeback?',
      options: ['30 dias', '60 dias', '90 dias', '120 dias'],
      correctAnswer: 3,
      explanation: 'O prazo padrão para iniciar um chargeback é de 120 dias a partir da data da transação.'
    },
    {
      question: 'O que é AVS?',
      options: [
        'Automated Verification System',
        'Address Verification System',
        'Authorization Verification System',
        'Account Verification System'
      ],
      correctAnswer: 1,
      explanation: 'AVS (Address Verification System) verifica se o endereço corresponde ao registrado no emissor do cartão.'
    }
  ];

  // Flashcards data
  currentFlashcardIndex = 0;
  isFlipped = false;

  flashcards: Flashcard[] = [
    {
      front: 'Chargeback',
      back: 'Processo de devolução de uma transação ao cartão de crédito do cliente. Mecanismo de proteção ao consumidor.',
      category: 'Disputas'
    },
    {
      front: 'MCC',
      back: 'Merchant Category Code - Código de quatro dígitos para classificar estabelecimentos comerciais.',
      category: 'Classificação'
    },
    {
      front: '3D Secure',
      back: 'Protocolo de segurança para transações online com autenticação adicional.',
      category: 'Segurança'
    },
    {
      front: 'AVS',
      back: 'Address Verification System - Verifica se o endereço corresponde ao registrado no emissor.',
      category: 'Segurança'
    },
    {
      front: 'EMV',
      back: 'Padrão global para cartões com chip que aumenta a segurança através de criptografia dinâmica.',
      category: 'Segurança'
    },
    {
      front: 'Tokenization',
      back: 'Substituição de dados sensíveis do cartão por um token único para reduzir riscos.',
      category: 'Segurança'
    },
    {
      front: 'Capture',
      back: 'Processo de captura dos fundos autorizados para receber o pagamento.',
      category: 'Processamento'
    },
    {
      front: 'Settlement',
      back: 'Processo final onde os fundos são transferidos do emissor para a conta do comerciante.',
      category: 'Processamento'
    }
  ];

  get currentQuestion(): QuizQuestion {
    return this.quizzes[this.currentQuestionIndex];
  }

  get currentFlashcard(): Flashcard {
    return this.flashcards[this.currentFlashcardIndex];
  }

  selectAnswer(index: number): void {
    if (this.showExplanation) return;
    
    this.selectedAnswer = index;
    this.showExplanation = true;

    if (index === this.currentQuestion.correctAnswer) {
      this.score++;
    }
  }

  nextQuestion(): void {
    if (this.currentQuestionIndex < this.quizzes.length - 1) {
      this.currentQuestionIndex++;
      this.selectedAnswer = null;
      this.showExplanation = false;
    } else {
      this.quizCompleted = true;
    }
  }

  resetQuiz(): void {
    this.currentQuestionIndex = 0;
    this.selectedAnswer = null;
    this.showExplanation = false;
    this.score = 0;
    this.quizCompleted = false;
  }

  flipCard(): void {
    this.isFlipped = !this.isFlipped;
  }

  nextFlashcard(): void {
    if (this.currentFlashcardIndex < this.flashcards.length - 1) {
      this.currentFlashcardIndex++;
      this.isFlipped = false;
    }
  }

  previousFlashcard(): void {
    if (this.currentFlashcardIndex > 0) {
      this.currentFlashcardIndex--;
      this.isFlipped = false;
    }
  }

  shuffleFlashcards(): void {
    this.flashcards = [...this.flashcards].sort(() => Math.random() - 0.5);
    this.currentFlashcardIndex = 0;
    this.isFlipped = false;
  }

  getPercentage(): number {
    return Math.round((this.score / this.quizzes.length) * 100);
  }
}
