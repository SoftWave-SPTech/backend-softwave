package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

import com.project.softwave.backend_SoftWave.entity.*;

public class FinanceiroResponseDTO {

    private Integer id;
    private Integer parcelaAtual;
    private Integer totalParcelas;
    private Double valorParcela;
    private Double valorPago;
    private Double valorPagar;
    private Integer ano;
    private String metodoPagamento;
    private String tipoPagamento;
    private String mes;
    private String statusFinanceiro;

    public static RegistroFinanceiro toEntity(FinanceiroResponseDTO dto) {
        RegistroFinanceiro entity = new RegistroFinanceiro();

        entity.setId(dto.getId());
        entity.setMes(dto.getMes() != null ? Meses.valueOf(dto.getMes().toUpperCase()) : null);
        entity.setValorParcela(dto.getValorParcela());
        entity.setValorPago(dto.getValorPago());
        entity.setValorPagar(dto.getValorPagar());
        entity.setStatusFinanceiro(dto.getStatusFinanceiro() != null ? StatusFinanceiro.valueOf(dto.getStatusFinanceiro().toUpperCase()) : null);
        entity.setTipoPagamento(dto.getTipoPagamento() != null ? TipoPagamento.valueOf(dto.getTipoPagamento().toUpperCase()) : null);
        entity.setMetodoPagamento(dto.getMetodoPagamento() != null ? MetodoPagamento.valueOf(dto.getMetodoPagamento().toUpperCase()) : null);
        entity.setTotalParcelas(dto.getTotalParcelas());
        entity.setParcelaAtual(dto.getParcelaAtual());
        entity.setAno(dto.getAno());

        return entity;
    }

    public static FinanceiroResponseDTO toDto (RegistroFinanceiro entity) {

        FinanceiroResponseDTO dto = new FinanceiroResponseDTO();
        dto.setParcelaAtual(entity.getParcelaAtual());
        dto.setTotalParcelas(entity.getTotalParcelas());
        dto.setValorParcela(entity.getValorParcela());
        dto.setValorPago(entity.getValorPago());
        dto.setValorPagar(entity.getValorPagar());
        dto.setAno(entity.getAno());
        dto.setId(entity.getId());

        dto.setMetodoPagamento(entity.getMetodoPagamento() != null ? entity.getMetodoPagamento().name() : null);
        dto.setTipoPagamento(entity.getTipoPagamento() != null ? entity.getTipoPagamento().name() : null);
        dto.setMes(entity.getMes() != null ? entity.getMes().name() : null);
        dto.setStatusFinanceiro(entity.getStatusFinanceiro() != null ? entity.getStatusFinanceiro().name() : null);

        return dto;
    }

    public FinanceiroResponseDTO() {
    }

    public FinanceiroResponseDTO(
            Integer id,
            Integer parcelaAtual,
            Integer totalParcelas,
            Double valorParcela,
            Double valorPago,
            Double valorPagar,
            Integer ano,
            String metodoPagamento,
            String tipoPagamento,
            String mes,
            String statusFinanceiro
    ) {
        this.id = id;
        this.parcelaAtual = parcelaAtual;
        this.totalParcelas = totalParcelas;
        this.valorParcela = valorParcela;
        this.valorPago = valorPago;
        this.valorPagar = valorPagar;
        this.ano = ano;
        this.metodoPagamento = metodoPagamento;
        this.tipoPagamento = tipoPagamento;
        this.mes = mes;
        this.statusFinanceiro = statusFinanceiro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParcelaAtual() {
        return parcelaAtual;
    }

    public void setParcelaAtual(Integer parcelaAtual) {
        this.parcelaAtual = parcelaAtual;
    }

    public Integer getTotalParcelas() {
        return totalParcelas;
    }

    public void setTotalParcelas(Integer totalParcelas) {
        this.totalParcelas = totalParcelas;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(Double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(String statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }
}
