CREATE TABLE categorias(
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(250),
    usuario_id BIGINT,
    CONSTRAINT fk_usuario
                     FOREIGN KEY (usuario_id)
                     REFERENCES usuarios(id)
)