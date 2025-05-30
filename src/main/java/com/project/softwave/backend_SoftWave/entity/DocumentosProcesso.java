package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;

@Entity
public class DocumentosProcesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nomeArquivo;
    private String urlArquivo;

    @ManyToOne
    private Processo fkProcesso;

    public DocumentosProcesso() {}

    public DocumentosProcesso(String nomeArquivo, String urlArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public DocumentosProcesso(String nomeArquivo, String urlArquivo, Processo fkProcesso) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.fkProcesso = fkProcesso;
    }

    public DocumentosProcesso(Integer id, String nomeArquivo, String urlArquivo, Processo fkProcesso) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.fkProcesso = fkProcesso;
    }

    public Processo getFkProcesso() {
        return fkProcesso;
    }

    public void setFkProcesso(Processo fkProcesso) {
        this.fkProcesso = fkProcesso;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNomeArquivo() {return nomeArquivo;}

    public void setNomeArquivo(String nomeArquivo) {this.nomeArquivo = nomeArquivo;}

    public String getUrlArquivo() {return urlArquivo;}

    public void setUrlArquivo(String urlArquivo) {this.urlArquivo = urlArquivo;}
}
