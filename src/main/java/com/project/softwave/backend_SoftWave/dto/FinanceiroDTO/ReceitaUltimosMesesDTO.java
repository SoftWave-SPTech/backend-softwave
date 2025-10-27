package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

import com.project.softwave.backend_SoftWave.entity.Meses;

public class ReceitaUltimosMesesDTO {
    private Meses mes;
    private Integer ano;
    private Double receita;

    public ReceitaUltimosMesesDTO() {
    }

    public ReceitaUltimosMesesDTO(
            Meses mes,
            Integer ano
    ) {
        this.mes = mes;
        this.ano = ano;
    }

    public Meses getMes() {
        return mes;
    }

    public Integer getAno() {
        return ano;
    }

    public Double getReceita() {
        return receita;
    }

    public void setMes(Meses mes) {
        this.mes = mes;
    }

    public void setReceita(Double receita) {
        this.receita = receita;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
