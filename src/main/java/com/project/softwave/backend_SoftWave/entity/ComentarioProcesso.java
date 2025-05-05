package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ComentarioProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private LocalDateTime dataCriacao;


    @ManyToOne
    private Processo processo;

    @ManyToOne
    private AdvogadoFisico advogadoFisico;

    @ManyToOne
    private AdvogadoJuridico advogadoJuridico;

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
}