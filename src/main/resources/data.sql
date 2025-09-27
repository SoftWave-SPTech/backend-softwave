use softwave;
INSERT INTO `softwave`.`usuario`
(id, tipo_usuario, ativo, bairro, cep, cidade, logradouro, numero, email, senha, telefone,
 cpf, nome, rg, oab, role, token_primeiro_acesso,
 cnpj, nome_fantasia, razao_social, representante)
VALUES
    (1,'advogado_fisico', b'0', 'Pinheiros', '05422-000', 'São Paulo', 'Rua dos Pinheiros', '123',
     'leticiasantos08032005@gmail.com', 'Senha@1234', '11911112222',
     '98765432100', 'Leticia Fonseca', '7654321', 123456, 1,
     'apy2ocT', NULL, NULL, NULL, NULL),

    (2,'usuario_fisico', b'0', 'Bela Vista', '01310-200', 'São Paulo', 'Avenida Paulista', '200',
     'maria.souza@email.com', 'Senha@1234', '11922223333',
     '46009286875', 'Maria Souza', '11523344', NULL, 0,
     'lz88fJQT', NULL, NULL, NULL, NULL),

    (3,'advogado_juridico', b'0', 'Bela Vista', '01310-200', 'São Paulo', 'Rua Haddock Lobo', '150',
     'felipe@laurianoleao.com', 'Senha@1234', '11999998888',
     NULL, 'Felipe Lauriano Rocha Marqueze', NULL, 509556, 3,
     'bz88fCQT', '57404168000180', 'Lauriano & Leão Sociedade de Advogados', 'Lauriano & Leão Sociedade de Advogados', 'Felipe Lauriano Rocha Marqueze'),

    (4,'usuario_juridico', b'0', 'Bela Vista', '01310-200', 'São Paulo', 'Rua Haddock Lobo', '155',
     'raissa@laurianoleao.com', 'Senha@1234', '11988887777',
     NULL, 'Raissa Leão Marqueze', NULL, NULL, 2,
     'kT72xQpL', '57404168250180', 'Lauriano & Leão Sociedade de Advogados', 'Lauriano & Leão Sociedade de Advogados', 'Raissa Leão Marqueze'),

    (5,'advogado_juridico', b'0', 'Savassi', '30140-000', 'Belo Horizonte', 'Rua da Bahia', '500',
     'dono@escritorio.com', 'Senha@1234', '31955556666',
     NULL, 'Escritório Dono Advogados', NULL, 654621, 1,
     'Lp99zVwR', '44332211000177', 'Escritório Dono', 'Escritório Dono Advogados', 'Maria Oliveira'),

    (6,'advogado_juridico', b'0', 'Moema', '04077-000', 'São Paulo', 'Avenida Ibirapuera', '900',
     'advocacia@silvaealmeida.com', 'Senha@1234', '11944445555',
     NULL, 'Silva & Almeida Advocacia', NULL, 856321, 1,
     'Qm44sXyT', '98765432000155', 'Silva & Almeida', 'Silva & Almeida Sociedade de Advogados', 'José Almeida'),

    (7,'usuario_fisico', b'0', 'Santana', '02012-020', 'São Paulo', 'Rua Voluntários da Pátria', '321',
     'joao.pereira@email.com', 'Senha@1234', '11987654321',
     '12345678910', 'João Pereira', '44556677', NULL, 0,
     'Rt21pKLM', NULL, NULL, NULL, NULL),

    (8,'usuario_fisico', b'0', 'Centro', '20040-010', 'Rio de Janeiro', 'Rua do Ouvidor', '45',
     'ana.costa@email.com', 'Senha@1234', '21999887766',
     '10987654321', 'Ana Costa', '88997766', NULL, 0,
     'Wq77nDHF', NULL, NULL, NULL, NULL),

    (9,'usuario_fisico', b'0', 'Boa Viagem', '51020-020', 'Recife', 'Avenida Conselheiro Aguiar', '101',
     'marcos.silva@email.com', 'Senha@1234', '81988776655',
     '32165498700', 'Marcos Silva', '22334455', NULL, 0,
     'Jk55vTRE', NULL, NULL, NULL, NULL),

    (10,'advogado_fisico', b'0', 'Lapa', '05075-010', 'São Paulo', 'Rua Guaicurus', '77',
     'carla.advogada@email.com', 'Senha@1234', '11911223344',
     '98732165400', 'Carla Menezes', '88667788', 785412, 1,
     'Gh83pZQT', NULL, NULL, NULL, NULL),

    (11,'advogado_fisico', b'0', 'Barra', '22631-000', 'Rio de Janeiro', 'Avenida das Américas', '1500',
     'ricardo.advogado@email.com', 'Senha@1234', '21933445566',
     '65498732100', 'Ricardo Oliveira', '11224455', 963258, 1,
     'Bn67xWEP', NULL, NULL, NULL, NULL),

    (12,'advogado_fisico', b'0', 'Savassi', '30140-110', 'Belo Horizonte', 'Rua Antônio de Albuquerque', '88',
     'fernanda.adv@email.com', 'Senha@1234', '31988776655',
     '74185296300', 'Fernanda Rocha', '66778899', 741852, 1,
     'Vc11mXLO', NULL, NULL, NULL, NULL);


