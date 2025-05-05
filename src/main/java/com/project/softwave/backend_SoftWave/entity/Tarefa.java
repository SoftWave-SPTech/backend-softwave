package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;


@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private String descricao;
    private LocalDateTime prazo;
    private String prioridade;
    private boolean isFinalizada;


    public String getStatus() {
        if (this.isFinalizada) {
            return "FINALIZADA";
        } else if (prazo.isBefore(LocalDateTime.now())) {
            return "ATRASADA";
        }
        return "EM_DIA";

    }



    public Integer getId() {return id;}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
        this.prazo = prazo;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

}
