package com.project.softwave.backend_SoftWave.Jobs.ProcessoModel;

import jakarta.persistence.*;

//@Entity
public class DadosDelegacia {

//            "documento": "Inquérito Policial",
//             "numero": "2053985/2024",
//             "distrito_policial": "02º D.P. CARAPICUIBA",
//             "municipio": "Carapicuíba-SP"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String documento;
    private String numero;
    private String distritoPolicial;
    private String municipio;
    @ManyToOne
    @JoinColumn(name = "processo_id")
    private Processo processo;

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDistritoPolicial() {
        return distritoPolicial;
    }

    public void setDistritoPolicial(String distritoPolicial) {
        this.distritoPolicial = distritoPolicial;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