INSERT INTO processo
(id, apensado, area, assunto, autor, classe, controle, descricao, distribuicao, executado, foro, indiciado, juiz, normalizado_valor_acao, numero_processo, requerente, requerido, valor_acao, vara)
VALUES
    (1, 'Não informado', 'Cível', 'Indenização por Dano Material', 'Não informado', 'Procedimento Comum Cível', '2025/000844', 'Caso de trabalhos', '08/04/2025 às 20:03 - Livre', 'Não informado', 'Foro Central Cível', 'Não indiciado', 'Andrea de Abreu', 84418.62, '1046526-28.2025.8.26.0100', 'Nelson Missaglia Advogado: Felipe Lauriano Rocha Marqueze', 'João Ricardo Martins Pinheiro Advogado: Glauco Castiglioni Cerri', 84418.62, '10ª Vara Cível'),
    (2, 'Não informado', 'Cível', 'Cobrança', 'Não informado', 'Procedimento Comum Cível', '2024/003429', 'Direito da mulher', '30/09/2024 às 21:32 - Livre', 'Não informado', 'Foro Central Cível', 'Não indiciado', 'Adriana Sachsida Garcia', 22680.00, '1157885-17.2024.8.26.0100', 'Qunto Andar- Grpqa Ltda Advogado: Celso de Faria Monteiro', 'Ernani Dorador Martinez Advogado: Jarbas Alessandro Rocha Marqueze Advogada: Karla Janayna Rocha Marqueze Advogado: Felipe Lauriano Rocha Marqueze', 22680.00, '34ª Vara Cível'),
    (3, 'Não informado', 'Cível', 'Transação', 'Não informado', 'Execução de Título Extrajudicial', '2022/000082', 'Processos', '20/01/2022 às 16:11 - Livre', 'Não informado', 'Foro Central Cível', 'Não indiciado', 'PAULA DA ROCHA E SILVA', 8762.76, '1004117-42.2022.8.26.0100', 'Não informado', 'Não informado', 8762.76, '36ª Vara Cível'),
    (4, 'Não informado', 'Cível', 'Rescisão do contrato e devolução do dinheiro', 'Não informado', 'Procedimento do Juizado Especial Cível', '2025/005651', 'Caso de trabalho', '08/05/2025 às 13:11 - Livre', 'Não informado', 'Foro Central Juizados Especiais Cíveis', 'Não indiciado', 'Thania Pereira Teixeira De Carvalho Cardin', 8840.00, '1006105-78.2025.8.26.0008', 'Beatriz Araujo da Silva Advogada: Thaís Duarte Costa Advogado: Pedro Henrique Silva de Sales', 'Alexandre Magno Feola Advogado: Felipe Lauriano Rocha Marqueze', 8840.00, '1ª Vara do Juizado Especial Cível - Vergueiro'),
    (5, 'Não informado', 'Cível', 'Inclusão Indevida em Cadastro de Inadimplentes', 'Não informado', 'Procedimento do Juizado Especial Cível', '2024/002383', 'Direito da mulher', '14/02/2024 às 20:00 - Livre', 'Não informado', 'Foro Central Juizados Especiais Cíveis', 'Não indiciado', 'Gabriela Afonso Adamo Ohanian', 35759.52, '1003560-45.2024.8.26.0016', 'Beatriz dos Santos Cezarano Advogado: Altair Ferreira', 'Leonardo Troiano Advogada: Karla Janayna Rocha Marqueze', 35759.52, '2ª Vara do Juizado Especial Cível - Vergueiro'),
    (6, 'Não informado', 'Criminal', 'Roubo', 'Não informado', 'Ação Penal - Procedimento Ordinário', '2024/000479', 'Direito da mulher', '26/02/2024 às 12:59 - Livre', 'Não informado', 'Foro de Carapicuíba', 'Não indiciado', 'CAMILE DE LIMA E SILVA BONILHA', 0.00, '1500481-56.2024.8.26.0127', 'Não informado', 'Não informado', NULL, '2ª Vara Criminal'),
    (7, 'Não informado', 'Cível', 'Práticas Abusivas', 'Não informado', 'Procedimento Comum Cível', '2024/001667', 'Prática abusiva', '07/08/2024 às 00:04 - Livre', 'Não informado', 'Foro de Mongaguá', 'Não indiciado', 'NATÁLIA DOMINGUES TAKAKI', 81745.10, '1002506-61.2024.8.26.0366', 'Jarbas Marqueze Advogado: Jarbas Alessandro Rocha Marqueze Advogada: Karla Janayna Rocha Marqueze Advogado: Felipe Lauriano Rocha Marqueze', 'Condominio Edificio Residencial Miami & Florida Advogado: Carlos Alberto Fernandes da Silva Advogado: Artur Fernandes Campos Rodrigues', 81745.10, '2ª Vara'),
    (8, 'Não informado', 'Cível', 'DIREITO PROCESSUAL CIVIL E DO TRABALHO', 'Não informado', 'Procedimento Comum Cível', '2023/002051', NULL, '04/10/2023 às 16:04 - Livre', 'Não informado', 'Foro de Presidente Prudente', 'Não indiciado', 'LUIZ AUGUSTO ESTEVES DE MELLO', 91200.00, '1019300-37.2023.8.26.0482', 'Edielson de Oliveira da Silva Advogado: Bruno Bravo Estacio', 'Não informado', 91200.00, '1ª Vara Cível'),
    (9, 'Não informado', 'Cível', 'Seguro', 'Não informado', 'Monitória', '2019/000510', NULL, '25/02/2019 às 12:48 - Livre', 'Não informado', 'Foro Regional de Vila Mimosa', 'Não indiciado', 'DANIELLA APARECIDA SORIANO UCCELLI', 14591.55, '1001275-45.2019.8.26.0084', 'BRADESCO SAÚDE S/A Advogado: Walter Roberto Hee Advogado: Walter Roberto Lodi Hee', 'Americaexport - Exportação e Importação Eireli Advogado: Gilmar Luiz Panatto', 14591.55, '4ª Vara'),
    (10, 'Não informado', 'Cível', 'Levantamento de Valor', 'Não informado', 'Alvará Judicial - Lei 6858/80', '2024/002371', NULL, '27/09/2024 às 12:19 - Livre', 'Não informado', 'Foro Regional IX - Vila Prudente', 'Não indiciado', 'Claudia Akemi Okoda Oshiro Kato', 1000.00, '1013970-86.2024.8.26.0009', 'Não informado', 'Não informado', 1000.00, '4ª Vara Cível'),
    (11, 'Não informado', 'Cível', 'Contratos Bancários', 'Não informado', 'Procedimento Comum Cível', '2024/001791', NULL, '15/07/2024 às 10:16 - Livre', 'Não informado', 'Foro Regional IX - Vila Prudente', 'Não indiciado', 'Cristiane Sampaio Alves Mascari Bonilha', 58559.52, '1010105-55.2024.8.26.0009', 'Luiza Maria da Silva de Souza Advogado: Felipe Lauriano Rocha Marqueze', 'Não informado', 58559.52, '3ª Vara Cível'),
    (12, 'Não informado', 'Cível', 'Locação de Imóvel', 'Não informado', 'Procedimento do Juizado Especial Cível', '2025/000809', NULL, '24/02/2025 às 10:59 - Livre', 'Não informado', 'Foro Regional VI - Penha de França', 'Não indiciado', 'GUSTAVO SAMPAIO CORREIA', 26456.23, '1021187-86.2024.8.26.0008', 'Não informado', 'Não informado', 26456.23, '1ª Vara do Juizado Especial Cível');


