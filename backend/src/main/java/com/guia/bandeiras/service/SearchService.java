package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.FaqItem;
import com.guia.bandeiras.dto.SearchRequest;
import com.guia.bandeiras.dto.SearchResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {

    @Cacheable(value = "faqCache")
    public List<FaqItem> getFaq() {
        List<FaqItem> faqList = new ArrayList<>();
        
        faqList.add(new FaqItem(
            "O que é chargeback?",
            "Chargeback é o processo de devolução de uma transação ao cartão de crédito do cliente. É um mecanismo de proteção ao consumidor que permite contestar cobranças não reconhecidas ou fraudulentas.",
            "Conceitos Básicos"
        ));
        
        faqList.add(new FaqItem(
            "O que é MCC (Merchant Category Code)?",
            "MCC é um código de quatro dígitos usado para classificar estabelecimentos comerciais pelo tipo de negócio. É utilizado pelas bandeiras para categorizar transações e aplicar regras específicas.",
            "Conceitos Básicos"
        ));
        
        faqList.add(new FaqItem(
            "O que é autorização de transação?",
            "Autorização é o processo pelo qual a bandeira verifica se a transação pode ser processada, verificando limite disponível, validade do cartão e outros parâmetros de segurança.",
            "Processamento"
        ));
        
        faqList.add(new FaqItem(
            "Quais são os prazos para contestação?",
            "Os prazos variam conforme a bandeira e o tipo de contestação. Geralmente, o prazo para iniciar um chargeback é de 120 dias a partir da data da transação, podendo ser estendido em casos específicos.",
            "Prazos"
        ));
        
        faqList.add(new FaqItem(
            "O que é 3D Secure?",
            "3D Secure é um protocolo de segurança para transações online que adiciona uma camada extra de autenticação, exigindo que o cliente confirme a compra através de senha, SMS ou app do banco.",
            "Segurança"
        ));
        
        log.info("FAQ loaded with {} items", faqList.size());
        return faqList;
    }

    public SearchResponse search(SearchRequest request) {
        String query = request.getQuery().toLowerCase();
        String cardBrandFilter = request.getCardBrand();
        
        List<SearchResponse.SearchResult> results = new ArrayList<>();
        
        // Simulated data - In production, this would scrape or consume APIs from card brands
        results.addAll(searchVisaManual(query, cardBrandFilter));
        results.addAll(searchMastercardManual(query, cardBrandFilter));
        results.addAll(searchAmexManual(query, cardBrandFilter));
        
        SearchResponse response = new SearchResponse();
        response.setQuery(request.getQuery());
        response.setResults(results);
        response.setTotalResults(results.size());
        
        log.info("Search completed for query '{}': {} results found", request.getQuery(), results.size());
        return response;
    }

    private List<SearchResponse.SearchResult> searchVisaManual(String query, String cardBrandFilter) {
        if (cardBrandFilter != null && !cardBrandFilter.equalsIgnoreCase("visa")) {
            return new ArrayList<>();
        }
        
        List<SearchResponse.SearchResult> results = new ArrayList<>();
        
        // Simulated Visa manual data
        if (query.contains("chargeback")) {
            results.add(new SearchResponse.SearchResult(
                "Visa",
                "Chargeback Rules and Timeframes",
                "O chargeback Visa deve ser iniciado dentro de 120 dias da data da transação. Para transações eletrônicas, o prazo pode ser estendido para 540 dias em casos de fraude.",
                "https://www.visa.com/support",
                "Disputes"
            ));
        }
        
        if (query.contains("mcc")) {
            results.add(new SearchResponse.SearchResult(
                "Visa",
                "Merchant Category Codes",
                "Visa utiliza códigos MCC de 4 dígitos para classificar estabelecimentos. Exemplos: 5411 - Supermercados, 5812 - Restaurantes, 5542 - Postos de combustível.",
                "https://www.visa.com/support",
                "Classification"
            ));
        }
        
        if (query.contains("autoriza")) {
            results.add(new SearchResponse.SearchResult(
                "Visa",
                "Authorization Process",
                "A autorização Visa verifica disponibilidade de limite, validade do cartão e verificações de fraude em tempo real. O código de resposta 00 indica aprovação.",
                "https://www.visa.com/support",
                "Processing"
            ));
        }
        
        return results;
    }

    private List<SearchResponse.SearchResult> searchMastercardManual(String query, String cardBrandFilter) {
        if (cardBrandFilter != null && !cardBrandFilter.equalsIgnoreCase("mastercard")) {
            return new ArrayList<>();
        }
        
        List<SearchResponse.SearchResult> results = new ArrayList<>();
        
        if (query.contains("chargeback")) {
            results.add(new SearchResponse.SearchResult(
                "MasterCard",
                "Chargeback Guide",
                "MasterCard define prazos específicos para cada motivo de chargeback. O prazo padrão é de 120 dias, mas pode variar conforme o código de razão utilizado.",
                "https://www.mastercard.com/support",
                "Disputes"
            ));
        }
        
        if (query.contains("mcc")) {
            results.add(new SearchResponse.SearchResult(
                "MasterCard",
                "MCC Classification",
                "MasterCard mantém uma lista atualizada de MCCs. Estabelecimentos podem ter múltiplos MCCs dependendo dos serviços oferecidos.",
                "https://www.mastercard.com/support",
                "Classification"
            ));
        }
        
        if (query.contains("seguran")) {
            results.add(new SearchResponse.SearchResult(
                "MasterCard",
                "Security Standards",
                "MasterCard exige conformidade com PCI DSS para todos os estabelecimentos. Adicionalmente, recomenda uso de tokenização e criptografia de ponta a ponta.",
                "https://www.mastercard.com/support",
                "Security"
            ));
        }
        
        return results;
    }

    private List<SearchResponse.SearchResult> searchAmexManual(String query, String cardBrandFilter) {
        if (cardBrandFilter != null && !cardBrandFilter.equalsIgnoreCase("amex") && !cardBrandFilter.equalsIgnoreCase("american express")) {
            return new ArrayList<>();
        }
        
        List<SearchResponse.SearchResult> results = new ArrayList<>();
        
        if (query.contains("chargeback")) {
            results.add(new SearchResponse.SearchResult(
                "American Express",
                "Inquiry and Dispute Resolution",
                "American Express possui um processo de disputa em duas etapas: Inquiry (inquérito) e Chargeback. O inquérito permite investigação antes do chargeback formal.",
                "https://www.americanexpress.com/merchant",
                "Disputes"
            ));
        }
        
        if (query.contains("autoriza")) {
            results.add(new SearchResponse.SearchResult(
                "American Express",
                "Authorization Requirements",
                "American Express recomenda autorização para todas as transações acima de um valor mínimo. A autorização é válida por 7 a 30 dias dependendo do estabelecimento.",
                "https://www.americanexpress.com/merchant",
                "Processing"
            ));
        }
        
        if (query.contains("fraude")) {
            results.add(new SearchResponse.SearchResult(
                "American Express",
                "Fraud Prevention",
                "American Express utiliza modelos avançados de detecção de fraude com machine learning. Estabelecimentos devem utilizar AVS e CVV para reduzir fraudes.",
                "https://www.americanexpress.com/merchant",
                "Security"
            ));
        }
        
        return results;
    }
}
