package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDateTime data;

    @Column(columnDefinition = "MEDIUMTEXT")
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(String data) {
        // Espera receber data no formato "dd/MM/yyyy" ou "dd/MM/yyyy HH:mm"
        DateTimeFormatter formatter;
        if (data.trim().length() > 10) {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withLocale(new java.util.Locale("pt", "BR"));
        } else {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(new java.util.Locale("pt", "BR"));
        }
        this.data = LocalDateTime.parse(
            data.trim().length() > 10 ? data.trim() : data.trim() + " 00:00",
            formatter
        );
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }

    @Override
    public String toString() {
        return "UltimasMovimentacoes{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", movimento='" + movimento + '\'' +
                ", processo=" + processo +
                '}';
    }
}
