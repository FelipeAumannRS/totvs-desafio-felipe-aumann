package com.br.module.conta.service;

import com.br.config.kafka.KafkaProducerService;
import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;
import com.br.module.conta.repository.ContaRepository;
import com.br.module.conta.use_case.ContaUseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContaService implements ContaUseCase {

    private final ContaRepository repository;
    private final KafkaProducerService kafkaProducerService;

    @Override
    public void save(Conta conta) {
        log.info("Salvando Conta...");
        kafkaProducerService.sendNotification(conta);
    }

    @Override
    @Transactional
    public void update(Long id, Conta conta) {
        conta.setId(id);
        log.info("Atualizando conta {}...", conta.getId());
        kafkaProducerService.sendNotification(conta);
    }

    @Override
    public void changeSituacao(Long id, SituacaoConta situacao) {
        log.info("Efetuando pagamento da Conta {}...", id);
        Conta conta = repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        conta.setSituacao(situacao);
        kafkaProducerService.sendNotification(conta);
    }

    @Override
    public Page<Conta> getContas(LocalDateTime dataInicio, LocalDateTime dataFim, String descricao, Pageable pageable) {
        return repository.findByDataVencimentoBetweenAndDescricaoContainingAndSituacaoNot(dataInicio, dataFim, descricao, SituacaoConta.PAGO, pageable);
    }

    @Override
    public Conta getContaById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    @Override
    public BigDecimal getTotalPaid(LocalDateTime startDate, LocalDateTime endDate) {
        List<Conta> contas = repository.findByDataPagamentoBetween(startDate, endDate);
        return contas.stream()
                .filter(conta -> conta.getSituacao() == SituacaoConta.PAGO)
                .map(Conta::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void deletarConta(Long id) {
        log.info("Deletando Conta com ID {}", id);
        if (!repository.existsById(id)) {
            throw new RuntimeException("Conta com ID {0} não encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public int obterContagemDeContas() {
        log.info("Obtendo contagem de contas");
        return (int) repository.count();
    }

    @Override
    public Page<Conta> listContas(SituacaoConta situacao, Pageable pageable) {
        log.info("Obtendo listagem de contas por data crescente");
        return repository.findAllBySituacaoOrderByDataVencimentoDesc(situacao, pageable);
    }
}