package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;

import java.util.Objects;

public class ComentarioProcessoResponseDTO {

    private Integer id;
    private String comentario;
    private String dataComentario;
    private String nomeUsuario;
    private String fotoUsuario;

    public static ComentarioProcessoResponseDTO toResponseDTO(ComentarioProcesso comentarioProcesso) {
            if(comentarioProcesso.getUsuario() instanceof AdvogadoFisico || comentarioProcesso.getUsuario() instanceof UsuarioFisico) {
                String nome = ((UsuarioFisico) comentarioProcesso.getUsuario()).getNome();
                return new ComentarioProcessoResponseDTO(
                        comentarioProcesso.getId().intValue(),
                        comentarioProcesso.getComentario(),
                        comentarioProcesso.getDataCriacao().toString(),
                        nome,
                        comentarioProcesso.getUsuario().getFoto()
                );
            }else {
                String nome = ((UsuarioJuridico) comentarioProcesso.getUsuario()).getRepresentante();
                return new ComentarioProcessoResponseDTO(
                        comentarioProcesso.getId().intValue(),
                        comentarioProcesso.getComentario(),
                        comentarioProcesso.getDataCriacao().toString(),
                        nome,
                        comentarioProcesso.getUsuario().getFoto()
                );
            }
    }

    public ComentarioProcessoResponseDTO(){}

    public ComentarioProcessoResponseDTO(Integer id, String comentario, String dataComentario, String nomeUsuario, String fotoUsuario) {
        this.id = id;
        this.comentario = comentario;
        this.dataComentario = dataComentario;
        this.nomeUsuario = nomeUsuario;
        this.fotoUsuario = fotoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(String dataComentario) {
        this.dataComentario = dataComentario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }
}
