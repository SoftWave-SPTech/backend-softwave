package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;

public class UsuariosResponseDTO {

    private Integer id;
    private String nome;
    private String representante;

    public UsuariosResponseDTO() {

    }

    public UsuariosResponseDTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UsuariosResponseDTO(Integer id, String nome, String representante) {
        this.id = id;
        this.nome = nome;
        this.representante = representante;
    }

    public static UsuariosResponseDTO toUsuariosResponseDTO (Usuario usuario){
        if (usuario instanceof UsuarioJuridico) {
            UsuarioJuridico usuarioJuridico = (UsuarioJuridico) usuario;
            return new UsuariosResponseDTO(usuario.getId(), usuarioJuridico.getNomeFantasia(), usuarioJuridico.getRepresentante());
        }else if (usuario instanceof UsuarioFisico) {
            UsuarioFisico usuarioFisico = (UsuarioFisico) usuario;
            return new UsuariosResponseDTO(usuario.getId(), usuarioFisico.getNome());
        }else {
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
}
