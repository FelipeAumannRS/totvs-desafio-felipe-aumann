package com.br.config.kafka;

import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final ContaRepository repository;

    @KafkaListener(topics = "conta-notification", groupId = "totvs")
    public void receiveNotification(Conta conta) {
        if (conta.getId() != null && repository.existsById(conta.getId()))
            repository.findById(conta.getId()).ifPresent(existingConta -> {
                existingConta.setDataVencimento(conta.getDataVencimento());
                existingConta.setDataPagamento(conta.getDataPagamento());
                existingConta.setValor(conta.getValor());
                existingConta.setDescricao(conta.getDescricao());
                existingConta.setSituacao(conta.getSituacao());
                repository.save(existingConta);
                log.info("Conta {} atualizada com sucesso, Situacao {}", conta.getId(), conta.getSituacao());
            });
        else {
            repository.save(conta);
            log.info("Conta {} criada com sucesso!", conta.getId());
        }
    }
}