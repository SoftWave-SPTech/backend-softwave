package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
public class RegistroFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer parcelaAtual;
    private Integer totalParcelas;
    private Double valorParcela;
    private Double valorPago;
    private Double valorPagar;
    private Integer ano;
    private final Double honorarioSucumbencia = 0.10;
    private MetodoPagamento metodoPagamento;
    private TipoPagamento tipoPagamento;
    private Meses mes;
    private StatusFinanceiro statusFinanceiro;

    @ManyToOne
    private Usuario cliente;

    @ManyToOne
    private Processo processo;

    public RegistroFinanceiro() {
    }

    public RegistroFinanceiro(
            Integer id,
            Integer parcelaAtual,
            Integer totalParcelas,
            Double valorParcela,
            Double valorPago,
            Double valorPagar,
            Integer ano,
            MetodoPagamento metodoPagamento,
            TipoPagamento tipoPagamento,
            Meses mes,
            StatusFinanceiro statusFinanceiro,
            Usuario cliente,
            Processo processo
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
        this.cliente = cliente;
        this.processo = processo;
    }

    public RegistroFinanceiro(
            Integer parcelaAtual,
            Integer totalParcelas,
            Double valorParcela,
            Double valorPago,
            Double valorPagar,
            Integer ano,
            MetodoPagamento metodoPagamento,
            TipoPagamento tipoPagamento,
            Meses mes,
            Usuario cliente,
            Processo processo
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

    public Double getHonorarioSucumbencia() {
        return honorarioSucumbencia;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
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

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Meses getMes() {
        return mes;
    }

    public void setMes(Meses mes) {
        this.mes = mes;
    }

    public StatusFinanceiro getStatusFinanceiro() {
        return statusFinanceiro;
    }

    public void setStatusFinanceiro(StatusFinanceiro statusFinanceiro) {
        this.statusFinanceiro = statusFinanceiro;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
}