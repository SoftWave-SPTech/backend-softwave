package com.project.softwave.backend_SoftWave.Jobs.messaging;

import java.io.Serializable;

public class ProcessoMessage implements Serializable {
    private String numeroProcesso;
    private String correlationId;

    public ProcessoMessage() {}

    public ProcessoMessage(String numeroProcesso, String correlationId) {
        this.numeroProcesso = numeroProcesso;
        this.correlationId = correlationId;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
