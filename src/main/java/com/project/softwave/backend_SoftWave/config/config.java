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
        UsuarioFisico usuario1 = new UsuarioFisico(null,"Senha123","email1", "nome1" ,"cpf1", "rg1");
        UsuarioFisico usuario2 = new UsuarioFisico(null,"Senha123","email2", "nome2" ,"cpf2", "rg2");
        UsuarioJuridico usuario3 = new UsuarioJuridico(null,"Senha123","email3", "cnpj1" ,"nomeFantasia1", "razaoSocial1");
        UsuarioJuridico usuario4 = new UsuarioJuridico(null,"Senha123","email4", "cnpj2" ,"nomeFantasia2", "razaoSocial2");
        usuarioFisicoRepository.saveAll(Arrays.asList(usuario1,usuario2));
        usuarioJuridicoRepository.saveAll(Arrays.asList(usuario3,usuario4));

        Setor setor1 = new Setor("Setor1", "Descricao1");
        Setor setor2 = new Setor("Setor1", "Descricao1");
        setorRepository.saveAll(Arrays.asList(setor1,setor2));

        Processo processo1 = new Processo("numero1", "nome1", "descricao1", setor1);
        Processo processo2 = new Processo("numero2", "nome2", "descricao2", setor2);
        processoRepository.saveAll(Arrays.asList(processo1,processo2));

    }
}
