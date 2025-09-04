package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.config.GerenciadorTokenJwt;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FotoPerfilService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${file.PASTA_FOTOS_PERFIS}")
    private String PASTA_FOTOS_PERFIS;

    public String atualizarFotoPerfil(Integer id, MultipartFile fotoPerfil) throws IOException {


        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado!");
        }

        Usuario usuario = usuarioOpt.get();

        // Deleta a foto antiga, se existir
        if (usuario.getFoto() != null) {
            Path fotoAntigaPath = Paths.get(usuario.getFoto());
            Files.deleteIfExists(fotoAntigaPath);
        }

        // Cria o diretório, se necessário
        File diretorio = new File(PASTA_FOTOS_PERFIS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        // Salva a nova foto
        String novoNomeArquivo = "perfil_" + id + "_" + fotoPerfil.getOriginalFilename();
        Path caminhoFoto = Paths.get(PASTA_FOTOS_PERFIS, novoNomeArquivo);
        Files.write(caminhoFoto, fotoPerfil.getBytes());

        // Atualiza a URL no banco de dados
        usuario.setFoto(caminhoFoto.toString());
        usuarioRepository.save(usuario);

        return "http://localhost:8080/" + usuario.getFoto();
    }

    public void deletarFotoPerfil(Integer id) throws IOException{
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        Files.deleteIfExists(Paths.get(usuario.getFoto()));
        usuario.setFoto(null);
        usuarioRepository.save(usuario);
    }

    public String buscarPorId(Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        return usuario.getFoto();
    }
}
