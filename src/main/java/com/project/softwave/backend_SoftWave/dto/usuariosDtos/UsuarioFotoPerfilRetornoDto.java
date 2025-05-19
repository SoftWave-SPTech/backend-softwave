package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

public class UsuarioFotoPerfilRetornoDto {

    private Integer id;
    private String fotoPerfil;

    public UsuarioFotoPerfilRetornoDto() {
    }

    public UsuarioFotoPerfilRetornoDto(Integer id, String fotoPerfil) {
        this.id = id;
        this.fotoPerfil = fotoPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
