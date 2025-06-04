INSERT INTO usuario (
    tipo_usuario, id, bairro, cep, cidade, complemento, email,
    logradouro, role, senha, telefone,
    cpf, nome, rg, oab, cnpj, nome_fantasia, razao_social, representante, numero
) VALUES
-- Usuário Jurídico
('usuario_juridico', 1, 'Centro', '03471047', 'São Paulo', 'Sala 3, Edifício Central', 'usuario.admin@exemplo.com',
 'Rua das Flores', 0, 'Senha123@', '(11) 91234-5678',
 NULL, NULL, NULL, NULL, '15529313000109', 'Empresa XPTOS2', 'XPTO LTDA', 'João da Silva', 101),

-- Usuário Físico
('usuario_fisico', 2, 'Vila Nova', '01234000', 'São Paulo', 'Apto 12, Bloco A', 'maria.helena@gmail.com',
 'Rua dos Jacarandás', 0, 'Maria123@', '(11) 91234-5678',
 '43293406238', 'Maria Helena Costas', '12.345.678-9', NULL, NULL, NULL, NULL, NULL , 1),

-- Advogado Jurídico
('advogado_juridico', 3, 'Centro', '03471047', 'São Paulo', 'Sala 801, Torre Oeste', 'usuario2.admin@exemplo.com',
 'Rua das Flores', 1, 'SenhaForte@123', '(11) 91234-5678',
 NULL, NULL, NULL, 56789, '96678175000121', 'Empresa XPTOS', 'XPTO LTDA' , 'João da Silva', 102),

-- Advogado Físico
('advogado_fisico', 4, 'Vila Nova', '01234000', 'São Paulo', 'Apto 102, Bloco B', 'maria2.helena@gmail.com',
 'Rua dos Jacarandás', 1, 'SenhaSegura@2024', '(11) 91234-5678',
 '90869258044', 'Maria Helena Costa', '12.345.678-9', 123456, NULL, NULL, NULL , NULL , 2);