package com.br.module.conta.domain.csv;

import com.br.module.conta.domain.entity.enums.SituacaoConta;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class ContaCSV {

    @CsvBindByPosition(position = 0)
    private String dataVencimento;

    @CsvBindByPosition(position = 1)
    private String dataPagamento;

    @CsvBindByPosition(position = 2)
    private String valor;

    @CsvBindByPosition(position = 3)
    private String descricao;

    @CsvBindByPosition(position = 4)
    private String situacao;
}
