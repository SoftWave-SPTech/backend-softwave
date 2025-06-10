package com.project.softwave.backend_SoftWave.dto.DTOsDash;

import java.util.List;

public class DashResponseDTO {

    private String valorTotalProcessos;
    private Integer quantidadeAdvogados;
    private List<QtdClienteInativoAndAtivo> ClientesInativosAndAtivos;
    private SetorComMaisProcessosDTO setorComMaisProcessos;
    private List<QtdPorSetorDTO> qtdProcessosPorSetor;
    private Integer quantidadeProcessosTotais;

    public DashResponseDTO() {
    }

    public DashResponseDTO(
            String valorTotalProcessos,
            Integer quantidadeAdvogados,
            List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos,
            SetorComMaisProcessosDTO setorComMaisProcessos,
            List<QtdPorSetorDTO> qtdProcessosPorSetor,
            Integer quantidadeProcessosTotais
    ) {
        this.valorTotalProcessos = valorTotalProcessos;
        this.quantidadeAdvogados = quantidadeAdvogados;
        ClientesInativosAndAtivos = clientesInativosAndAtivos;
        this.setorComMaisProcessos = setorComMaisProcessos;
        this.qtdProcessosPorSetor = qtdProcessosPorSetor;
        this.quantidadeProcessosTotais = quantidadeProcessosTotais;
    }

    public SetorComMaisProcessosDTO getSetorComMaisProcessos() {
        return setorComMaisProcessos;
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

    public List<QtdClienteInativoAndAtivo> getClientesInativosAndAtivos() {
        return ClientesInativosAndAtivos;
    }

    public void setClientesInativosAndAtivos(List<QtdClienteInativoAndAtivo> clientesInativosAndAtivos) {
        ClientesInativosAndAtivos = clientesInativosAndAtivos;
    }

    public void setSetorComMaisProcessos(SetorComMaisProcessosDTO setorComMaisProcessos) {
        this.setorComMaisProcessos = setorComMaisProcessos;
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
