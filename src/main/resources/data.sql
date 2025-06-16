-- roles
INSERT INTO tb_role (id, autoridade)
VALUES ('1', 'USUARIO');

INSERT INTO tb_role (id, autoridade)
VALUES ('2', 'ADMIN');

-- usuarios
INSERT INTO tb_usuario (nome_completo, cpf, email, celular, genero, data_nascimento, senha, role_id)
VALUES ('Administrador do Sistema', '00000000000', 'admin@admin.com', '51999999999', 'Outro', '1990-01-01',
        '$2a$12$bgTqpPV29owPWupiw9jAi.7shswOupUnXNhD2wEysucBmVuQLiFC2', 2);

INSERT INTO tb_usuario (nome_completo, cpf, email, celular, genero, data_nascimento, senha, role_id)
VALUES ('Renata Vitória Mattes Fleck', '02079642030', 'renatavitoriamfleck@gmail.com', '51995577707', 'FEMININO',
        '1999-02-01', '$2a$12$zeviyOKj0wcOUhavo3AlCeCiEO3.OdgaNhDlM/RfiHNyYw.9u1oOO', 1);

-- pets
INSERT INTO tb_pet (sexo, raca, pelo, peso, nome, especie, castrado, data_nascimento, porte, usuario_id)
VALUES ('MACHO', 'GOLDEN_RETRIEVER', 'LONGO', 38.7, 'Balu', 'CACHORRO', true, '2023-11-26', 'GRANDE', 2);

INSERT INTO tb_pet (sexo, raca, pelo, peso, nome, especie, castrado, data_nascimento, porte, usuario_id)
VALUES ('MACHO', 'SHIH_TZU', 'CURTO', 9.5, 'Frodo', 'CACHORRO', true, '2023-04-05', 'PEQUENO', 2);

INSERT INTO tb_pet (sexo, raca, pelo, peso, nome, especie, castrado, data_nascimento, porte, usuario_id)
VALUES ('FEMEA', 'MANX', 'CURTO', 9.5, 'Frida', 'GATO', true, '2023-04-05', 'PEQUENO', 2);

-- tipos servico
INSERT INTO tb_tipo_servico (nome, porte, duracao, valor_pacote, valor_individual)
VALUES
    ('Banho', 'PEQUENO', INTERVAL '01:00:00', 35.00, 55.00),
    ('Banho', 'MEDIO', INTERVAL '01:00:00', 45.00, 75.00),
    ('Banho', 'GRANDE', INTERVAL '01:30:00', 75.00, 95.00),
    ('Tosa', 'PEQUENO', INTERVAL '00:30:00', 35.00, 65.00),
    ('Tosa', 'MEDIO', INTERVAL '00:30:00', 45.00, 75.00),
    ('Tosa', 'GRANDE', INTERVAL '00:30:00', 65.00, 95.00),
    ('Hidratação', 'PEQUENO', INTERVAL '00:30:00', 25.00, 35.00),
    ('Hidratação', 'MEDIO', INTERVAL '00:30:00', 35.00, 45.00),
    ('Hidratação', 'GRANDE', INTERVAL '00:30:00', 45.00, 55.00);

INSERT INTO tb_agendamento (data, horario, status, duracao, pet_id)
VALUES ('2025-06-01', '10:00:00', 'PENDENTE', INTERVAL '02:00:00', 1);

INSERT INTO tb_agendamento (data, horario, status, duracao, pet_id)
VALUES ('2025-06-02', '17:00:00', 'PENDENTE', INTERVAL '01:30:00', 2);

INSERT INTO tb_servico (nome, status, agendamento_id, tipo_servico_id)
VALUES ('Banho', 'PENDENTE', 1, 3);

INSERT INTO tb_servico (nome, status, agendamento_id, tipo_servico_id)
VALUES ('Hidratação', 'PENDENTE', 1, 9);

INSERT INTO tb_servico (nome, status, agendamento_id, tipo_servico_id)
VALUES ('Banho', 'PENDENTE', 2, 1);

INSERT INTO tb_servico (nome, status, agendamento_id, tipo_servico_id)
VALUES ('Hidratação', 'PENDENTE', 2, 4);

