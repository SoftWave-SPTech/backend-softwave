package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

public class FinanceiroDadosTelaDTO {

    private Double valorTotalReceber;
    private Double valorTotalCaso;
    private Double valorTotalProcesso;
    private Double valorHonorarioSucumbencia;

    public FinanceiroDadosTelaDTO() {
    }

    public FinanceiroDadosTelaDTO(
            Double valorTotalReceber,
            Double valorTotalCaso,
            Double valorTotalProcesso,
            Double valorHonorarioSucumbencia
    ) {
        this.valorTotalReceber = valorTotalReceber;
        this.valorTotalCaso = valorTotalCaso;
        this.valorTotalProcesso = valorTotalProcesso;
        this.valorHonorarioSucumbencia = valorHonorarioSucumbencia;
    }

    public Double getValorTotalReceber() {
        return valorTotalReceber;
    }

    public void setValorTotalReceber(Double valorTotalReceber) {
        this.valorTotalReceber = valorTotalReceber;
    }

    public Double getValorTotalCaso() {
        return valorTotalCaso;
    }

    public void setValorTotalCaso(Double valorTotalCaso) {
        this.valorTotalCaso = valorTotalCaso;
    }

    public Double getValorTotalProcesso() {
        return valorTotalProcesso;
    }

    public void setValorTotalProcesso(Double valorTotalProcesso) {
        this.valorTotalProcesso = valorTotalProcesso;
    }

    public Double getValorHonorarioSucumbencia() {
        return valorHonorarioSucumbencia;
    }

    public void setValorHonorarioSucumbencia(Double valorHonorarioSucumbencia) {
        this.valorHonorarioSucumbencia = valorHonorarioSucumbencia;
    }
}
