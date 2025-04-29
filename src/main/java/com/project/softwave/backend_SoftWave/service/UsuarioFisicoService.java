package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.*;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.repository.UsuarioFisicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import jakarta.transaction.Transactional;
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
                validarUsuarios.validarSenha(usuarioFisico.getSenha())

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
        throw new DadosInvalidosException("Senha inválida para cadastro");
    }

    public List<UsuarioFisico> listar() {

        List<UsuarioFisico> usuariosFisicos = usuariosFisicosRepository.findAll();

        if (usuariosFisicos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum usuário encontrado");
        }

        return usuariosFisicos;
    }

    public UsuarioFisico buscarPorId(Integer id) {
        return usuariosFisicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário Físico com ID " + id + " não encontrado."));
    }

    @Transactional
    public UsuarioFisico atualizar(Integer id, UsuarioFisicoAtualizacaoDTO dto) {

        UsuarioFisico usuarioFisico = usuariosFisicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com id: " + id));

        usuarioFisico.setNome(dto.getNome());
        usuarioFisico.setEmail(dto.getEmail());
        usuarioFisico.setTelefone(dto.getTelefone());
        usuarioFisico.setLogradouro(dto.getLogradouro());
        usuarioFisico.setCep(dto.getCep());
        usuarioFisico.setBairro(dto.getBairro());
        usuarioFisico.setCidade(dto.getCidade());

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
