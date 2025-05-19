package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DocumentosProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeArquivo;
    private String urlArquivo;

    public DocumentosProcesso() {}

    public DocumentosProcesso(String nomeArquivo, String urlArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNomeArquivo() {return nomeArquivo;}

    public void setNomeArquivo(String nomeArquivo) {this.nomeArquivo = nomeArquivo;}

    public String getUrlArquivo() {return urlArquivo;}

    public void setUrlArquivo(String urlArquivo) {this.urlArquivo = urlArquivo;}
}
