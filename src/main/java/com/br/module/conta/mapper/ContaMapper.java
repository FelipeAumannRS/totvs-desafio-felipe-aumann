package com.br.module.conta.mapper;

import com.br.module.conta.domain.csv.ContaCSV;
import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ContaMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");

    public static Conta toEntity(ContaCSV contaCSV) {
        LocalDateTime dataVencimento = LocalDateTime.parse(contaCSV.getDataVencimento(), formatter);
        LocalDateTime dataPagamento = LocalDateTime.parse(contaCSV.getDataPagamento(), formatter);
        BigDecimal valor = new BigDecimal(contaCSV.getValor().replace(",", "."));
        SituacaoConta situacao = SituacaoConta.valueOf(contaCSV.getSituacao().toUpperCase());

        return new Conta(dataVencimento, dataPagamento, valor, contaCSV.getDescricao(), situacao);
    }

    public static List<Conta> toEntityList(List<ContaCSV> contaCSVList) {
        return contaCSVList.stream()
                .map(ContaMapper::toEntity)
                .collect(Collectors.toList());
    }
}