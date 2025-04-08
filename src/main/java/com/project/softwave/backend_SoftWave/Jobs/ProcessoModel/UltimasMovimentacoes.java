package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import jakarta.persistence.*;

@Entity
public class UltimasMovimentacoes {
//    {
//            "data": "28/03/2025",
//            "movimento": "Petição Juntada Nº Protocolo: WJMJ.25.40720318-9 Tipo da Petição: Petições Diversas Data: 28/03/2025 17:12"
//    },
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    // @Many to one (fk_processo)
    private String data;
    private String movimento;

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

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }
}
