package com.project.softwave.backend_SoftWave.dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDateTime;


public class TarefaDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
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

    public TarefaDTO() {}

    public TarefaDTO(Integer id, String titulo, String descricao, LocalDateTime prazo, String prioridade, boolean isFinalizada) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.prioridade = prioridade;
        this.isFinalizada = isFinalizada;
    }


    public Integer getId() {
        return id;
    }

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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }
}
