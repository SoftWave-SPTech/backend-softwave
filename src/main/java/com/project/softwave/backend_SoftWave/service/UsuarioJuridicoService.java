package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.BasicException;
import com.project.softwave.backend_SoftWave.repository.UsuarioJuridicoRepository;
import com.project.softwave.backend_SoftWave.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioJuridicoService {
    @Autowired
    private UsuarioJuridicoRepository usuariosJuridicosRepository;

    @Autowired
    private UserValidator validacoesUsuarios;

    public UsuarioJuridico cadastrar(UsuarioJuridico usuarioJuridico) {
        if (
                validacoesUsuarios.validarSenha(usuarioJuridico.getSenha()) &&
                        validacoesUsuarios.validarCamposVaziosJuridico(
                                usuarioJuridico.getNomeFantasia(),
                                usuarioJuridico.getRazaoSocial(),
                                usuarioJuridico.getCnpj()
                        )
        ) {
            if (
                    usuariosJuridicosRepository.findByEmailEqualsOrCnpjEquals(
                            usuarioJuridico.getEmail(),
                            usuarioJuridico.getCnpj()
                    ).isPresent()
            ) {
                throw new BasicException("Email ou CNPJ já cadastrado.");
            }

            UsuarioJuridico usuarioJuridicoCadastrado = usuariosJuridicosRepository.save(usuarioJuridico);

            return usuarioJuridicoCadastrado;

        }
        throw new BasicException("Dados inválidos para cadastro.");
    }

    public List<UsuarioJuridicoDTO> listar(){

        if (usuariosJuridicosRepository.findAll().isEmpty()){
            throw new BasicException("Nenhum usuário jurídico encontrado.");
        }

        List<UsuarioJuridicoDTO> todosUsuariosJuridicos =
                usuariosJuridicosRepository.findAll()
                        .stream()
                        .map(usuarioJuridico -> new UsuarioJuridicoDTO(usuarioJuridico))
                        .collect(Collectors.toList());

        return todosUsuariosJuridicos;
    }

    public UsuarioJuridicoDTO atualizar(Integer id, UsuarioJuridicoDTO usuarioJuridicoDTO){

        if (usuariosJuridicosRepository.findById(id).isPresent()){
            if(
                    usuariosJuridicosRepository.existsByEmailEqualsOrCnpjEqualsAndIdNot(
                            usuarioJuridicoDTO.getEmail(),
                            usuarioJuridicoDTO.getCnpj(),
                            id
                    )
            ){
                throw new BasicException("Email ou CNPJ já cadastrado.");
            }else if(
                    validacoesUsuarios.validarSenha(usuarioJuridicoDTO.getSenha()) &&
                            validacoesUsuarios.validarCamposVaziosJuridico(
                                    usuarioJuridicoDTO.getNomeFantasia(),
                                    usuarioJuridicoDTO.getRazaoSocial(),
                                    usuarioJuridicoDTO.getCnpj()
                            )) {

                UsuarioJuridico usuarioJuridicoAtualizado = new UsuarioJuridico(usuarioJuridicoDTO);
                usuarioJuridicoAtualizado.setId(id);

                return new UsuarioJuridicoDTO(usuarioJuridicoAtualizado);
            }
        }
        throw new BasicException("Usuário jurídico não encontrado.");
    }

    public Boolean deletar(Integer id){
        if (usuariosJuridicosRepository.findById(id).isPresent()){
            usuariosJuridicosRepository.deleteById(id);
            return true;
        }
        throw new BasicException("Usuário jurídico não encontrado.");
    }

    public UsuarioJuridicoDTO login(UsuarioJuridicoDTO usuarioJuridicoDTO){
        Optional<UsuarioJuridico> possivelUsuario =
                usuariosJuridicosRepository.findByEmailEqualsAndSenhaEquals(
                        usuarioJuridicoDTO.getEmail(),
                        usuarioJuridicoDTO.getSenha()
                );

        if (possivelUsuario.isEmpty()){
            throw new BasicException("Email ou senha incorretos.");
        }

        UsuarioJuridico usuarioJuridicoAutendicado = possivelUsuario.get();

        return new UsuarioJuridicoDTO(usuarioJuridicoAutendicado);

    }
}
