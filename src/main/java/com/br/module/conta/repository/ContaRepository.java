package com.br.module.conta.repository;

import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findAllBySituacaoOrderByDataVencimentoDesc(SituacaoConta situacao, Pageable pageable);
    List<Conta> findByDataPagamentoBetween(LocalDateTime startDate, LocalDateTime endDate);
    Page<Conta> findByDataVencimentoBetweenAndDescricaoContainingAndSituacaoNot(
            LocalDateTime startDate,
            LocalDateTime endDate,
            String descricao,
            SituacaoConta situacao,
            Pageable pageable
    );
}