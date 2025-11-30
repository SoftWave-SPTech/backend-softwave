package com.project.softwave.backend_SoftWave.dto.DTOsDash;

public class QtdPorSetorDTOImpl implements QtdPorSetorDTO {
    private String setor;
    private Integer qtdProcessos;

    public QtdPorSetorDTOImpl() {
    }

    public QtdPorSetorDTOImpl(String setor, Integer qtdProcessos) {
        this.setor = setor;
        this.qtdProcessos = qtdProcessos;
    }

    @Override
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    @Override
    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Integer qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }
}

