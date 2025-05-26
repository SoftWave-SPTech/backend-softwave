package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;

public class ProcessoSimplesDTO {

    private Integer id;
    private String numeroProcesso;

    public ProcessoSimplesDTO(Integer id, String numeroProcesso) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
    }

    public static ProcessoSimplesDTO toProcessoSimplesDTO(Processo processo){
        return new ProcessoSimplesDTO(processo.getId(), processo.getNumeroProcesso());
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
