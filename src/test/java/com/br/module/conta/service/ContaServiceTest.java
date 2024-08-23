package com.br.module.conta.service;

import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.domain.entity.enums.SituacaoConta;
import com.br.module.conta.repository.ContaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContaServiceTest {

    @InjectMocks
    ContaService service;
    @Mock
    ContaRepository repository;

    @Test
    void test_save() {
        Conta conta = new Conta(LocalDateTime.now(), LocalDateTime.now(), new BigDecimal("100.00"), "Descricao", SituacaoConta.PENDENTE);
        //when(service.save(conta)).thenReturn(conta);

        service.save(conta);

        assertThat(conta).isNotNull();
        verify(repository, times(1)).save(conta);
    }

    @Test
    void test_update() {
        Conta contaExistente = new Conta(LocalDateTime.now(), LocalDateTime.now(), new BigDecimal("100.00"), "Descricao", SituacaoConta.PENDENTE);
        Conta conta = new Conta(LocalDateTime.now(), LocalDateTime.now(), new BigDecimal("200.00"), "Descricao 2", SituacaoConta.PENDENTE);
        Long idContaExistente = 1L;
        when(repository.findById(idContaExistente)).thenReturn(Optional.of(contaExistente));
        //when(service.update(idContaExistente, conta)).thenReturn(conta);

        service.update(idContaExistente, conta);

        assertThat(conta).isNotNull();
        assertThat(contaExistente.getValor()).isEqualTo(repository.findById(idContaExistente).get().getValor());
    }
}