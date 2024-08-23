package com.br.module.conta.service;

import com.br.module.conta.domain.csv.ContaCSV;
import com.br.module.conta.domain.entity.Conta;
import com.br.module.conta.mapper.ContaMapper;
import com.br.module.conta.repository.ContaRepository;
import com.br.module.conta.use_case.LerArquivoContaCsvUseCase;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LerArquivoContaCsvService implements LerArquivoContaCsvUseCase {

    private final ContaRepository repository;

    @Override
    public List<ContaCSV> getFile(MultipartFile arquivoCsv) throws IOException {
        File tempFile = File.createTempFile("LocalDateTime.now().toString()", ".csv");
        arquivoCsv.transferTo(tempFile);
        return readFile(tempFile);
    }

    @Override
    @Transactional
    public List<Conta> salvarContas(List<ContaCSV> contaCSVList) {
        return repository.saveAll(ContaMapper.toEntityList(contaCSVList));
    }

    private List<ContaCSV> readFile(File file) throws IOException {
        try (FileReader fileReader = new FileReader(file, StandardCharsets.UTF_8)) {
            return new CsvToBeanBuilder<ContaCSV>(fileReader)
                    .withSeparator(',')
                    .withType(ContaCSV.class)
                    .build()
                    .parse();
        }
    }
}