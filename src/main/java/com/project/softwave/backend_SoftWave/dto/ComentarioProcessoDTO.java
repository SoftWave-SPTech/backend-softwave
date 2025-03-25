package com.project.softwave.backend_SoftWave.dto;

import java.time.LocalDateTime;

public class ComentarioProcessoDTO {

    private Long id;
    private Long processoId;
    private String comentario;
    private LocalDateTime dataCriacao;

    public ComentarioProcessoDTO() {
    }

    public ComentarioProcessoDTO(Long id, Long processoId, String comentario, LocalDateTime dataCriacao) {
        this.id = id;
        this.processoId = processoId;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessoId() {
        return processoId;
    }

    public void setProcessoId(Long processoId) {
        this.processoId = processoId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
