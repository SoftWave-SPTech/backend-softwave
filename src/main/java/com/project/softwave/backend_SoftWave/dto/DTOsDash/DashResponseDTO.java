package com.project.softwave.backend_SoftWave.dto.DTOsDash;

import java.util.List;

public class DashResponseDTO {

    private String valorTotalProcessos;
    private Integer quantidadeAdvogados;
    private Integer quantidadeClientes;
    private List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos;
//    private SetorComMaisProcessosDTO setorComMaisProcessos;
    private List<QtdPorSetorDTO> qtdProcessosPorSetor;
    private Integer quantidadeProcessosTotais;

    public DashResponseDTO() {
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
}
