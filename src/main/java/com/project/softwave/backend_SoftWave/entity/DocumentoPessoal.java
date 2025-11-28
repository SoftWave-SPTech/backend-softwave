package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
public class DocumentoPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String nomeArquivo;
    private String urlArquivo;
    @Column(name = "s3_key", length = 500)
    private String s3Key;


    @ManyToOne
    private Usuario fkUsuario;

    public DocumentoPessoal() {
    }

    public DocumentoPessoal(String nomeArquivo, String urlArquivo, String s3Key) {
        this.nomeArquivo = nomeArquivo;
        this.s3Key = s3Key;
        this.urlArquivo = urlArquivo;
    }

    public DocumentoPessoal(String nomeArquivo, String urlArquivo, String s3Key, Usuario fkUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.s3Key = s3Key;
        this.fkUsuario = fkUsuario;
    }

    public DocumentoPessoal(Integer id, String nomeArquivo, String urlArquivo, String s3Key, Usuario fkUsuario) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.s3Key = s3Key;
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

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
}
