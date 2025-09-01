package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

import com.project.softwave.backend_SoftWave.entity.Meses;
import com.project.softwave.backend_SoftWave.entity.MetodoPagamento;
import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import com.project.softwave.backend_SoftWave.entity.TipoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class FinanceiroAtualizacaoDTO {
    @Schema(description = "Parcela sendo paga atualmente", example = "5")
    private Integer parcelaAtual;

    @Schema(description = "Em quantas vezes foi parcelado", example = "12")
    private Integer totalParcelas;

    @Schema(description = "Valor de cada parcela", example = "500.99")
    private Double valorParcela;

    @NotNull
    @Schema(description = "Valor total do caso", example = "4449.90")
    private Double valorPago;

    @NotNull
    @Schema(description = "Valor sendo pago", example = "440.90")
    private Double valorPagar;

    @NotNull
    @Schema(description = "Ano do pagamento", example = "2025")
    private Integer ano;

    @NotNull
    @Schema(description = "Método do pagamento", example = "PIX")
    private String metodoPagamento;

    @NotNull
    @Schema(description = "Tipo de pagamento", example = "A_VISTA")
    private String tipoPagamento;

    @NotNull
    @Schema(description = "Mês do pagamento", example = "MARCO")
    private String mes;


    // ------------------ CONVERSORES ------------------

    public static RegistroFinanceiro toEntity(FinanceiroAtualizacaoDTO dto) {
        RegistroFinanceiro entity = new RegistroFinanceiro();

        entity.setMes(dto.getMes() != null ? Meses.valueOf(dto.getMes().toUpperCase()) : null);
        entity.setValorParcela(dto.getValorParcela());
        entity.setValorPago(dto.getValorPago());
        entity.setValorPagar(dto.getValorPagar());
        entity.setMetodoPagamento(dto.getMetodoPagamento() != null ? MetodoPagamento.valueOf(dto.getMetodoPagamento().toUpperCase()) : null);
        entity.setTipoPagamento(dto.getTipoPagamento() != null ? TipoPagamento.valueOf(dto.getTipoPagamento().toUpperCase()) : null);
        entity.setTotalParcelas(dto.getTotalParcelas());
        entity.setParcelaAtual(dto.getParcelaAtual());
        entity.setAno(dto.getAno());

        return entity;
    }

    public static FinanceiroAtualizacaoDTO toDto (RegistroFinanceiro entity) {

        FinanceiroAtualizacaoDTO dto = new FinanceiroAtualizacaoDTO();
        dto.setParcelaAtual(entity.getParcelaAtual());
        dto.setTotalParcelas(entity.getTotalParcelas());
        dto.setValorParcela(entity.getValorParcela());
        dto.setValorPago(entity.getValorPago());
        dto.setValorPagar(entity.getValorPagar());
        dto.setAno(entity.getAno());

        dto.setMetodoPagamento(entity.getMetodoPagamento() != null ? entity.getMetodoPagamento().name() : null);
        dto.setTipoPagamento(entity.getTipoPagamento() != null ? entity.getTipoPagamento().name() : null);
        dto.setMes(entity.getMes() != null ? entity.getMes().name() : null);

        return dto;
    }

    // ------------------ CONSTRUTORES ------------------

    public FinanceiroAtualizacaoDTO() {}

    public FinanceiroAtualizacaoDTO(
            Integer parcelaAtual,
            Integer totalParcelas,
            Double valorParcela,
            Double valorPago,
            Double valorPagar,
            Integer ano,
            String metodoPagamento,
            String tipoPagamento,
            String mes
    ) {
        this.parcelaAtual = parcelaAtual;
        this.totalParcelas = totalParcelas;
        this.valorParcela = valorParcela;
        this.valorPago = valorPago;
        this.valorPagar = valorPagar;
        this.ano = ano;
        this.metodoPagamento = metodoPagamento;
        this.tipoPagamento = tipoPagamento;
        this.mes = mes;
    }

    // ------------------ GETTERS E SETTERS ------------------

    public Integer getParcelaAtual() { return parcelaAtual; }
    public void setParcelaAtual(Integer parcelaAtual) { this.parcelaAtual = parcelaAtual; }

    public Integer getTotalParcelas() { return totalParcelas; }
    public void setTotalParcelas(Integer totalParcelas) { this.totalParcelas = totalParcelas; }

    public Double getValorParcela() { return valorParcela; }
    public void setValorParcela(Double valorParcela) { this.valorParcela = valorParcela; }

    public Double getValorPago() { return valorPago; }
    public void setValorPago(Double valorPago) { this.valorPago = valorPago; }

    public Double getValorPagar() { return valorPagar; }
    public void setValorPagar(Double valorPagar) { this.valorPagar = valorPagar; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public String getMetodoPagamento() { return metodoPagamento; }
    public void setMetodoPagamento(String metodoPagamento) { this.metodoPagamento = metodoPagamento; }

    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
}
