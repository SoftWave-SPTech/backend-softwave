package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoResponseDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import jakarta.persistence.Column;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class ProcessoDTO {

    private Integer id;
    private String numeroProcesso;
    private String descricao;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private String dataCriacao;

    @LastModifiedDate
    @Column(name = "updated_at")
    private String dataAtualizacao;


    public ProcessoDTO() {
    }

    public ProcessoDTO(Integer id, String numeroProcesso, String descricao, String dataCriacao, String dataAtualizacao) {
        this.id = id;
        this.numeroProcesso = numeroProcesso;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public static ProcessoDTO toResponseDto(Processo processo) {
        ProcessoDTO Dto = new ProcessoDTO();
        Dto.setId(processo.getId());
        Dto.setNumeroProcesso(processo.getNumeroProcesso());
        Dto.setDescricao(processo.getDescricao());
        Dto.setDataCriacao(processo.getCreatedAt().toString());
        Dto.setDataAtualizacao(processo.getUpdatedAt().toString());

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
    public String getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public String getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}
