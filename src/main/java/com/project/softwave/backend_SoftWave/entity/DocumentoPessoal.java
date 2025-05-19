package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
public class DocumentoPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeArquivo;
    private String urlArquivo;

//    TODO IMPLEMENTAR ESSE CAMPO NO FUTURO
//    @ManyToOne
//    @JoinColumn(name = "fk_cliente_id", referencedColumnName = "id")
//    private Usuario fkCliente;


    public DocumentoPessoal() {
    }

    public DocumentoPessoal(String nomeArquivo, String urlArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
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
}
