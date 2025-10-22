package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.config.GerenciadorTokenJwt;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdClienteInativoAndAtivo;
import com.project.softwave.backend_SoftWave.dto.DocumentoPessoalDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.*;
import com.project.softwave.backend_SoftWave.entity.*;
import com.project.softwave.backend_SoftWave.exception.*;
import com.project.softwave.backend_SoftWave.repository.DocumentoPessoalRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
@AllArgsConstructor
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

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private DocumentoPessoalRepository documentoPessoalRepository;


    public UsuarioTokenDTO autenticar(UsuarioLoginDto usuarioLoginDto) {
        try {
            final UsernamePasswordAuthenticationToken credentials =
                    new UsernamePasswordAuthenticationToken(usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

            final Authentication authentication = this.authenticationManager.authenticate(credentials);

            Usuario usuarioAutenticado = usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Email do usuário não cadastrado!"));

            if (!usuarioAutenticado.getAtivo()) {
                throw new ForbiddenException("Usuário inativo!");
            }

            if(usuarioAutenticado.getTentativasFalhasLogin() >= 3){
                throw new TooManyRequestsException("Muitas tentativas de login! Por favor, faça o reset de senha!");
            }

            // Login bem-sucedido → zera contador
            usuarioAutenticado.setTentativasFalhasLogin(0);
            usuarioRepository.save(usuarioAutenticado);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String tipoUsuario = usuarioAutenticado.getClass().getSimpleName();
            String nome = usuarioAutenticado instanceof UsuarioFisico
                    ? ((UsuarioFisico) usuarioAutenticado).getNome()
                    : ((UsuarioJuridico) usuarioAutenticado).getNomeFantasia();

            final String token = gerenciadorTokenJwt.generateToken(authentication, tipoUsuario, nome, usuarioAutenticado.getId());
            String role = authentication.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("ROLE_USER");

            return UsuarioTokenDTO.toDTO(usuarioAutenticado, token, role, nome, usuarioAutenticado.getFoto());

        } catch (AuthenticationException e) {
            // Senha incorreta → incrementa contador
            usuarioRepository.findByEmail(usuarioLoginDto.getEmail()).ifPresent(usuario -> {
                usuario.setTentativasFalhasLogin(usuario.getTentativasFalhasLogin() + 1);
                usuarioRepository.save(usuario);
            });
            throw new TooManyRequestsException("Muitas tentativas de login! Por favor, faça o reset de senha!");
        }
    }


    public UsuarioLoginDto primeiroAcesso(UsuarioPrimeiroAcessoDTO usuario) {
        if (usuario.getEmail() == null || usuario.getTokenPrimeiroAcesso() == null) {
            throw new LoginIncorretoException("Email e chave de acesso não podem ser nulos");
        }

        Optional<Usuario> possivelUsuario =
                usuarioRepository.findByEmailEqualsAndTokenPrimeiroAcessoEquals(
                            usuario.getEmail(),usuario.getTokenPrimeiroAcesso());
        if (possivelUsuario.isEmpty()) {
            throw new LoginIncorretoException("Email ou chave de acesso inválido");
        }
        UsuarioLoginDto primeiroAcesso = new UsuarioLoginDto(
                possivelUsuario.get().getEmail(),
                possivelUsuario.get().getSenha());

        Boolean usuarioAtivo = usuarioRepository.existsByEmailAndAtivoIsTrue(usuario.getEmail());
        if (usuarioAtivo) {
            throw new ForbiddenException("Usuário Já Ativo!");
        }

        return primeiroAcesso;
        }

    @Transactional
    public void cadastrarSenha(UsuarioSenhaDto usuarioSenhaDto) {
        String email = usuarioSenhaDto.getEmail();
        String senha = usuarioSenhaDto.getSenha();
        String confirmaSenha = usuarioSenhaDto.getConfirmaSenha();

        if (senha == null || confirmaSenha == null) {
            throw new CorpoRequisicaoVazioException("Senha e confirmação de senha não podem ser nulas!");
        }

        if (!senha.equals(confirmaSenha)) {
            throw new DadosInvalidosException("As senhas não coincidem!");
        }

        String senhaCriptografada = passwordEncoder.encode(senha);
        usuarioRepository.updateSenhaByEmail(senhaCriptografada, email);
    }

    @Transactional
    public void editarEmail(String EmailAntigo, String novoEmail) {
        Usuario usuario = usuarioRepository.findByEmail(EmailAntigo)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));