INSERT INTO ultimas_movimentacoes
(id, data, movimento, processo_id)
VALUES
    (1, '2025-08-28', 'Certidão de Publicação Expedida Relação: 1290/2025 Data da Publicação: 29/08/2025', 1),
    (2, '2025-08-27', 'Remetido ao DJE Relação: 1290/2025 Teor do ato: Vistos. Às contrarrazões, no prazo previsto em lei. Decorrido o prazo, encaminhem-se os autos à Superior Instância para análise do apelo. Após a distribuição do recurso no Tribunal, atentem-se as partes para o correto peticionamento, uma vez que as peças deverão ser protocoladas eletronicamente em Segunda Instância. Intime-se. Advogados(s): Glauco Castiglioni Cerri (OAB 462550/SP), Felipe Lauriano Rocha Marqueze (OAB 509556/SP)', 1),
    (3, '2025-08-26', 'Proferido Despacho de Mero Expediente Vistos. Às contrarrazões, no prazo previsto em lei. Decorrido o prazo, encaminhem-se os autos à Superior Instância para análise do apelo. Após a distribuição do recurso no Tribunal, atentem-se as partes para o correto peticionamento, uma vez que as peças deverão ser protocoladas eletronicamente em Segunda Instância. Intime-se.', 1),
    (4, '2025-08-26', 'Conclusos para Despacho', 1),
    (5, '2025-08-25', 'Apelação/Razões Juntada Nº Protocolo: WJMJ.25.41981548-6 Tipo da Petição: Razões de Apelação Data: 25/08/2025 19:15', 1),
    (11, '2025-08-20', 'Conclusos para Despacho', 3),
    (12, '2025-08-18', 'Impugnação à Exceção de Pré-Executividade Juntada Nº Protocolo: WJMJ.25.41919689-1 Tipo da Petição: Impugnação à Exceção de Pré-Executividade Data: 18/08/2025 17:48', 3),
    (13, '2025-07-25', 'Certidão de Publicação Expedida Relação: 0740/2025 Data da Publicação: 28/07/2025', 3),
    (14, '2025-07-17', 'Exceção de Pré-Executividade Juntada Nº Protocolo: WJMJ.25.41652400-6 Tipo da Petição: Exceção de Pré-Executividade Data: 17/07/2025 16:33', 3),
    (15, '2025-08-25', 'Petição Juntada Nº Protocolo: WJEC.25.70134732-0 Tipo da Petição: Petição Intermediária Data: 25/08/2025 11:21', 4),
    (16, '2025-08-21', 'Conclusos para Despacho', 4),
    (17, '2025-08-20', 'Réplica Juntada Nº Protocolo: WJEC.25.70133440-6 Tipo da Petição: Manifestação Sobre a Contestação Data: 20/08/2025 23:01', 4),
    (18, '2025-08-20', 'Réplica Juntada Nº Protocolo: WJEC.25.70133438-4 Tipo da Petição: Manifestação Sobre a Contestação Data: 20/08/2025 22:55', 4),
    (19, '2025-08-20', 'Petição Juntada Nº Protocolo: WJEC.25.70133395-7 Tipo da Petição: Pedido de Juntada de Procuração/Substabelecimento Data: 20/08/2025 19:33', 4),
    (20, '2025-09-05', 'Remetido ao DJE Relação: 1648/2025 Teor do ato: Vistos. Intime-se a parte autora, por carta, para que apresente o necessário ao levantamento do valor depositado pela requerida, ante o não cumprimento do determinado no ato ordinatório pelo patrono constituído. Intimem-se. Advogados(s): Marcus Frederico Botelho Fernandes (OAB 119851/SP), Lucas Renault Cunha (OAB 138675/SP), Karla Janayna Rocha Marqueze (OAB 179001/SP), Adriano Mendonça Rodrigues (OAB 310296/SP), Altair Ferreira (OAB 479713/SP), Felipe Lauriano Rocha Marqueze (OAB 509556/SP)', 5),
    (21, '2025-09-05', 'Carta de Intimação Expedida Processo Digital - Carta - Intimação - Despacho-Ato Ordinatório - Juizado', 5),
    (22, '2025-09-05', 'Proferidas Outras Decisões não Especificadas Vistos. Intime-se a parte autora, por carta, para que apresente o necessário ao levantamento do valor depositado pela requerida, ante o não cumprimento do determinado no ato ordinatório pelo patrono constituído. Intimem-se.', 5),
    (23, '2025-09-02', 'Conclusos para Decisão', 5),
    (24, '2025-05-31', 'Certidão de Publicação Expedida Relação: 0582/2025 Data da Publicação: 29/05/2025', 5),
    (25, '2025-07-23', 'Certidão de Cartório Expedida Certidão - Genérica', 6),
    (26, '2025-07-23', 'Desmembramento de Feitos Processo desmembrado para 0003804-12.2025.8.26.0127, em relação a(s) parte(s) ALEXANDRE DOS SANTOS SOUZA, Justiça Pública', 6),
    (27, '2025-07-23', 'Documento Juntado', 6),
    (28, '2025-07-23', 'Folha de Antecedentes Juntada', 6),
    (29, '2025-07-23', 'Protocolo Juntado', 6),
    (30, '2025-06-13', 'Remetidos os Autos para o Tribunal de Justiça/Colégio Recursal - Processo Digital', 7),
    (31, '2025-06-13', 'Certidão de Cartório Expedida Certidão - Remessa dos Autos à 2ª Instância - Art. 102 - NSCGJ', 7),
    (32, '2025-06-13', 'Planilha de Cálculos Juntada', 7),
    (33, '2025-03-12', 'Contrarrazões Juntada Nº Protocolo: WMGG.25.70007909-7 Tipo da Petição: Contrarrazões de Apelação Data: 12/03/2025 10:25', 7),
    (34, '2025-08-12', 'Petição Juntada Nº Protocolo: WPPE.25.70206654-5 Tipo da Petição: Petições Diversas Data: 12/08/2025 13:37', 8),
    (35, '2025-07-24', 'Mandado Devolvido Cumprido Positivo Certidão - Oficial de Justiça - Mandado Cumprido Positivo', 8),
    (36, '2025-07-24', 'Mandado Juntado', 8),
    (37, '2025-07-03', 'Mandado Expedido Mandado nº: 482.2025/030054-0 Situação: Cumprido - Ato positivo em 23/07/2025 Local: Oficial de justiça - Maria Luisa Galdino', 8),
    (38, '2025-07-02', 'Certidão de Publicação Expedida Relação: 0607/2025 Data da Publicação: 03/07/2025', 8),
    (39, '2022-04-28', 'Arquivado Definitivamente', 9),
    (40, '2022-04-28', 'Certidão de Cartório Expedida Certidão de Cartório - CUSTAS - Certidão de Pagamento de Custas e Arquivamento - Cível - 61615', 9),
    (41, '2022-04-28', 'Trânsito em Julgado às partes - Proc. em Andamento CERTIDÃO - TRÂNSITO EM JULGADO Certifico e dou fé que a r. sentença de fls. 230 transitou em julgado em 25/02/2022. Nada Mais. Campinas, 28 de abril de 2022. Eu, ___, Juliana Ozawa Nishizaki, Escrevente Técnico Judiciário.', 9),
    (42, '2022-04-27', 'Apensado ao processo Apenso o processo 0001031-31.2022.8.26.0084 - Classe: Cumprimento de sentença - Assunto principal: Seguro', 9),
    (43, '2022-04-27', 'Início da Execução Juntado 0001031-31.2022.8.26.0084 - Cumprimento de sentença', 9),
    (44, '2025-08-22', 'Certidão de Publicação Expedida Relação: 1035/2025 Data da Publicação: 25/08/2025', 10),
    (45, '2025-08-21', 'Remetido ao DJE Relação: 1035/2025 Teor do ato: Vistos. Diante da certidão de fl. 75, JULGO EXTINTO o presente processo, sem apreciação de mérito, com fundamento no artigo 485, inciso I do Código de Processo Civil (petição inicial inepta), arcando a parte autora com as custas e despesas processuais já recolhidas. Transitada em julgado, arquivem-se definitivamente os autos, dando-se baixa na distribuição. P.R.I. Advogados(s): Felipe Lauriano Rocha Marqueze (OAB 509556/SP)', 10),
    (46, '2025-08-21', 'Indeferida a Petição Inicial sem Resolução do Mérito Vistos. Diante da certidão de fl. 75, JULGO EXTINTO o presente processo, sem apreciação de mérito, com fundamento no artigo 485, inciso I do Código de Processo Civil (petição inicial inepta), arcando a parte autora com as custas e despesas processuais já recolhidas. Transitada em julgado, arquivem-se definitivamente os autos, dando-se baixa na distribuição. P.R.I.', 10),
    (47, '2025-08-19', 'Conclusos para Decisão', 10),
    (48, '2025-08-07', 'Conclusos para Despacho', 10),
    (49, '2025-09-05', 'Conclusos para Sentença', 11),
    (50, '2025-07-16', 'Petição Juntada Nº Protocolo: WVIP.25.70109489-8 Tipo da Petição: Petições Diversas Data: 16/07/2025 12:14', 11),
    (51, '2025-07-15', 'Certidão de Publicação Expedida Relação: 0759/2025 Data da Publicação: 16/07/2025', 11),
    (52, '2025-07-14', 'Remetido ao DJE Relação: 0759/2025 Teor do ato: Deverá a autora cumprir corretamente a decisão de fls. 206, juntando as três últimas declarações de imposto de renda, pois os documentos juntados a fls. 210/212 são comprovantes de rendimentos, no prazo de 5 dias. Advogados(s): Roberta Beatriz do Nascimento (OAB 192649/SP), Felipe Lauriano Rocha Marqueze (OAB 509556/SP)', 11),
    (53, '2025-07-14', 'Ato Ordinatório - Intimação - DJE Deverá a autora cumprir corretamente a decisão de fls. 206, juntando as três últimas declarações de imposto de renda, pois os documentos juntados a fls. 210/212 são comprovantes de rendimentos, no prazo de 5 dias.', 11),
    (54, '2025-07-25', 'Petição Juntada Nº Protocolo: WPEN.25.70136774-6 Tipo da Petição: Petições Diversas Data: 25/07/2025 12:41', 12),
    (55, '2025-06-27', 'Certidão de Publicação Expedida Relação: 0539/2025 Data da Publicação: 30/06/2025', 12),
    (56, '2025-06-26', 'Remetido ao DJE Relação: 0539/2025 Teor do ato: Vistos. Ante o teor de fls. 145/150, reputo justificada a ausência da coautora Giucione à audiência de conciliação. Sem prejuízo, uma vez tendo sido a carta expedida para fins citatórios, malgrado entregue, recebida por pessoa cujo sobrenome sequer coincide com o da ré Mônica (fl. 135) e consubstanciando a citação pressuposto de constituição e desenvolvimento válido do processo, expeça-se mandado para fins de constatação, que deverá ser cumprido na Rua Argeu Guimarães, 100, Vila Costa Melo, São Paulo - SP, CEP 03625-020. Restando negativa a diligência anteriormente determinada, deverão os autores informar o atual endereço da corré ou requerer as medidas que entenderem cabíveis para sua localização. Int. Advogados(s): Felipe Lauriano Rocha Marqueze (OAB 509556/SP)', 12),
    (57, '2025-06-26', 'Proferido Despacho de Mero Expediente Vistos. Ante o teor de fls. 145/150, reputo justificada a ausência da coautora Giucione à audiência de conciliação. Sem prejuízo, uma vez tendo sido a carta expedida para fins citatórios, malgrado entregue, recebida por pessoa cujo sobrenome sequer coincide com o da ré Mônica (fl. 135) e consubstanciando a citação pressuposto de constituição e desenvolvimento válido do processo, expeça-se mandado para fins de constatação, que deverá ser cumprido na Rua Argeu Guimarães, 100, Vila Costa Melo, São Paulo - SP, CEP 03625-020. Restando negativa a diligência anteriormente determinada, deverão os autores informar o atual endereço da corré ou requerer as medidas que entenderem cabíveis para sua localização. Int.', 12),
    (58, '2025-06-25', 'Conclusos para Despacho', 12);

