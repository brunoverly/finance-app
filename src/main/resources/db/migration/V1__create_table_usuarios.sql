CREATE TABLE usuarios(
    id BIGSERIAL PRIMARY KEY,
    nome varchar(250),
    email varchar(250) UNIQUE,
    senha varchar(400),
    role varchar(50)
)