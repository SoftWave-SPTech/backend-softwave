package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
public class DocumentoPessoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String documento;
    private String conteudo;

//    TODO IMPLEMENTAR ESSE CAMPO NO FUTURO
//    @ManyToOne
//    @JoinColumn(name = "fk_cliente_id", referencedColumnName = "id")
//    private Usuario fkCliente;


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

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}
