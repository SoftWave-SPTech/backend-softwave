package com.project.softwave.backend_SoftWave.dto.DTOsDash;

public class SetorComMaisProcessosDTO {
    private String setor;
    private Integer qtdProcessos;

    public SetorComMaisProcessosDTO() {
    }

    public SetorComMaisProcessosDTO(String setor, Integer qtdProcessos) {
        this.setor = setor;
        this.qtdProcessos = qtdProcessos;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Integer qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }
}
