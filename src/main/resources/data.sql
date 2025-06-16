-- Inserção de Usuários
INSERT INTO usuario (tipo_usuario, senha, email, role, cep, logradouro, bairro, cidade, complemento, numero, telefone, ativo) VALUES
('advogado_fisico', '12fsr678',
 'joao.silva@email.com', 'ROLE_ADVOGADO','01234-567', 'Rua das Flores', 'Centro', 'São Paulo',
 'Sala 101', '123', '(11) 99999-9999', true),

('advogado_juridico', 'dkl456jf',
 'escritorio@email.com', 'ROLE_ADVOGADO', '04567-890', 'Av. Paulista', 'Bela Vista', 'São Paulo',
 'Andar 15', '1000', '(11) 3333-3333', true),

('usuario_fisico', '12kdsro8',
 'cliente@email.com', 'ROLE_USUARIO', '07890-123', 'Rua dos Clientes', 'Vila Nova', 'São Paulo',
 'Apto 45', '789', '(11) 4444-4444', true);

-- Inserção de Registros Financeiros
INSERT INTO registro_financeiro (descricao, valor, data, usuarios_id, processos_id) VALUES
('Honorários Iniciais', 5000.00, '2024-01-15', 1, 1),
('Custas Processuais', 2500.00, '2024-02-01', 2, 2);

-- Inserção de Comentários do Processo
INSERT INTO comentario_processo (comentario, data_criacao, ultima_movimentacao_id, processo_id, usuario_id) VALUES
('Processo em andamento normal', '2024-03-01 10:00:00', 1, 1, 1),
('Aguardando citação', '2024-03-05 14:30:00', 2, 1, 2);                                                                                                                                               ('2024-03-15 10:00:00', '2024-03-15 11:00:00', 60.0, 'REALIZADA', 'Teams', true, true, 'Apresentação do andamento processual');