CREATE TABLE usuarios(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(250),
    email VARCHAR(250) UNIQUE,
    senha VARCHAR(400),
    role VARCHAR(20)
)