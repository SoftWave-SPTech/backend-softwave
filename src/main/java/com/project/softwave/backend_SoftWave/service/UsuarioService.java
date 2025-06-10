package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.config.GerenciadorTokenJwt;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdClienteInativoAndAtivo;
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


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private EmailService emailService;

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

        String nome = "";
        if (usuarioAutenticado instanceof UsuarioFisico){
             nome = ((UsuarioFisico) usuarioAutenticado).getNome();
        }else {
             nome = ((UsuarioJuridico) usuarioAutenticado).getNomeFantasia();
        }

        return UsuarioTokenDTO.toDTO(usuarioAutenticado, token, role, nome, usuarioAutenticado.getFoto());
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

    @Transactional
    public void solicitarResetSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacaoSenha(token);
        usuario.setDataExpiracaoTokenRecuperacaoSenha(LocalDateTime.now().plusHours(2));
        usuarioRepository.save(usuario);

        emailService.enviarEmailResetSenha(usuario.getEmail(), token);
    }

    @Transactional
    public void resetarSenha(String token, String novaSenha, String novaSenhaConfirma) {
        Usuario usuario = usuarioRepository.findByTokenRecuperacaoSenha(token)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Token inválido"));

        if (novaSenha == null || novaSenhaConfirma == null) {
            throw new ResponseStatusException(400, "Senha e confirmação de senha não podem ser nulas", null);
        }

        if (!novaSenha.equals(novaSenhaConfirma)) {
            throw new ResponseStatusException(400, "As senhas não coincidem", null);
        }

        if (LocalDateTime.now().isAfter(usuario.getDataExpiracaoTokenRecuperacaoSenha())) {
            throw new EntidadeNaoEncontradaException("Token expirado");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setTokenRecuperacaoSenha(null);
        usuario.setDataExpiracaoTokenRecuperacaoSenha(null);
        usuarioRepository.save(usuario);
    }

    public List<QtdClienteInativoAndAtivo> quantidadeClienteInativoAndInativo(){

        List<Usuario> all = usuarioRepository.findAll();
        List<QtdClienteInativoAndAtivo> quantidadeClienteInativoAndInativo = new ArrayList<>();
        Integer ativos = 0;
        Integer inativos = 0;
        QtdClienteInativoAndAtivo clientesAtivos = new QtdClienteInativoAndAtivo("Clientes Ativos");
        QtdClienteInativoAndAtivo clientesInativos = new QtdClienteInativoAndAtivo("Clientes Inativos");

        if(!all.isEmpty()){
            for(Usuario usuarioDaVez : all){
                if (usuarioDaVez.getSenha().length() <= 8 && usuarioDaVez.getSenha().length() > 0){
                    inativos++;
                }else if(usuarioDaVez.getSenha().length() > 8){
                    ativos++;
                }
            }
        }

        clientesAtivos.setQtdClienteAtivoOrInativo(ativos);
        clientesInativos.setQtdClienteAtivoOrInativo(inativos);

        quantidadeClienteInativoAndInativo.add(clientesInativos);
        quantidadeClienteInativoAndInativo.add(clientesAtivos);

        return quantidadeClienteInativoAndInativo;
    }

    public Integer quantidadeAdvogados(){
        Integer qtdAdvogados = usuarioRepository.quantidadeAdvogados();

        if (qtdAdvogados > 0){
            return qtdAdvogados;
        }else{
            return 0;
        }
    }
}
