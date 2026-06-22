package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.GlossaryTerm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class GlossaryService {
    
    private final List<GlossaryTerm> glossaryTerms = Arrays.asList(
        new GlossaryTerm("Chargeback", "Processo de devolução de uma transação ao cartão de crédito do cliente. É um mecanismo de proteção ao consumidor que permite contestar cobranças não reconhecidas ou fraudulentas.", "Disputas", new String[]{"Disputa", "Reembolso"}),
        new GlossaryTerm("MCC (Merchant Category Code)", "Código de quatro dígitos usado para classificar estabelecimentos comerciais pelo tipo de negócio. É utilizado pelas bandeiras para categorizar transações e aplicar regras específicas.", "Classificação", new String[]{"Código de Categoria", "Classificação"}),
        new GlossaryTerm("Autorização", "Processo pelo qual a bandeira verifica se a transação pode ser processada, verificando limite disponível, validade do cartão e outros parâmetros de segurança.", "Processamento", new String[]{"Aprovação", "Validação"}),
        new GlossaryTerm("3D Secure", "Protocolo de segurança para transações online que adiciona uma camada extra de autenticação, exigindo que o cliente confirme a compra através de senha, SMS ou app do banco.", "Segurança", new String[]{"Verificação", "Autenticação"}),
        new GlossaryTerm("AVS (Address Verification System)", "Sistema que verifica se o endereço de cobrança fornecido pelo cliente corresponde ao endereço registrado no emissor do cartão.", "Segurança", new String[]{"Verificação de Endereço"}),
        new GlossaryTerm("CVV (Card Verification Value)", "Código de segurança de três dígitos no verso do cartão (quatro dígitos para American Express) usado para verificar que o cliente está em posse do cartão físico.", "Segurança", new String[]{"CVC", "Código de Segurança"}),
        new GlossaryTerm("Capture", "Processo de captura dos fundos autorizados. Após a autorização, o comerciante deve capturar a transação para receber o pagamento.", "Processamento", new String[]{"Captura de Fundos"}),
        new GlossaryTerm("Settlement", "Processo final onde os fundos são transferidos do emissor do cartão para a conta do comerciante.", "Processamento", new String[]{"Liquidation", "Transferência"}),
        new GlossaryTerm("Interchange Fee", "Taxa paga pelo comerciante ao banco emissor do cartão como compensação pelo risco de processar a transação.", "Tarifas", new String[]{"Taxa de Intercâmbio"}),
        new GlossaryTerm("Discount Rate", "Taxa percentual cobrada pelo processador de pagamentos ao comerciante por cada transação processada.", "Tarifas", new String[]{"Taxa de Desconto"}),
        new GlossaryTerm("Tokenization", "Processo de substituir dados sensíveis do cartão por um token único, reduzindo o risco de exposição de informações de pagamento.", "Segurança", new String[]{"Tokenização"}),
        new GlossaryTerm("EMV (Europay, MasterCard, Visa)", "Padrão global para cartões com chip, que aumenta a segurança através de criptografia dinâmica para cada transação.", "Segurança", new String[]{"Chip", "Cartão com Chip"}),
        new GlossaryTerm("NFC (Near Field Communication)", "Tecnologia que permite comunicação sem fio de curto alcance, usada para pagamentos contactless.", "Tecnologia", new String[]{"Contactless", "Pagamento por Aproximação"}),
        new GlossaryTerm("PCI DSS (Payment Card Industry Data Security Standard)", "Conjunto de requisitos de segurança destinados a garantir que todas as empresas que processam, armazenam ou transmitem informações de cartão mantenham um ambiente seguro.", "Segurança", new String[]{"PCI", "Conformidade"}),
        new GlossaryTerm("BIN (Bank Identification Number)", "Primeiros 6 a 8 dígitos do número do cartão que identificam a instituição emissora do cartão.", "Identificação", new String[]{"IIN", "Banco Emissor"}),
        new GlossaryTerm("Void", "Cancelamento de uma transação autorizada ainda não capturada. O valor é liberado imediatamente no limite do cartão.", "Processamento", new String[]{"Cancelamento"}),
        new GlossaryTerm("Refund", "Devolução de uma transação já capturada e liquidada. O valor é devolvido ao cliente pelo comerciante.", "Processamento", new String[]{"Reembolso", "Estorno"}),
        new GlossaryTerm("Pre-authorization", "Autorização temporária que reserva um valor no limite do cartão, geralmente usado em hotéis e aluguel de carros.", "Processamento", new String[]{"Pré-autorização", "Bloqueio"}),
        new GlossaryTerm("Card Not Present (CNP)", "Transação onde o cartão físico não está presente, como em compras online ou por telefone.", "Tipos de Transação", new String[]{"CNP", "Online"}),
        new GlossaryTerm("Card Present (CP)", "Transação onde o cartão físico está presente e é lido através de um terminal POS.", "Tipos de Transação", new String[]{"CP", "Presencial"}),
        new GlossaryTerm("Elo", "Bandeira brasileira de cartões, resultado da parceria entre Bradesco e Banco do Brasil.", "Bandeiras", new String[]{"Bandeira Brasileira"}),
        new GlossaryTerm("Hipercard", "Bandeira brasileira de cartões, focada em crédito à consumo e pertencente ao grupo Itaú.", "Bandeiras", new String[]{"Cartão Itaú"}),
        new GlossaryTerm("Discover", "Bandeira americana de cartões, focada em cashback e recompensas.", "Bandeiras", new String[]{"Cashback"})
    );
    
    @Cacheable(value = "glossaryCache", key = "#letter")
    public List<GlossaryTerm> getTermsByLetter(String letter) {
        log.info("Fetching glossary terms starting with: {}", letter);
        if ("all".equalsIgnoreCase(letter)) {
            return glossaryTerms;
        }
        return glossaryTerms.stream()
                .filter(term -> term.getTerm().toUpperCase().startsWith(letter.toUpperCase()))
                .toList();
    }
    
    @Cacheable(value = "glossaryCache", key = "'all'")
    public List<GlossaryTerm> getAllTerms() {
        log.info("Fetching all glossary terms");
        return glossaryTerms;
    }
    
    public List<GlossaryTerm> searchTerms(String query) {
        log.info("Searching glossary terms with query: {}", query);
        String searchLower = query.toLowerCase();
        return glossaryTerms.stream()
                .filter(term -> 
                    term.getTerm().toLowerCase().contains(searchLower) ||
                    term.getDefinition().toLowerCase().contains(searchLower) ||
                    term.getCategory().toLowerCase().contains(searchLower)
                )
                .toList();
    }
}
