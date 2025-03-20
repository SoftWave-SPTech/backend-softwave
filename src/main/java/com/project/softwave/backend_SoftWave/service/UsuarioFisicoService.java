package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
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

    public UsuarioFisicoDTO cadastrar(UsuarioFisicoDTO usuarioFisicoDTO){
        if(
                validarUsuarios.validarSenha(usuarioFisicoDTO.getSenha()) &&
                        validarUsuarios.validarCamposVaziosFisico(
                                usuarioFisicoDTO.getNome(),
                                usuarioFisicoDTO.getRg()
                        ) &&
                        validarUsuarios.validarEmail(usuarioFisicoDTO.getEmail()) &&
                        validarUsuarios.validarCpf(usuarioFisicoDTO.getCpf())
        ){
            if(
                    usuariosFisicosRepository.findByEmailEqualsOrCpfEquals(
                            usuarioFisicoDTO.getEmail(),
                            usuarioFisicoDTO.getCpf()
                    ).isPresent()
            ){
                return null;
            }

            UsuarioFisico usuarioFisicoCadastrado = usuariosFisicosRepository.save(
                    new UsuarioFisico(usuarioFisicoDTO)
            );

            return new UsuarioFisicoDTO(usuarioFisicoCadastrado);

        }
        return null;
    }

    public List<UsuarioFisicoDTO> listar(){

        if (usuariosFisicosRepository.findAll().isEmpty()){
            return null;
        }

        List<UsuarioFisicoDTO> todosUsuariosFisicos =
                usuariosFisicosRepository.findAll()
                        .stream()
                        .map(usuarioFisico -> new UsuarioFisicoDTO(usuarioFisico))
                        .collect(Collectors.toList());

        return todosUsuariosFisicos;
    }

    public UsuarioFisicoDTO atualizar(Integer id, UsuarioFisicoDTO usuarioFisicoDTO){

        if (usuariosFisicosRepository.findById(id).isPresent()){
            if(
                    usuariosFisicosRepository.existsByEmailEqualsOrCpfEqualsAndIdNot(
                            usuarioFisicoDTO.getEmail(),
                            usuarioFisicoDTO.getCpf(),
                            id
                    )
            ){
                return null;
            }else if(
                    validarUsuarios.validarSenha(usuarioFisicoDTO.getSenha()) &&
                            validarUsuarios.validarCamposVaziosFisico(
                                    usuarioFisicoDTO.getNome(),
                                    usuarioFisicoDTO.getRg()
                            ) &&
                            validarUsuarios.validarEmail(usuarioFisicoDTO.getEmail())){

                UsuarioFisico usuarioFisicoAtualizado = new UsuarioFisico(usuarioFisicoDTO);
                usuarioFisicoAtualizado.setId(id);

                return new UsuarioFisicoDTO(usuarioFisicoAtualizado);
            }
        }
        return null;
    }

    public Boolean deletar(Integer id){
        if (usuariosFisicosRepository.findById(id).isPresent()){
            usuariosFisicosRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public UsuarioFisicoDTO login(UsuarioFisicoDTO usuarioFisicoDTO){
        Optional<UsuarioFisico> possivelUsuario =
                usuariosFisicosRepository.findByEmailEqualsAndSenhaEquals(
                        usuarioFisicoDTO.getEmail(),
                        usuarioFisicoDTO.getSenha()
                );

        if (possivelUsuario.isEmpty()){
            return null;
        }

        UsuarioFisico usuarioFisicoAutendicado = possivelUsuario.get();

        return new UsuarioFisicoDTO(usuarioFisicoAutendicado);

    }
}