INSERT INTO usuarios_processos (usuario_id, processo_id)
VALUES
-- Processo 1
(3, 1),
(1, 1),
-- Processo 2
(3, 2),
(4, 2),
-- Processo 3
(1, 3),
-- Processo 4
(2, 4),
-- Processo 5
(5, 5),
(4, 5),
-- Processo 6
(6, 6),
-- Processo 7
(9, 7),
(12, 7),
-- Processo 8
(10, 8),
-- Processo 9
(11, 9),
-- Processo 10
(3, 10),
-- Processo 11
(3, 11),
(2, 11),
-- Processo 12
(7, 12),
(8, 12),
(9, 12);
INSERT INTO softwave.comentario_processo
(usuario_id, processo_id, ultima_movimentacao_id, comentario, data_criacao)
VALUES
-- Comentários vinculados ao processo
(1, 1, NULL, 'Analisados os documentos apresentados pelo autor; recomenda-se solicitar esclarecimentos adicionais sobre a petição inicial.', '2025-01-10 09:15:22'),
(3, 2, NULL, 'Verificada a execução de cobrança; sugerido envio de notificação extrajudicial ao réu antes de prosseguir com a ação.', '2025-01-11 14:47:39'),
(5, 5, NULL, 'Avaliação preliminar da inclusão indevida em cadastro de inadimplentes; preparar contestação fundamentada.', '2025-01-13 10:28:11'),
(6, 6, NULL, 'Análise do boletim de ocorrência e laudos apresentados; providenciar juntada de novas provas.', '2025-01-15 16:02:44'),
(12, 7, NULL, 'Verificados contratos e cláusulas de prática abusiva; sugerida estratégia de defesa baseada em precedentes judiciais.', '2025-01-17 11:37:05'),
(10, 8, NULL, 'Revisão da documentação processual; observar prazos para apresentação de contestações e manifestações.', '2025-01-19 08:56:42'),
(11, 9, NULL, 'Analisado seguro contestado; recomendada preparação de laudo técnico para reforço do argumento do autor.', '2025-01-21 15:29:13'),
(3, 10, NULL, 'Levantamento do valor a ser liberado via alvará judicial; verificar eventuais impugnações antes da requisição.', '2025-01-24 10:42:51'),
(3, 11, NULL, 'Revisão do contrato bancário questionado; preparar memoriais para audiência de conciliação.', '2025-01-27 13:18:37'),
(1, 12, NULL, 'Analisada locação de imóvel e cumprimento de cláusulas; sugerida notificação ao locatário para regularização.', '2025-01-30 17:05:29');

