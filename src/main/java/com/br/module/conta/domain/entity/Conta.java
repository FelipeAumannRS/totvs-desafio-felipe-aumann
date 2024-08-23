package com.br.module.conta.domain.entity;

import com.br.module.conta.domain.entity.enums.SituacaoConta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_vencimento")
    private LocalDateTime dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    private BigDecimal valor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private SituacaoConta situacao;

    public Conta(LocalDateTime dataVencimento, LocalDateTime dataPagamento, BigDecimal valor, String descricao, SituacaoConta situacao) {
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.descricao = descricao;
        this.situacao = situacao;
    }
}