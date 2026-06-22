package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.Flashcard;
import com.guia.bandeiras.dto.QuizQuestion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class TrainingService {
    
    private final List<QuizQuestion> quizQuestions = Arrays.asList(
        new QuizQuestion(
            "O que é chargeback?",
            new String[]{"Um tipo de cartão de crédito", "Processo de devolução de uma transação", "Taxa cobrada pelo banco", "Método de pagamento online"},
            1,
            "Chargeback é o processo de devolução de uma transação ao cartão de crédito do cliente, permitindo contestar cobranças não reconhecidas."
        ),
        new QuizQuestion(
            "Quantos dígitos tem o MCC?",
            new String[]{"2 dígitos", "3 dígitos", "4 dígitos", "5 dígitos"},
            2,
            "MCC (Merchant Category Code) é um código de quatro dígitos usado para classificar estabelecimentos comerciais."
        ),
        new QuizQuestion(
            "O que significa 3D Secure?",
            new String[]{"Três cartões seguros", "Protocolo de segurança para transações online", "Tipo de cartão premium", "Sistema de criptografia"},
            1,
            "3D Secure é um protocolo de segurança para transações online que adiciona uma camada extra de autenticação."
        ),
        new QuizQuestion(
            "Qual é o prazo padrão para iniciar um chargeback?",
            new String[]{"30 dias", "60 dias", "90 dias", "120 dias"},
            3,
            "O prazo padrão para iniciar um chargeback é de 120 dias a partir da data da transação."
        ),
        new QuizQuestion(
            "O que é AVS?",
            new String[]{"Automated Verification System", "Address Verification System", "Authorization Verification System", "Account Verification System"},
            1,
            "AVS (Address Verification System) verifica se o endereço corresponde ao registrado no emissor do cartão."
        )
    );
    
    private final List<Flashcard> flashcards = Arrays.asList(
        new Flashcard("Chargeback", "Processo de devolução de uma transação ao cartão de crédito do cliente. Mecanismo de proteção ao consumidor.", "Disputas"),
        new Flashcard("MCC", "Merchant Category Code - Código de quatro dígitos para classificar estabelecimentos comerciais.", "Classificação"),
        new Flashcard("3D Secure", "Protocolo de segurança para transações online com autenticação adicional.", "Segurança"),
        new Flashcard("AVS", "Address Verification System - Verifica se o endereço corresponde ao registrado no emissor.", "Segurança"),
        new Flashcard("EMV", "Padrão global para cartões com chip que aumenta a segurança através de criptografia dinâmica.", "Segurança"),
        new Flashcard("Tokenization", "Substituição de dados sensíveis do cartão por um token único para reduzir riscos.", "Segurança"),
        new Flashcard("Capture", "Processo de captura dos fundos autorizados para receber o pagamento.", "Processamento"),
        new Flashcard("Settlement", "Processo final onde os fundos são transferidos do emissor para a conta do comerciante.", "Processamento"),
        new Flashcard("Elo", "Bandeira brasileira de cartões, resultado da parceria entre Bradesco e Banco do Brasil.", "Bandeiras"),
        new Flashcard("Hipercard", "Bandeira brasileira de cartões, focada em crédito à consumo e pertencente ao grupo Itaú.", "Bandeiras"),
        new Flashcard("Discover", "Bandeira americana de cartões, focada em cashback e recompensas.", "Bandeiras"),
        new Flashcard("PCI DSS", "Conjunto de requisitos de segurança para empresas que processam informações de cartão.", "Segurança")
    );
    
    public List<QuizQuestion> getQuizQuestions() {
        log.info("Fetching quiz questions");
        return quizQuestions;
    }
    
    public List<Flashcard> getFlashcards() {
        log.info("Fetching flashcards");
        return flashcards;
    }
}
