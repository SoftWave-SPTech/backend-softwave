package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import jakarta.persistence.*;

@Entity
public class HistoricoClasses {
//            "data": "10/04/2024",
//            "tipo": "Evolução",
//            "classe": "Ação Penal - Procedimento Ordinário",
//            "area": "Criminal",
//            "motivo": "-"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String data;
    private String tipo;
    private String classe;
    private String area;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
