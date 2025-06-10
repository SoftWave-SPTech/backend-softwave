package com.project.softwave.backend_SoftWave.dto.DTOsDash;

public class QtdClienteInativoAndAtivo {
    private String clienteAtivoOrInativo;
    private Integer qtdClienteAtivoOrInativo;

    public QtdClienteInativoAndAtivo() {
    }

    public QtdClienteInativoAndAtivo(String clienteAtivoOrInativo, Integer qtdClienteAtivoOrInativo) {
        this.clienteAtivoOrInativo = clienteAtivoOrInativo;
        this.qtdClienteAtivoOrInativo = qtdClienteAtivoOrInativo;
    }

    public QtdClienteInativoAndAtivo(String clienteAtivoOrInativo) {
        this.clienteAtivoOrInativo = clienteAtivoOrInativo;
    }

    public String getClienteAtivoOrInativo() {
        return clienteAtivoOrInativo;
    }

    public void setClienteAtivoOrInativo(String clienteAtivoOrInativo) {
        this.clienteAtivoOrInativo = clienteAtivoOrInativo;
    }

    public Integer getQtdClienteAtivoOrInativo() {
        return qtdClienteAtivoOrInativo;
    }

    public void setQtdClienteAtivoOrInativo(Integer qtdClienteAtivoOrInativo) {
        this.qtdClienteAtivoOrInativo = qtdClienteAtivoOrInativo;
    }
}
