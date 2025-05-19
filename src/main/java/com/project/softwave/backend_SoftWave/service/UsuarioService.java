package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.config.GerenciadorTokenJwt;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioSenhaDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioTokenDTO;
import com.project.softwave.backend_SoftWave.entity.*;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class UsuarioService {

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

    public UsuarioTokenDTO autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String tipoUsuario = usuarioAutenticado.getClass().getSimpleName();

        final String token = gerenciadorTokenJwt.generateToken(authentication, tipoUsuario);
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        return UsuarioTokenDTO.toDTO(usuarioAutenticado, token, role);
    }

    public UsuarioLoginDto primeiroAcesso(UsuarioLoginDto usuario) {
        if (usuario.getEmail() == null || usuario.getSenha() == null) {
            throw new LoginIncorretoException("Email ou senha não podem ser nulos");
        }

        Optional<Usuario> possivelUsuario =
                usuarioRepository.findByEmailEqualsAndSenhaEquals(
                            usuario.getEmail(),usuario.getSenha());
        if (possivelUsuario.isEmpty()) {
            throw new LoginIncorretoException("Email ou senha inválidos");
        }
        UsuarioLoginDto primeiroAcesso = new UsuarioLoginDto(
                possivelUsuario.get().getEmail(),
                possivelUsuario.get().getSenha());

        return primeiroAcesso;
        }

    @Transactional
    public void cadastrarSenha(UsuarioSenhaDto usuarioSenhaDto) {
        String email = usuarioSenhaDto.getEmail();
        String senha = usuarioSenhaDto.getSenha();
        String confirmaSenha = usuarioSenhaDto.getConfirmaSenha();

        if (senha == null || confirmaSenha == null) {
            throw new ResponseStatusException(400, "Senha e confirmação de senha não podem ser nulas", null);
        }

        if (!senha.equals(confirmaSenha)) {
            throw new ResponseStatusException(400, "As senhas não coincidem", null);
        }

        String senhaCriptografada = passwordEncoder.encode(senha);
        usuarioRepository.updateSenhaByEmail(senhaCriptografada, email);
    }

    public String atualizarFotoPerfil(UsuarioFotoPerfilDTO usuarioFotoPerfilDTO) throws IOException {


        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioFotoPerfilDTO.getId());
        if (usuarioOpt.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
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
        String novoNomeArquivo = "perfil_" + usuarioFotoPerfilDTO.getId()+ "_" + usuarioFotoPerfilDTO.getFotoPerfil().getOriginalFilename();
        Path caminhoFoto = Paths.get(PASTA_FOTOS_PERFIS, novoNomeArquivo);
        Files.write(caminhoFoto, usuarioFotoPerfilDTO.getFotoPerfil().getBytes());

        // Atualiza a URL no banco de dados
        usuario.setFoto(caminhoFoto.toString());
        usuarioRepository.save(usuario);

        return usuario.getFoto();
    }

    public void deletarFotoPerfil(Integer id) throws IOException{
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        Files.deleteIfExists(Paths.get(usuario.getFoto()));
        usuario.setFoto(null);
        usuarioRepository.save(usuario);
    }
}
