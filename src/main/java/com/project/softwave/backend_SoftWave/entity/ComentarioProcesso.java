package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ComentarioProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String comentario;

    private LocalDateTime dataCriacao;

    @ManyToOne
    private UltimasMovimentacoes ultimaMovimentacao;

    @ManyToOne
    private Processo processo;

    @ManyToOne
    private Usuario usuario;

    public ComentarioProcesso() {}

    public ComentarioProcesso(Long id, String comentario, LocalDateTime dataCriacao, UltimasMovimentacoes ultimaMovimentacao, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
        this.ultimaMovimentacao = ultimaMovimentacao;
        this.usuario = usuario;
    }

    public ComentarioProcesso(Long id, String comentario, LocalDateTime dataCriacao, Processo processo, Usuario usuario) {
        this.id = id;
        this.comentario = comentario;
        this.dataCriacao = dataCriacao;
        this.processo = processo;
        this.usuario = usuario;
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

    public UltimasMovimentacoes getUltimaMovimentacao() {
        return ultimaMovimentacao;
    }

    public void setUltimaMovimentacao(UltimasMovimentacoes ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}