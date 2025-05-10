package com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO;

import jakarta.validation.constraints.NotBlank;

public class UltimasMovimentacoesDTO {

    private Integer id;

    private String data;

    @NotBlank(message = "O movimento não pode estar em branco")
    private String movimento;

    public UltimasMovimentacoesDTO() {
    }

    public UltimasMovimentacoesDTO(Integer id, String data, String movimento) {
        this.id = id;
        this.data = data;
        this.movimento = movimento;
    }

    public static UltimasMovimentacoesDTO toDTO(UltimasMovimentacoesDTO movimentacao) {
        if (movimentacao == null) {
            return null;
        }
        return new UltimasMovimentacoesDTO(movimentacao.getId(), movimentacao.getData(), movimentacao.getMovimento());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }
}
