package com.project.softwave.backend_SoftWave.dto.FinanceiroDTO;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.entity.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class FinanceiroRequestDTO {

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

    @NotNull
    private Integer cliente; // ID do cliente

    @NotNull
    private Integer processo; // ID do processo

    // ------------------ CONVERSORES ------------------

    public RegistroFinanceiro toEntity() {
        return null;
    }

    public static FinanceiroRequestDTO fromEntity(RegistroFinanceiro entity) {
        if (entity == null) return null;

        FinanceiroRequestDTO dto = new FinanceiroRequestDTO();
        dto.parcelaAtual = entity.getParcelaAtual();
        dto.totalParcelas = entity.getTotalParcelas();
        dto.valorParcela = entity.getValorParcela();
        dto.valorPago = entity.getValorPago();
        dto.valorPagar = entity.getValorPagar();
        dto.ano = entity.getAno();

        dto.metodoPagamento = entity.getMetodoPagamento() != null ? entity.getMetodoPagamento().name() : null;
        dto.tipoPagamento = entity.getTipoPagamento() != null ? entity.getTipoPagamento().name() : null;
        dto.mes = entity.getMes() != null ? entity.getMes().name() : null;

        dto.cliente = entity.getCliente() != null ? entity.getCliente().getId() : null;
        dto.processo = entity.getProcesso() != null ? entity.getProcesso().getId() : null;

        return dto;
    }

    // ------------------ CONSTRUTORES ------------------

    public FinanceiroRequestDTO() {}

    public FinanceiroRequestDTO(
            Integer parcelaAtual,
            Integer totalParcelas,
            Double valorParcela,
            Double valorPago,
            Double valorPagar,
            Integer ano,
            String metodoPagamento,
            String tipoPagamento,
            String mes,
            Integer cliente,
            Integer processo
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
        this.cliente = cliente;
        this.processo = processo;
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

    public Integer getCliente() { return cliente; }
    public void setCliente(Integer cliente) { this.cliente = cliente; }

    public Integer getProcesso() { return processo; }
    public void setProcesso(Integer processo) { this.processo = processo; }
}
