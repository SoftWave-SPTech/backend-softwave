package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Processo> listaDeProcesos = new ArrayList<>();

    public Setor() {}
    public Setor(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.listaDeProcesos = new ArrayList<>();
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

    public List<Processo> getProcessoList() {
        return listaDeProcesos;
    }
    public void setProcessoList(List<Processo> processoList) {
        this.listaDeProcesos = processoList;
    }

    public void adicionarProcesso(Processo processo) {
        listaDeProcesos.add(processo);
        processo.setSetor(this);
    }
}
