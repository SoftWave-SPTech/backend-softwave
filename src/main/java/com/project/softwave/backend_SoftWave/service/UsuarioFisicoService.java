package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.UsuarioFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioFisicoService {
    @Autowired
    private UsuarioFisicoRepository usuariosFisicosRepository;

    @Autowired
    private UserValidator validarUsuarios;

    public UsuarioFisico cadastrar(UsuarioFisico usuarioFisico) {
        if (
                usuariosFisicosRepository.findByEmailEqualsOrCpfEqualsOrRgEquals(
                    usuarioFisico.getEmail(),usuarioFisico.getCpf(), usuarioFisico.getRg()
                ).isPresent()
        ) {
            throw new EntidadeConflitoException("Email, CPF ou RG já existe");
        }
        usuarioFisico.setRole(Role.ROLE_USUARIO);
        usuarioFisico.setAtivo(false);
        UsuarioFisico usuarioFisicoCadastrado = usuariosFisicosRepository.save(usuarioFisico);
        return usuarioFisicoCadastrado;
    }

    public List<UsuarioFisico> listar() {

        List<UsuarioFisico> usuariosFisicos = usuariosFisicosRepository.findAll();

        if (usuariosFisicos.isEmpty()) {
            throw new NoContentException("Nenhum usuário encontrado!");
        }

        return usuariosFisicos;
    }

    public UsuarioFisico buscarPorId(Integer id) {
        return usuariosFisicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário Físico não encontrado!"));
    }

    @Transactional
    public UsuarioFisico atualizar(Integer id, UsuarioFisicoAtualizacaoDTO dto) {

        UsuarioFisico usuarioFisico = usuariosFisicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        usuarioFisico.setNome(dto.getNome());
        usuarioFisico.setEmail(dto.getEmail());
        usuarioFisico.setTelefone(dto.getTelefone());
        usuarioFisico.setLogradouro(dto.getLogradouro());
        usuarioFisico.setCep(dto.getCep());
        usuarioFisico.setBairro(dto.getBairro());
        usuarioFisico.setCidade(dto.getCidade());
        usuarioFisico.setNumero(dto.getNumero());

        return usuariosFisicosRepository.save(usuarioFisico);
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

}