-- Comentários vinculados à última movimentação
INSERT INTO softwave.comentario_processo
(usuario_id, processo_id, ultima_movimentacao_id, comentario, data_criacao)
VALUES
    (1, NULL, 1, 'Verificada certidão de publicação; recomendar acompanhamento de prazo para manifestações futuras.', '2025-02-02 09:12:23'),
    (3, NULL, 2, 'Despacho do juiz analisado; sugerido envio de contrarrazões dentro do prazo legal.', '2025-02-04 11:46:51'),
    (5, NULL, 20, 'Intimação recebida; providenciar resposta documental e atualização do cliente sobre prazos.', '2025-02-07 10:04:39'),
    (6, NULL, 25, 'Juntada de documentos ao processo; revisar consistência e validade das provas apresentadas.', '2025-02-10 16:58:02'),
    (9, NULL, 30, 'Planilha de cálculos conferida; elaborar parecer sobre valores devidos e possíveis ajustes.', '2025-02-13 12:37:48'),
    (10, NULL, 34, 'Petição juntada; analisar conteúdo e preparar eventual manifestação do escritório.', '2025-02-16 18:25:31'),
    (11, NULL, 49, 'Conclusos para sentença; revisar todo o histórico processual e preparar eventuais recursos ou manifestações.', '2025-02-20 20:41:10');

INSERT INTO registro_financeiro (
    id, ano, honorario_sucumbencia, mes, metodo_pagamento, parcela_atual,
    status_financeiro, tipo_pagamento, total_parcelas, valor_pagar, valor_pago,
    valor_parcela, cliente_id, processo_id
) VALUES
      (7, 2025, 0.1, 0, 0, 1, 0, 0, 12, 1000, 200, 100, 4, 5),
      (8, 2025, 0.1, 1, 1, 2, 0, 1, 6, 600, 150, 100, 2, 4),
      (9, 2025, 0.1, 2, 2, 3, 1, 0, 3, 300, 100, 100, 7, 12),
      (10, 2025, 0.1, 3, 0, 1, 0, 1, 10, 1000, 400, 100, 8, 12),
      (11, 2025, 0.1, 4, 1, 5, 1, 0, 5, 500, 200, 100, 9, 12),
      (12, 2025, 0.1, 5, 2, 6, 0, 1, 8, 800, 300, 100, 2, 11);
