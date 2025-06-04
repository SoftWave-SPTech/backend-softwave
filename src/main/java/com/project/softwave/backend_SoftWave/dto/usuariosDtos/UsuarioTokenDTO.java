package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;

public class UsuarioTokenDTO {

    private Integer id;
    private String email;
    private String token;
    private String tipoUsuario;
    private String role;
    private String nome;
    private String foto;

    public UsuarioTokenDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioTokenDTO(Integer id,String email, String token, String tipoUsuario, String role, String nome, String foto) {
        this.id = id;
        this.email = email;
        this.token = token;
        this.tipoUsuario = tipoUsuario;
        this.role = role;
        this.nome = nome;
        this.foto = foto;
    }

    public static UsuarioTokenDTO toDTO(Usuario usuario, String token, String role, String nome, String foto) {
        return new UsuarioTokenDTO(usuario.getId(), usuario.getEmail(),token, usuario.getClass().getSimpleName(), role, nome, foto);
    }

//    public static UsuarioTokenDTO toDTO(Usuario usuario, String token) {
//        UsuarioTokenDTO usuarioTokenDto = new UsuarioTokenDTO();
//
//        usuarioTokenDto.setUserId(usuario.getId());
//        usuarioTokenDto.setEmail(usuario.getEmail());
//        usuarioTokenDto.setToken(token);
//
//        return usuarioTokenDto;
//    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
