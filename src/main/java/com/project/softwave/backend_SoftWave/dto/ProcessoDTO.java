package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoResponseDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;

public class ProcessoDTO {

    private Integer id;
    private String numeroProcesso;
    private String descricao;


    public ProcessoDTO() {
    }
    public ProcessoDTO(Integer id, String numeroProcesso, String descricao) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
        this.descricao = descricao;
    }


    public static ProcessoDTO toResponseDto(Processo processo) {
        ProcessoDTO Dto = new ProcessoDTO();
        Dto.setId(processo.getId());
        Dto.setNumeroProcesso(processo.getNumeroProcesso());
        Dto.setDescricao(processo.getDescricao());
        return Dto;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
