package com.project.softwave.backend_SoftWave.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ComentarioProcessoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String comentario;

    @NotNull
    private LocalDateTime dataCriacao;

    private Integer usuarioID;

    private Integer ultimaMovimentacaoID;

    private Long processoID;

    public ComentarioProcessoDTO() {
    }

    public ComentarioProcessoDTO(Long id, String comentario, LocalDateTime dataCriacao, Integer ultimaMovimentacaoID) {
        this.id = id;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
        this.ultimaMovimentacaoID = ultimaMovimentacaoID;
    }

    public ComentarioProcessoDTO(Long id, String comentario, LocalDateTime dataCriacao, Integer ultimaMovimentacaoID, Integer usuarioID) {
        this.id = id;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
        this.ultimaMovimentacaoID = ultimaMovimentacaoID;
        this.usuarioID = usuarioID;
    }

    public ComentarioProcessoDTO(Long id, String comentario, LocalDateTime dataCriacao, Long processoId, Integer usuarioId) {
        this.id = id;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
        this.processoID = processoId;
        this.usuarioID = usuarioId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getUltimaMovimentacaoID() {
        return ultimaMovimentacaoID;
    }

    public void setUltimaMovimentacaoID(Integer ultimaMovimentacaoID) {
        this.ultimaMovimentacaoID = ultimaMovimentacaoID;
    }

    public Long getProcessoID() {
        return processoID;
    }

    public void setProcessoID(Long processoID) {
        this.processoID = processoID;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }




}
