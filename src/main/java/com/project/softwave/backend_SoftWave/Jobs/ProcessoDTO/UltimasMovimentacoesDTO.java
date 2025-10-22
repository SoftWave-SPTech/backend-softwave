package com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UltimasMovimentacoesDTO {

    private Integer id;

    private LocalDateTime data;

    @NotBlank(message = "O movimento não pode estar em branco")
    private String movimento;

    private Integer idProcesso;

    public UltimasMovimentacoesDTO() {
    }

    public UltimasMovimentacoesDTO(Integer id, LocalDateTime data, String movimento, Integer idProcesso) {
        this.id = id;
        this.data = data;
        this.movimento = movimento;
        this.idProcesso = idProcesso;
    }




    public static UltimasMovimentacoesDTO fromEntity(UltimasMovimentacoes entity) {
        if (entity == null) {
            return null;
        }
        return new UltimasMovimentacoesDTO(
                entity.getId(),
                entity.getData(),
                entity.getMovimento(),
                entity.getProcesso().getId()
        );
    }


    public static UltimasMovimentacoesDTO toDTO(UltimasMovimentacoes movimentacao) {
        if (movimentacao == null) {
            return null;
        }
        return new UltimasMovimentacoesDTO(
                movimentacao.getId(),
                movimentacao.getData(),
                movimentacao.getMovimento(),
                movimentacao.getProcesso().getId()
        );
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

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }
}
