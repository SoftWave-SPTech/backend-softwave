package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@DiscriminatorValue("advogado_fisico")
public class AdvogadoFisico extends UsuarioFisico {

    @Column(unique = true, nullable = false)
    private Integer oab;

    public AdvogadoFisico() {
    }

    public AdvogadoFisico(Integer oab) {
        this.oab = oab;
    }

    public AdvogadoFisico(String nome, String cpf, String rg, Integer oab) {
        super(nome, cpf, rg);
        this.oab = oab;
    }

    public AdvogadoFisico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg, Integer oab) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone, nome, cpf, rg);
        this.oab = oab;
    }

    public AdvogadoFisico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg, Integer oab) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone, nome, cpf, rg);
        this.oab = oab;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }
}
