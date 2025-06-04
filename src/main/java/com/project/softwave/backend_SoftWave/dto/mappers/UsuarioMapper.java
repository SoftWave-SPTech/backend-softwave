package com.project.softwave.backend_SoftWave.dto.mappers;

import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.entity.*;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private static UsuarioRepository usuarioRepository;

    public static Usuario of(UsuarioLoginDto usuarioLoginDto) {
        if (usuarioLoginDto == null) {
            throw new IllegalArgumentException("UsuarioLoginDto não pode ser nulo");
        }
        Usuario user =  usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        if (user instanceof UsuarioFisico) {
            if (user instanceof AdvogadoFisico){
                return new AdvogadoFisico();
            }else{
                return new UsuarioFisico();
            }
        } else if (user instanceof UsuarioJuridico) {
            if (user instanceof AdvogadoJuridico) {
                return new AdvogadoJuridico();
            }else{
                return new UsuarioJuridico();
            }
        }
        throw new IllegalArgumentException("Tipo de usuário não reconhecido");
    }

//    public Usuario usuarioFotoPerfilDtoToUsuario(UsuarioFotoPerfilDTO usuarioFotoPerfilDTO){
//        return null;
//    }

//    public UsuarioFotoPerfilDTO usuarioToUsuarioFotoPerfilDto(Usuario usuario){
//        return null;
//    }

}
