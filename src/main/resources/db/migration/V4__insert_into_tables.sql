INSERT INTO usuarios (nome, email, senha, role) VALUES
('Bruno', 'bruno@email.com', '$2a$12$KbSWCaK2.KFpgRWvSg9QlOP2Sd1nfQkhW3TfpkPAlwpSKmNwFtLBu', 'ADMIN'),
('Joao', 'joao@email.com', '$2a$12$KbSWCaK2.KFpgRWvSg9QlOP2Sd1nfQkhW3TfpkPAlwpSKmNwFtLBu', 'USER'),
('Maria', 'maria@email.com', '$2a$12$KbSWCaK2.KFpgRWvSg9QlOP2Sd1nfQkhW3TfpkPAlwpSKmNwFtLBu', 'USER');

INSERT INTO categorias (nome, usuario_id) VALUES
                                              ('Alimentação', 1),
                                              ('Transporte', 1),
                                              ('Salário', 1),
                                              ('Lazer', 2),
                                              ('Saúde', 2),
                                              ('Investimentos', 2),
                                              ('Educação', 3),
                                              ('Moradia', 3),
                                              ('Freelance', 3),
                                              ('Outros', 1);

INSERT INTO lancamentos (descricao, valor, data_lancamento, tipo, usuario_id, categoria_id) VALUES
                                                                                                ('Salário Abril', 5000.00, NOW(), 'RECEITA', 1, 3),
                                                                                                ('Mercado', 250.75, NOW(), 'DESPESA', 1, 1),
                                                                                                ('Uber', 45.30, NOW(), 'DESPESA', 1, 2),
                                                                                                ('Cinema', 60.00, NOW(), 'DESPESA', 2, 4),
                                                                                                ('Consulta médica', 120.00, NOW(), 'DESPESA', 2, 5),
                                                                                                ('Compra ações', 1000.00, NOW(), 'DESPESA', 2, 6),
                                                                                                ('Curso Java', 300.00, NOW(), 'DESPESA', 3, 7),
                                                                                                ('Aluguel', 1200.00, NOW(), 'DESPESA', 3, 8),
                                                                                                ('Freelance site', 800.00, NOW(), 'RECEITA', 3, 9),
                                                                                                ('Diversos', 150.00, NOW(), 'DESPESA', 1, 10);