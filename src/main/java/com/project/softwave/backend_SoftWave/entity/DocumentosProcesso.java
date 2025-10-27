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
    @Column(name = "s3_key", length = 500)
    private String s3Key;

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }

    @ManyToOne
    private Processo fkProcesso;

    public DocumentosProcesso() {}

    public DocumentosProcesso(String nomeArquivo, String urlArquivo,  String s3Key) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.s3Key = s3Key;
    }

    public DocumentosProcesso(String nomeArquivo, String urlArquivo,String s3Key, Processo fkProcesso) {
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.s3Key = s3Key;
        this.fkProcesso = fkProcesso;
    }

    public DocumentosProcesso(Integer id, String nomeArquivo, String urlArquivo,String s3Key, Processo fkProcesso) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.s3Key = s3Key;
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
