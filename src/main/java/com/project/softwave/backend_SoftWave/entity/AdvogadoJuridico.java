package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@DiscriminatorValue("advogado_juridico")
public class AdvogadoJuridico extends UsuarioJuridico {

    @Column(unique = true)
    private Integer oab;

    public AdvogadoJuridico() {
    }

    public AdvogadoJuridico(String cnpj, String nomeFantasia, String razaoSocial, String representante, Integer oab) {
        super(cnpj, nomeFantasia, razaoSocial, representante);
        this.oab = oab;
    }

    public AdvogadoJuridico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String cnpj, String nomeFantasia, String razaoSocial, String representante, Integer oab) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone, cnpj, nomeFantasia, razaoSocial, representante);
        this.oab = oab;
    }

    public AdvogadoJuridico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String cnpj, String nomeFantasia, String razaoSocial, String representante, Integer oab) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone, cnpj, nomeFantasia, razaoSocial, representante);
        this.oab = oab;
    }

    public AdvogadoJuridico(Integer oab) {
        this.oab = oab;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }



}
