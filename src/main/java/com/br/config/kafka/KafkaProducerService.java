package com.br.config.kafka;

import com.br.module.conta.domain.entity.Conta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Conta> kafkaTemplate;

    public void sendNotification(Conta conta) {
        kafkaTemplate.send("conta-notification", conta);
    }
}