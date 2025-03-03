package com.project.softwave.backend_SoftWave.config;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class config implements CommandLineRunner {
// Classe para realizar Mocks Etc
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Usuario>  lista = new ArrayList<>();

        Usuario usuario = new Usuario("Bryan");
        Usuario usuario1 = new Usuario("Bea");
        Usuario usuario2 = new Usuario("Sofia");
        Usuario usuario3 = new Usuario("Teste");
        lista.add(usuario);
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);
// Não funcionou esqueci como faz KKKKKKKK



    }
}
