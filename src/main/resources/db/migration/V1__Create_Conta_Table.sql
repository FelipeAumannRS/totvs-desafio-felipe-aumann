CREATE TABLE conta (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_vencimento DATE           NOT NULL,
    data_pagamento  DATE,
    valor           DECIMAL(19, 2) NOT NULL,
    descricao       VARCHAR(255),
    situacao        VARCHAR(50)    NOT NULL
);