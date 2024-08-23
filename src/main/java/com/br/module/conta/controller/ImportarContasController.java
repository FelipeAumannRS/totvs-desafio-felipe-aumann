package com.br.module.conta.controller;

import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.use_case.LerArquivoContaCsvUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas/import")
@RequiredArgsConstructor
public class ImportarContasController {

    private final LerArquivoContaCsvUseCase service;

    @PostMapping("/upload")
    public ResponseEntity<List<Conta>> uploadCsv(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.salvarContas(service.getFile(file)));
    }
}