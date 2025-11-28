package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.config.GerenciadorTokenJwt;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.integracao.S3MicroserviceClient;
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

    @Autowired
    private S3MicroserviceClient s3MicroserviceClient;

    @Value("${file.PASTA_FOTOS_PERFIS}")
    private String PASTA_FOTOS_PERFIS;

    public String atualizarFotoPerfil(Integer id, MultipartFile fotoPerfil) throws IOException {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado!");
        }

        Usuario usuario = usuarioOpt.get();

        // Deleta a foto antiga do S3, se existir
        if (usuario.getFoto() != null) {
            try {
                String key;
                
                // Se é uma URL completa, extrai a key
                if (usuario.getFoto().contains("softwave-arquivos-prod.s3.amazonaws.com")) {
                    key = usuario.getFoto().replace("https://softwave-arquivos-prod.s3.amazonaws.com/", "");
                } else {
                    // Se já é uma key, usa diretamente
                    key = usuario.getFoto();
                }
                
                s3MicroserviceClient.deleteFile(key);
            } catch (Exception e) {
                System.err.println("Erro ao deletar foto antiga do S3: " + e.getMessage());
            }
        }

        // Upload da nova foto para o S3
        String nomeArquivo = "perfil_" + id + "_" + fotoPerfil.getOriginalFilename();
        com.project.softwave.backend_SoftWave.integracao.UploadResponse uploadResponse = s3MicroserviceClient.uploadFile("perfis", fotoPerfil);

        // Salva a key no banco de dados (não a URL completa)
        usuario.setFoto(uploadResponse.getKey());
        usuarioRepository.save(usuario);

        // Retorna URL pré-assinada para uso imediato
        return s3MicroserviceClient.generatePresignedUrl(uploadResponse.getKey());
    }

    public void deletarFotoPerfil(Integer id) throws IOException{
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        // Deleta a foto do S3, se existir
        if (usuario.getFoto() != null) {
            try {
                String key;
                
                // Se é uma URL completa, extrai a key
                if (usuario.getFoto().contains("softwave-arquivos-prod.s3.amazonaws.com")) {
                    key = usuario.getFoto().replace("https://softwave-arquivos-prod.s3.amazonaws.com/", "");
                } else {
                    // Se já é uma key, usa diretamente
                    key = usuario.getFoto();
                }
                
                s3MicroserviceClient.deleteFile(key);
            } catch (Exception e) {
                System.err.println("Erro ao deletar foto do S3: " + e.getMessage());
            }
        }

        usuario.setFoto(null);
        usuarioRepository.save(usuario);
    }

    public String buscarPorId(Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        if (usuario.getFoto() == null) {
            return null;
        }

        // Se a foto é uma key (não contém http), gera URL pré-assinada
        if (!usuario.getFoto().startsWith("http")) {
            return s3MicroserviceClient.generatePresignedUrl(usuario.getFoto());
        }

        // Se já é uma URL, retorna como está (para compatibilidade com dados antigos)
        return usuario.getFoto();
    }
}
