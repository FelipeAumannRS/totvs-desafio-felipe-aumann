CREATE TABLE conta(
    id              BIGSERIAL PRIMARY KEY,
    data_vencimento DATE,
    data_pagamento  DATE,
    valor           DECIMAL(19, 2),
    descricao       VARCHAR(255),
    situacao        VARCHAR(50)
);
