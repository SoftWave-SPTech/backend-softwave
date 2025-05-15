package com.project.softwave.backend_SoftWave.dto;

public class ProcessoSimplesDTO {

    private Integer id;
    private String numeroProcesso;

    public ProcessoSimplesDTO(Integer id, String numeroProcesso) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }
}
