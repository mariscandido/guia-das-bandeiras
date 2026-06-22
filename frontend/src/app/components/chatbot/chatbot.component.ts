import { Component } from '@angular/core';

export interface ChatMessage {
  text: string;
  isUser: boolean;
  timestamp: Date;
}

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  isOpen = false;
  messages: ChatMessage[] = [
    {
      text: 'Olá! Sou o assistente virtual do Guia das Bandeiras. Como posso ajudar?',
      isUser: false,
      timestamp: new Date()
    }
  ];
  userInput = '';
  isTyping = false;

  private responses: { [key: string]: string } = {
    'chargeback': 'Chargeback é o processo de devolução de uma transação ao cartão de crédito do cliente. É um mecanismo de proteção ao consumidor que permite contestar cobranças não reconhecidas ou fraudulentas.',
    'mcc': 'MCC (Merchant Category Code) é um código de quatro dígitos usado para classificar estabelecimentos comerciais pelo tipo de negócio.',
    'autorização': 'Autorização é o processo pelo qual a bandeira verifica se a transação pode ser processada, verificando limite disponível, validade do cartão e outros parâmetros de segurança.',
    'fraude': 'Fraude em cartões envolve uso não autorizado de informações de cartão para realizar transações. As bandeiras têm diversos mecanismos de prevenção.',
    'visa': 'Visa é uma das maiores bandeiras de cartão do mundo, com regras específicas para processamento e disputas.',
    'mastercard': 'MasterCard é uma das principais bandeiras globais, com diretrizes próprias para processamento de pagamentos.',
    'amex': 'American Express é uma bandeira que emite seus próprios cartões e tem processos específicos para disputas.',
    '3d secure': '3D Secure é um protocolo de segurança para transações online que adiciona autenticação adicional.',
    'padrão': 'Desculpe, não entendi sua pergunta. Tente perguntar sobre chargeback, MCC, autorização, fraude ou outras bandeiras.'
  };

  toggleChat(): void {
    this.isOpen = !this.isOpen;
  }

  sendMessage(): void {
    if (!this.userInput.trim()) return;

    const userMessage: ChatMessage = {
      text: this.userInput,
      isUser: true,
      timestamp: new Date()
    };

    this.messages.push(userMessage);
    const query = this.userInput.toLowerCase();
    this.userInput = '';

    this.isTyping = true;

    setTimeout(() => {
      this.isTyping = false;
      let response = this.responses['padrão'];

      for (const key in this.responses) {
        if (query.includes(key) && key !== 'padrão') {
          response = this.responses[key];
          break;
        }
      }

      const botMessage: ChatMessage = {
        text: response,
        isUser: false,
        timestamp: new Date()
      };

      this.messages.push(botMessage);
    }, 1000);
  }

  onKeyPress(event: KeyboardEvent): void {
    if (event.key === 'Enter') {
      this.sendMessage();
    }
  }
}
