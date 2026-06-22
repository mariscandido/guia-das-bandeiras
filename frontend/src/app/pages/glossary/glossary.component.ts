import { Component, OnInit } from '@angular/core';

export interface GlossaryTerm {
  term: string;
  definition: string;
  category: string;
  relatedTerms?: string[];
}

@Component({
  selector: 'app-glossary',
  templateUrl: './glossary.component.html',
  styleUrls: ['./glossary.component.css']
})
export class GlossaryComponent implements OnInit {
  allTerms: GlossaryTerm[] = [];
  filteredTerms: GlossaryTerm[] = [];
  searchTerm: string = '';
  selectedLetter: string = 'all';
  letters: string[] = ['all', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

  ngOnInit(): void {
    this.loadGlossary();
    this.filterTerms();
  }

  private loadGlossary(): void {
    this.allTerms = [
      { term: 'Chargeback', definition: 'Processo de devolução de uma transação ao cartão de crédito do cliente. É um mecanismo de proteção ao consumidor que permite contestar cobranças não reconhecidas ou fraudulentas.', category: 'Disputas' },
      { term: 'MCC (Merchant Category Code)', definition: 'Código de quatro dígitos usado para classificar estabelecimentos comerciais pelo tipo de negócio. É utilizado pelas bandeiras para categorizar transações e aplicar regras específicas.', category: 'Classificação' },
      { term: 'Autorização', definition: 'Processo pelo qual a bandeira verifica se a transação pode ser processada, verificando limite disponível, validade do cartão e outros parâmetros de segurança.', category: 'Processamento' },
      { term: '3D Secure', definition: 'Protocolo de segurança para transações online que adiciona uma camada extra de autenticação, exigindo que o cliente confirme a compra através de senha, SMS ou app do banco.', category: 'Segurança' },
      { term: 'AVS (Address Verification System)', definition: 'Sistema que verifica se o endereço de cobrança fornecido pelo cliente corresponde ao endereço registrado no emissor do cartão.', category: 'Segurança' },
      { term: 'CVV (Card Verification Value)', definition: 'Código de segurança de três dígitos no verso do cartão (quatro dígitos para American Express) usado para verificar que o cliente está em posse do cartão físico.', category: 'Segurança' },
      { term: 'Capture', definition: 'Processo de captura dos fundos autorizados. Após a autorização, o comerciante deve capturar a transação para receber o pagamento.', category: 'Processamento' },
      { term: 'Settlement', definition: 'Processo final onde os fundos são transferidos do emissor do cartão para a conta do comerciante.', category: 'Processamento' },
      { term: 'Interchange Fee', definition: 'Taxa paga pelo comerciante ao banco emissor do cartão como compensação pelo risco de processar a transação.', category: 'Tarifas' },
      { term: 'Discount Rate', definition: 'Taxa percentual cobrada pelo processador de pagamentos ao comerciante por cada transação processada.', category: 'Tarifas' },
      { term: 'Tokenization', definition: 'Processo de substituir dados sensíveis do cartão por um token único, reduzindo o risco de exposição de informações de pagamento.', category: 'Segurança' },
      { term: 'EMV (Europay, MasterCard, Visa)', definition: 'Padrão global para cartões com chip, que aumenta a segurança através de criptografia dinâmica para cada transação.', category: 'Segurança' },
      { term: 'NFC (Near Field Communication)', definition: 'Tecnologia que permite comunicação sem fio de curto alcance, usada para pagamentos contactless.', category: 'Tecnologia' },
      { term: 'PCI DSS (Payment Card Industry Data Security Standard)', definition: 'Conjunto de requisitos de segurança destinados a garantir que todas as empresas que processam, armazenam ou transmitem informações de cartão mantenham um ambiente seguro.', category: 'Segurança' },
      { term: 'BIN (Bank Identification Number)', definition: 'Primeiros 6 a 8 dígitos do número do cartão que identificam a instituição emissora do cartão.', category: 'Identificação' },
      { term: 'Void', definition: 'Cancelamento de uma transação autorizada ainda não capturada. O valor é liberado imediatamente no limite do cartão.', category: 'Processamento' },
      { term: 'Refund', definition: 'Devolução de uma transação já capturada e liquidada. O valor é devolvido ao cliente pelo comerciante.', category: 'Processamento' },
      { term: 'Pre-authorization', definition: 'Autorização temporária que reserva um valor no limite do cartão, geralmente usado em hotéis e aluguel de carros.', category: 'Processamento' },
      { term: 'Card Not Present (CNP)', definition: 'Transação onde o cartão físico não está presente, como em compras online ou por telefone.', category: 'Tipos de Transação' },
      { term: 'Card Present (CP)', definition: 'Transação onde o cartão físico está presente e é lido através de um terminal POS.', category: 'Tipos de Transação' }
    ];
  }

  onSearchChange(): void {
    this.filterTerms();
  }

  onLetterChange(letter: string): void {
    this.selectedLetter = letter;
    this.filterTerms();
  }

  private filterTerms(): void {
    let filtered = this.allTerms;

    if (this.searchTerm) {
      const searchLower = this.searchTerm.toLowerCase();
      filtered = filtered.filter(term => 
        term.term.toLowerCase().includes(searchLower) ||
        term.definition.toLowerCase().includes(searchLower) ||
        term.category.toLowerCase().includes(searchLower)
      );
    }

    if (this.selectedLetter !== 'all') {
      filtered = filtered.filter(term => 
        term.term.toUpperCase().startsWith(this.selectedLetter)
      );
    }

    this.filteredTerms = filtered;
  }

  getCategories(): string[] {
    return [...new Set(this.allTerms.map(t => t.category))];
  }

  getTermsByCategory(category: string): GlossaryTerm[] {
    return this.filteredTerms.filter(t => t.category === category);
  }
}
