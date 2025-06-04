package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import org.springframework.web.multipart.MultipartFile;

public class UsuarioFotoPerfilDTO {
    private Integer id;
    private MultipartFile fotoPerfil;

    public UsuarioFotoPerfilDTO(){

    }

    public UsuarioFotoPerfilDTO(Integer id, MultipartFile fotoPerfil) {
        this.id = id;
        this.fotoPerfil = fotoPerfil;
    }

    public MultipartFile getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(MultipartFile fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
