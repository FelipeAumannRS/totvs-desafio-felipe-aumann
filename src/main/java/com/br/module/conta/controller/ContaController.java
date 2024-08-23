package com.br.module.conta.controller;

import lombok.RequiredArgsConstructor;
import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;
import com.br.module.conta.use_case.ContaUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Struct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/contas/manage")
@RequiredArgsConstructor
public class ContaController {

    private final ContaUseCase service;

    @PostMapping
    public ResponseEntity<String> cadastrarConta(@RequestBody Conta conta) {
        service.save(conta);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarConta(@PathVariable Long id, @RequestBody Conta conta) {
        service.update(id, conta);
        return ResponseEntity.ok("");
    }

    @PatchMapping("/{id}/situacao")
    public ResponseEntity<String> alterarSituacao(@PathVariable Long id, @RequestParam SituacaoConta situacao) {
        service.changeSituacao(id, situacao);
        return ResponseEntity.ok("");
    }

    @GetMapping
    public ResponseEntity<Page<Conta>> obterContas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestParam(required = false) String descricao,
            Pageable pageable) {
        Page<Conta> contas = service.getContas(dataInicio, dataFim, descricao, pageable);
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> obterContaPorId(@PathVariable Long id) {
        Conta conta = service.getContaById(id);
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/total-pago")
    public ResponseEntity<BigDecimal> obterValorTotalPagoPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        BigDecimal totalPago = service.getTotalPaid(dataInicio, dataFim);
        return ResponseEntity.ok(totalPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarConta(@PathVariable Long id) {
        service.deletarConta(id);
        return ResponseEntity.ok("Conta com ID " + id + " foi deletada com sucesso.");
    }

    @GetMapping("/contagem-de-contas")
    public ResponseEntity<String> contagemDeContas() {
        return ResponseEntity.ok("Total de contas: " + service.obterContagemDeContas());
    }

    @GetMapping("/lista-de-contas-por-situacao")
    public ResponseEntity<Page<Conta>> contasListPorSituacao(@RequestParam SituacaoConta situacao,
                                                             Pageable pageable) {
        return ResponseEntity.ok(service.listContas(situacao, pageable));
    }
}