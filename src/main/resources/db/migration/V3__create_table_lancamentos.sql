CREATE TABLE lancamentos(
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(400),
    valor NUMERIC(10,2),
    data_lancamento TIMESTAMP,
    tipo VARCHAR(50),
    usuario_id BIGINT,
    categoria_id BIGINT,
    CONSTRAINT fk_usuario
                     FOREIGN KEY (usuario_id)
                     REFERENCES usuarios(id),
    CONSTRAINT fk_categoria
                     FOREIGN KEY (categoria_id)
                     REFERENCES categorias(id)
)