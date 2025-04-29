package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.*;
import com.project.softwave.backend_SoftWave.repository.UsuarioJuridicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioJuridicoService {
    @Autowired
    private UsuarioJuridicoRepository usuariosJuridicosRepository;

    @Autowired
    private UserValidator validacoesUsuarios;

    public UsuarioJuridico cadastrar(UsuarioJuridico usuarioJuridico) {
            if (usuariosJuridicosRepository.findByEmailEqualsOrCnpjEquals(
                    usuarioJuridico.getEmail(), usuarioJuridico.getCnpj()).isPresent()) {
                throw new EntidadeConflitoException("Email ou CNPJ já cadastrado.");
            }
            usuarioJuridico.setRole(Role.ROLE_USUARIO);
            UsuarioJuridico usuarioJuridicoCadastrado = usuariosJuridicosRepository.save(usuarioJuridico);
            return usuarioJuridicoCadastrado;
    }

    public List<UsuarioJuridico> listar(){

        if (usuariosJuridicosRepository.findAll().isEmpty()){
            throw new EntidadeNaoEncontradaException("Nenhum usuário jurídico encontrado.");
        }

        return usuariosJuridicosRepository.findAll();

    }

    public UsuarioJuridico buscarPorId(Integer id) {
        return usuariosJuridicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário jurídico com ID " + id + " não encontrado."));
    }

    @Transactional
    public UsuarioJuridico atualizar(Integer id, UsuarioJuridicoAtualizacaoDTO dto) {

        UsuarioJuridico usuarioJuridico = usuariosJuridicosRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com id: " + id));

        usuarioJuridico.setNomeFantasia(dto.getNomeFantasia());
        usuarioJuridico.setEmail(dto.getEmail());
        usuarioJuridico.setCnpj(dto.getCnpj());
        usuarioJuridico.setRazaoSocial(dto.getRazaoSocial());
        usuarioJuridico.setTelefone(dto.getTelefone());
        usuarioJuridico.setLogradouro(dto.getLogradouro());
        usuarioJuridico.setCep(dto.getCep());
        usuarioJuridico.setBairro(dto.getBairro());
        usuarioJuridico.setCidade(dto.getCidade());

        return usuariosJuridicosRepository.save(usuarioJuridico);
    }

    public Boolean deletar(Integer id){
        if (usuariosJuridicosRepository.findById(id).isPresent()){
            usuariosJuridicosRepository.deleteById(id);
            return true;
        }
        throw new EntidadeNaoEncontradaException("Usuário jurídico não encontrado.");
    }

}
