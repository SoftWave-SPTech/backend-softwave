package com.project.softwave.backend_SoftWave.config;

import com.project.softwave.backend_SoftWave.entity.*;
import com.project.softwave.backend_SoftWave.repository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.SetorRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioFisicoRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioJuridicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class config implements CommandLineRunner {
// Classe para realizar Mocks Etc
    @Autowired
    private UsuarioFisicoRepository usuarioFisicoRepository;

    @Autowired
    private UsuarioJuridicoRepository usuarioJuridicoRepository;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Override
    public void run(String... args) throws Exception {
        UsuarioFisico usuario1 = new UsuarioFisico("Senha123", "joao.silva@example.com", "João Silva", "123.456.789-00", "MG-12.345.678");
        UsuarioFisico usuario2 = new UsuarioFisico ("Senha123", "maria.oliveira@example.com", "Maria Oliveira", "987.654.321-00", "SP-98.765.432");
        UsuarioJuridico usuario3 = new UsuarioJuridico( "Senha123", "empresa1@example.com", "12.345.678/0001-99", "Empresa 1", "Empresa 1 Ltda");
        UsuarioJuridico usuario4 = new UsuarioJuridico( "Senha123", "empresa2@example.com", "98.765.432/0001-99", "Empresa 2", "Empresa 2 S.A.");
        usuarioFisicoRepository.saveAll(Arrays.asList(usuario1,usuario2));
        usuarioJuridicoRepository.saveAll(Arrays.asList(usuario3,usuario4));

        Setor setor1 = new Setor("Civil", "Setor responsável por casos civis");
        Setor setor2 = new Setor("Criminal", "Setor responsável por casos criminais");
        Setor setor3 = new Setor("Imobiliário", "Setor responsável por casos imobiliários");
        setorRepository.saveAll(Arrays.asList(setor1, setor2, setor3));

        Processo processo1 = new Processo("2023/0001", "Ação de Cobrança", "Cobrança de dívida", setor1);
        Processo processo2 = new Processo("2023/0002", "Ação Penal", "Crime de furto", setor2);
        Processo processo3 = new Processo("2023/0003", "Ação de Despejo", "Despejo por falta de pagamento", setor3);
        processoRepository.saveAll(Arrays.asList(processo1, processo2, processo3));
    }
}
