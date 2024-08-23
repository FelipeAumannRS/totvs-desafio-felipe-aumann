package com.br.module.conta.use_case;

import com.br.module.conta.domain.csv.ContaCSV;
import com.br.module.conta.domain.entity.Conta;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LerArquivoContaCsvUseCase {

    List<ContaCSV> getFile(MultipartFile arquivoCsv) throws IOException;
    List<Conta> salvarContas(List<ContaCSV> contaCSVList);
}
