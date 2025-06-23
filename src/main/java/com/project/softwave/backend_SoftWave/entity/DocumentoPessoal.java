package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
public class DocumentoPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String nomeArquivo;
    private String urlArquivo;

    @ManyToOne
    private Usuario fkUsuario;

    public DocumentoPessoal() {
    }

    public DocumentoPessoal(String nomeArquivo, String urlArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public DocumentoPessoal(String nomeArquivo, String urlArquivo, Usuario fkUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.fkUsuario = fkUsuario;
    }

    public DocumentoPessoal(Integer id, String nomeArquivo, String urlArquivo, Usuario fkUsuario) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.fkUsuario = fkUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
