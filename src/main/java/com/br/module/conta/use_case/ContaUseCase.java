package com.br.module.conta.use_case;

import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ContaUseCase {

    void save(Conta conta);
    void update(Long id, Conta conta);
    void changeSituacao(Long id, SituacaoConta situacao);
    Page<Conta> getContas(LocalDateTime startDate,
                          LocalDateTime endDate,
                          String descricao,
                          Pageable pageable);
    Conta getContaById(Long id);
    BigDecimal getTotalPaid(LocalDateTime startDate, LocalDateTime endDate);

    void deletarConta(Long id);
    int obterContagemDeContas();
    Page<Conta> listContas(SituacaoConta situacao, Pageable pageable);
}
