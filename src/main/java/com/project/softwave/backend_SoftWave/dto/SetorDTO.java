package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.Setor;

public class SetorDTO {
    private Long id;
    private String nome;
    private String descricao;

    public SetorDTO() {}

    public SetorDTO(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }
    public Setor toEntity() {
        return new Setor(nome, descricao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
