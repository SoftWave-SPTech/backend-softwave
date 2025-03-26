package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.repository.UsuarioFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UsuarioFisicoDTO cadastrar(UsuarioFisicoDTO usuarioFisicoDTO) {
        if (
                validarUsuarios.validarSenha(usuarioFisicoDTO.getSenha()) &&
                        validarUsuarios.validarCamposVaziosFisico(
                                usuarioFisicoDTO.getNome(),
                                usuarioFisicoDTO.getRg()
                        ) &&
                        validarUsuarios.validarEmail(usuarioFisicoDTO.getEmail()) &&
                        validarUsuarios.validarCpf(usuarioFisicoDTO.getCpf())
        ) {
            if (
                    usuariosFisicosRepository.findByEmailEqualsOrCpfEquals(
                            usuarioFisicoDTO.getEmail(),
                            usuarioFisicoDTO.getCpf()
                    ).isPresent()
            ) {
                throw new BasicException("Email ou CPF já existe");
            }

            UsuarioFisico usuarioFisicoCadastrado = usuariosFisicosRepository.save(
                    new UsuarioFisico(usuarioFisicoDTO)
            );

            return new UsuarioFisicoDTO(usuarioFisicoCadastrado);

        }
        throw new BasicException("Dados do usuário inválidos");
    }

    public List<UsuarioFisicoDTO> listar() {

        List<UsuarioFisico> usuariosFisicos = usuariosFisicosRepository.findAll();

        if (usuariosFisicos.isEmpty()) {
            throw new BasicException("Nenhum usuário encontrado");
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
                            usuarioFisicoDTO.getRg()) &&
                    validarUsuarios.validarEmail(usuarioFisicoDTO.getEmail())) {

                UsuarioFisico usuarioFisicoAtualizado = new UsuarioFisico(usuarioFisicoDTO);
                usuarioFisicoAtualizado.setId(id);

                return new UsuarioFisicoDTO(usuarioFisicoAtualizado);
            } else {
                throw new BasicException("Dados do usuário inválidos");
            }
        } else {
            throw new BasicException("Usuário não encontrado");
        }
    }

    public Boolean deletar(Integer id) {
        Optional<UsuarioFisico> usuarioFisicoOptional = usuariosFisicosRepository.findById(id);
        if (usuarioFisicoOptional.isPresent()) {
            usuariosFisicosRepository.deleteById(id);
            return true;
        } else {
            throw new BasicException("Usuário não encontrado");
        }
    }

    public UsuarioFisicoDTO login(UsuarioFisicoDTO usuarioFisicoDTO) {
        if (usuarioFisicoDTO.getEmail() == null || usuarioFisicoDTO.getSenha() == null) {
            throw new BasicException("Email ou senha não podem ser nulos");
        }

        Optional<UsuarioFisico> possivelUsuario =
                usuariosFisicosRepository.findByEmailEqualsAndSenhaEquals(
                        usuarioFisicoDTO.getEmail(),
                        usuarioFisicoDTO.getSenha()
                );

        if (possivelUsuario.isEmpty()) {
            throw new BasicException("Email ou senha inválidos");
        }

        UsuarioFisico usuarioFisicoAutendicado = possivelUsuario.get();

        return new UsuarioFisicoDTO(usuarioFisicoAutendicado);
    }
}
