package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.repository.UsuarioFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioFisicoService {
    @Autowired
    private UsuarioFisicoRepository usuariosFisicosRepository;

    @Autowired
    private UserValidator validarUsuarios;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioFisico cadastrar(UsuarioFisico usuarioFisico) {
        if (
                validarUsuarios.validarSenha(usuarioFisico.getSenha()) &&
                        validarUsuarios.validarCamposVaziosFisico(
                                usuarioFisico.getNome(),
                                usuarioFisico.getRg()
                        )
        ) {
            if (
                    usuariosFisicosRepository.findByEmailEqualsOrCpfEquals(
                            usuarioFisico.getEmail(),
                            usuarioFisico.getCpf()
                    ).isPresent()
            ) {
                throw new EntidadeConflitoException("Email ou CPF já existe");
            }
            String senhaCriptografada = passwordEncoder.encode(usuarioFisico.getSenha());
            usuarioFisico.setSenha(senhaCriptografada);


            UsuarioFisico usuarioFisicoCadastrado = usuariosFisicosRepository.save(usuarioFisico);

            return usuarioFisicoCadastrado;

        }
        throw new BasicException("Dados do usuário inválidos");
    }

    public List<UsuarioFisicoDTO> listar() {

        List<UsuarioFisico> usuariosFisicos = usuariosFisicosRepository.findAll();

        if (usuariosFisicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum usuário encontrado");
        }

        List<UsuarioFisicoDTO> todosUsuariosFisicos = usuariosFisicos.stream()
                .map(usuarioFisico -> new UsuarioFisicoDTO(usuarioFisico))
                .collect(Collectors.toList());

        return todosUsuariosFisicos;
    }

    public UsuarioFisicoDTO atualizar(Integer id, UsuarioFisicoDTO usuarioFisicoDTO) {

        Optional<UsuarioFisico> usuarioFisicoOptional = usuariosFisicosRepository.findById(id);
        if (usuarioFisicoOptional.isPresent()) {
            if (usuariosFisicosRepository.existsByEmailEqualsOrCpfEqualsAndIdNot(
                    usuarioFisicoDTO.getEmail(),
                    usuarioFisicoDTO.getCpf(),
                    id)) {
                throw new BasicException("Email ou CPF já existe para outro usuário");
            } else if (validarUsuarios.validarSenha(usuarioFisicoDTO.getSenha()) &&
                    validarUsuarios.validarCamposVaziosFisico(
                            usuarioFisicoDTO.getNome(),
                            usuarioFisicoDTO.getRg())) {

                UsuarioFisico usuarioFisicoAtualizado = new UsuarioFisico(usuarioFisicoDTO);
                usuarioFisicoAtualizado.setId(id);

                return new UsuarioFisicoDTO(usuarioFisicoAtualizado);
            } else {
                throw new BasicException("Dados do usuário inválidos");
            }
        } else {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
    }

    public Boolean deletar(Integer id) {
        Optional<UsuarioFisico> usuarioFisicoOptional = usuariosFisicosRepository.findById(id);
        if (usuarioFisicoOptional.isPresent()) {
            usuariosFisicosRepository.deleteById(id);
            return true;
        } else {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado");
        }
    }

    public UsuarioFisicoDTO login(UsuarioFisicoDTO usuarioFisicoDTO) {
        if (usuarioFisicoDTO.getEmail() == null || usuarioFisicoDTO.getSenha() == null) {
            throw new LoginIncorretoException("Email ou senha não podem ser nulos");
        }

        Optional<UsuarioFisico> possivelUsuario =
                usuariosFisicosRepository.findByEmailEqualsAndSenhaEquals(
                        usuarioFisicoDTO.getEmail(),
                        usuarioFisicoDTO.getSenha()
                );

        if (possivelUsuario.isEmpty()) {
            throw new LoginIncorretoException("Email ou senha inválidos");
        }

        UsuarioFisico usuarioFisicoAutendicado = possivelUsuario.get();

        return new UsuarioFisicoDTO(usuarioFisicoAutendicado);
    }
}
