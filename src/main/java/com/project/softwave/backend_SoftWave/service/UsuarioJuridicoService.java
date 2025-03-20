package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.SetorDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
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

    public UsuarioJuridicoDTO cadastrar(UsuarioJuridicoDTO usuarioJuridicoDTO){
        if(
                validacoesUsuarios.validarSenha(usuarioJuridicoDTO.getSenha()) &&
                validacoesUsuarios.validarCamposVaziosJuridico(
                        usuarioJuridicoDTO.getNomeFantasia(),
                        usuarioJuridicoDTO.getRazaoSocial(),
                        usuarioJuridicoDTO.getCnpj()
                ) &&
                validacoesUsuarios.validarEmail(usuarioJuridicoDTO.getEmail())
        ){
            if(
                    usuariosJuridicosRepository.findByEmailEqualsOrCnpjEquals(
                        usuarioJuridicoDTO.getEmail(),
                        usuarioJuridicoDTO.getCnpj()
                    ).isPresent()
            ){
                return null;
            }

            UsuarioJuridico usuarioJuridicoCadastrado = usuariosJuridicosRepository.save(
                    new UsuarioJuridico(usuarioJuridicoDTO)
            );

            return new UsuarioJuridicoDTO(usuarioJuridicoCadastrado);

        }
        return null;
    }

    public List<UsuarioJuridicoDTO> listar(){

        if (usuariosJuridicosRepository.findAll().isEmpty()){
            return null;
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
                return null;
            }else if(
                    validacoesUsuarios.validarSenha(usuarioJuridicoDTO.getSenha()) &&
                    validacoesUsuarios.validarCamposVaziosJuridico(
                            usuarioJuridicoDTO.getNomeFantasia(),
                            usuarioJuridicoDTO.getRazaoSocial(),
                            usuarioJuridicoDTO.getCnpj()
                    ) &&
                    validacoesUsuarios.validarEmail(usuarioJuridicoDTO.getEmail())){

                UsuarioJuridico usuarioJuridicoAtualizado = new UsuarioJuridico(usuarioJuridicoDTO);
                usuarioJuridicoAtualizado.setId(id);

                return new UsuarioJuridicoDTO(usuarioJuridicoAtualizado);
            }
        }
        return null;
    }

    public Boolean deletar(Integer id){
        if (usuariosJuridicosRepository.findById(id).isPresent()){
            usuariosJuridicosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UsuarioJuridicoDTO login(UsuarioJuridicoDTO usuarioJuridicoDTO){
        Optional<UsuarioJuridico> possivelUsuario =
                usuariosJuridicosRepository.findByEmailEqualsAndSenhaEquals(
                    usuarioJuridicoDTO.getEmail(),
                    usuarioJuridicoDTO.getSenha()
                );

        if (possivelUsuario.isEmpty()){
            return null;
        }

        UsuarioJuridico usuarioJuridicoAutendicado = possivelUsuario.get();

        return new UsuarioJuridicoDTO(usuarioJuridicoAutendicado);

    }
}
