package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

import com.project.softwave.backend_SoftWave.entity.Meses;

public class ReceitaUltimosMesesDTO {
    private Meses mes;
    private Integer ano;
    private Double receita;

    public ReceitaUltimosMesesDTO(Meses mes, Integer ano, Double receita) {
        this.mes = mes;
        this.ano = ano;
        this.receita = receita;
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
}