//        System.out.println("Email antigo: " + EmailAntigo);
//        System.out.println("Novo email: " + novoEmail);
        if (usuarioRepository.existsByEmail(novoEmail)) {
            throw new EntidadeConflitoException("Já existe um usuário cadastrado com este email!");
        }


        usuario.setEmail(novoEmail);
        emailService.enviarEmailPrimeiroAcesso(novoEmail, usuario.getTokenPrimeiroAcesso());
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void solicitarResetSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

       String token = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        usuario.setTokenRecuperacaoSenha(token);
        usuario.setDataCriacaoTokenRecuperacaoSenha(LocalDateTime.now());
        usuario.setDataExpiracaoTokenRecuperacaoSenha(LocalDateTime.now().plusMinutes(5));
        usuarioRepository.save(usuario);

        emailService.enviarEmailResetSenha(usuario.getEmail(), token);
    }

    @Transactional
    public void resetarSenha(String token, String novaSenha, String novaSenhaConfirma) {
        Usuario usuario = usuarioRepository.findByTokenRecuperacaoSenha(token)
                .orElseThrow(() -> new TokenExpiradoInvalidoException("Token inválido!"));




        if (novaSenha == null || novaSenhaConfirma == null) {
            throw new DadosInvalidosException("Senha e confirmação de senha não podem ser nulas!");
        }

        if (!novaSenha.equals(novaSenhaConfirma)) {
            throw new DadosInvalidosException("As senhas não coincidem!");
        }

        if (LocalDateTime.now().isAfter(usuario.getDataExpiracaoTokenRecuperacaoSenha())) {
            throw new TokenExpiradoInvalidoException("Token expirado!");
        }

        usuario.setTentativasFalhasLogin(0);
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

    public Integer quantidadeClientes(){
        Integer qtdUsuarios = usuarioRepository.quantidadeClientes();

        if (qtdUsuarios > 0){
            return qtdUsuarios;
        }else{
            return 0;
        }
    }

    public List<UsuarioProcessosDTO> listarUsuariosEProcessos(){

        List<Usuario> todos = usuarioRepository.findAll();

        List<UsuarioProcessosDTO> usuarios = todos.stream()
                .map(UsuarioProcessosDTO::new)
                .toList();

        for (UsuarioProcessosDTO usuarioDaVez : usuarios){
            usuarioDaVez.setProcesos(processoService.listarProcessoPorIdUsuario(usuarioDaVez.getId()));
        }

        return  usuarios;
    }

    public UsuarioDocumentosDTO buscarUsuarioComDocumentos(Integer idUsuario) {
        // 🔍 Buscar o usuário no banco
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + idUsuario));

        // 🔗 Buscar os documentos do usuário
        List<DocumentoPessoal> documentos = documentoPessoalRepository.findByFkUsuarioId(idUsuario);

        // 🔄 Mapear documentos para DTO
        List<DocumentoPessoalDTO> documentosDTO = documentos.stream()
                .map(doc -> new DocumentoPessoalDTO(doc.getId(), doc.getNomeArquivo(), doc.getUrlArquivo()))
                .toList();

        // 🧠 Montar o DTO do usuário
        String nome;
        String nomeFantasia = null;

        if (usuario instanceof UsuarioFisico usuarioFisico) {
            nome = usuarioFisico.getNome();
        } else if (usuario instanceof UsuarioJuridico usuarioJuridico) {
            nome = usuarioJuridico.getRepresentante();
            nomeFantasia = usuarioJuridico.getNomeFantasia(); // opcional, se quiser trazer
        } else {
            nome = null;
        }

        return new UsuarioDocumentosDTO(
                usuario.getId(),
                nome,
                nomeFantasia,
                usuario.getAtivo(),
                usuario.getTelefone(),
                usuario.getEmail(),
                usuario.getFoto(),
                documentosDTO
        );
    }

    public void atualizarRoleUsuario(Integer id, Integer role){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        if(role == 2){
            usuario.setRole(Role.ROLE_ADMIN);
        }else if(role == 1){
            usuario.setRole(Role.ROLE_ADVOGADO);
        }

        usuarioRepository.save(usuario);
    }

    public void atualizarStatusUsuario(Integer id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        usuario.setAtivo(!usuario.getAtivo());

        usuarioRepository.save(usuario);
    }
}
