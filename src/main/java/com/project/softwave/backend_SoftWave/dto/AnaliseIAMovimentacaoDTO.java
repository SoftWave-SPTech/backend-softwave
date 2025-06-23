package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.UltimasMovimentacoesDTO;

public class AnaliseIAMovimentacaoDTO {

    private Integer id;

    private String resumoIA;

    private UltimasMovimentacoesDTO ultimaMovimentacao;

    public AnaliseIAMovimentacaoDTO() {
    }

    public AnaliseIAMovimentacaoDTO(Integer id, String resumoIA, UltimasMovimentacoesDTO ultimaMovimentacao) {
        this.id = id;
        this.resumoIA = resumoIA;
        this.ultimaMovimentacao = ultimaMovimentacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResumoIA() {
        return resumoIA;
    }

    public void setResumoIA(String resumoIA) {
        this.resumoIA = resumoIA;
    }

    public UltimasMovimentacoesDTO getUltimaMovimentacao() {
        return ultimaMovimentacao;
    }

    public void setUltimaMovimentacao(UltimasMovimentacoesDTO ultimaMovimentacao) {
        this.ultimaMovimentacao = ultimaMovimentacao;
    }
}
