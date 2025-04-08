package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import jakarta.persistence.*;

@Entity
public class Apensos {
//            "numero": "0000123-50.2025.8.26.0542",
//            "classe": "Comunicado de Mandado de Prisão",
//            "apensamento": "22/01/2025",
//            "motivo": ""
//    @Many to one (fk_processo)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String numeroProcesso;
    private String classe;
    private String apensamento;
    private String motivo;
    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getApensamento() {
        return apensamento;
    }

    public void setApensamento(String apensamento) {
        this.apensamento = apensamento;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }
}
