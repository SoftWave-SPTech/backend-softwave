package com.project.softwave.backend_SoftWave.dto;

public class ProcessoDTO {
    private Long id;
    private String numero;
    private String nome;
    private String descricao;
    private Long setorId;

    public ProcessoDTO() {}

    public ProcessoDTO(Long id, String numero, String nome, String descricao, Long setorId) {
        this.id = id;
        this.numero = numero;
        this.nome = nome;
        this.descricao = descricao;
        this.setorId = setorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public Long getSetorId() {
        return setorId;
    }

    public void setSetorId(Long setorId) {
        this.setorId = setorId;
    }
}
