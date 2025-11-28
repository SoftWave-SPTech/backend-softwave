package com.project.softwave.backend_SoftWave.dto.DTOsDash;

import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.ReceitaUltimosMesesDTO;
import com.project.softwave.backend_SoftWave.dto.ProcessoSimplesDTO;

import java.util.List;

public class DashResponseDTO {

    private String valorTotalProcessos;
    private Integer quantidadeAdvogados;
    private Integer quantidadeClientes;
    private List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos;
//    private SetorComMaisProcessosDTO setorComMaisProcessos;
    private List<QtdPorSetorDTO> qtdProcessosPorSetor;
    private List<ProcessoSimplesDTO> processosOrdenadosPorData;
    private Integer quantidadeProcessosTotais;
    private List<ReceitaUltimosMesesDTO> receitaUltimos6Meses;

    public DashResponseDTO() {
    }

    public DashResponseDTO(
            String valorTotalProcessos,
            Integer quantidadeAdvogados,
            Integer quantidadeClientes,
            List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos,
            List<QtdPorSetorDTO> qtdProcessosPorSetor,
            List<ProcessoSimplesDTO> processosOrdenadosPorData,
            Integer quantidadeProcessosTotais,
            List<ReceitaUltimosMesesDTO> receitaUltimos6Meses
    ) {
        this.valorTotalProcessos = valorTotalProcessos;
        this.quantidadeAdvogados = quantidadeAdvogados;
        this.quantidadeClientes = quantidadeClientes;
        this.clientesInativosAndAtivos = clientesInativosAndAtivos;
        this.qtdProcessosPorSetor = qtdProcessosPorSetor;
        this.processosOrdenadosPorData = processosOrdenadosPorData;
        this.quantidadeProcessosTotais = quantidadeProcessosTotais;
        this.receitaUltimos6Meses = receitaUltimos6Meses;
    }

    public DashResponseDTO(
            String valorTotalProcessos,
            Integer quantidadeAdvogados,
            Integer quantidadeClientes,
            List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos,
            List<QtdPorSetorDTO> qtdProcessosPorSetor,
            Integer quantidadeProcessosTotais
    ) {
        this.valorTotalProcessos = valorTotalProcessos;
        this.quantidadeAdvogados = quantidadeAdvogados;
        this.quantidadeClientes = quantidadeClientes;
        this.clientesInativosAndAtivos = clientesInativosAndAtivos;
        this.qtdProcessosPorSetor = qtdProcessosPorSetor;
        this.quantidadeProcessosTotais = quantidadeProcessosTotais;
    }

    public List<ReceitaUltimosMesesDTO> getReceitaUltimos6Meses() {
        return receitaUltimos6Meses;
    }

    public void setReceitaUltimos6Meses(List<ReceitaUltimosMesesDTO> receitaUltimos6Meses) {
        this.receitaUltimos6Meses = receitaUltimos6Meses;
    }

    public String getValorTotalProcessos() {
        return valorTotalProcessos;
    }

    public void setValorTotalProcessos(String valorTotalProcessos) {
        this.valorTotalProcessos = valorTotalProcessos;
    }

    public Integer getQuantidadeAdvogados() {
        return quantidadeAdvogados;
    }

    public void setQuantidadeAdvogados(Integer quantidadeAdvogados) {
        this.quantidadeAdvogados = quantidadeAdvogados;
    }

    public Integer getQuantidadeClientes() {
        return quantidadeClientes;
    }

    public void setQuantidadeClientes(Integer quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }

    public List<QtdClienteInativoAndAtivo> getClientesInativosAndAtivos() {
        return clientesInativosAndAtivos;
    }

    public void setClientesInativosAndAtivos(List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos) {
        this.clientesInativosAndAtivos = clientesInativosAndAtivos;
    }

    public List<QtdPorSetorDTO> getQtdProcessosPorSetor() {
        return qtdProcessosPorSetor;
    }

    public void setQtdProcessosPorSetor(List<QtdPorSetorDTO> qtdProcessosPorSetor) {
        this.qtdProcessosPorSetor = qtdProcessosPorSetor;
    }

    public Integer getQuantidadeProcessosTotais() {
        return quantidadeProcessosTotais;
    }

    public void setQuantidadeProcessosTotais(Integer quantidadeProcessosTotais) {
        this.quantidadeProcessosTotais = quantidadeProcessosTotais;
    }

    public List<ProcessoSimplesDTO> getProcessosOrdenadosPorData() {
        return processosOrdenadosPorData;
    }

    public void setProcessosOrdenadosPorData(List<ProcessoSimplesDTO> processosOrdenadosPorData) {
        this.processosOrdenadosPorData = processosOrdenadosPorData;
    }
}
