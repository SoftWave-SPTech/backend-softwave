package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class AdvogadoFisico extends UsuarioFisico {


    private Integer oab;

    @ManyToMany
    private List<Processo> processos;

    @OneToMany
    private List<ComentarioProcesso> comentarios;

    @OneToMany
    private List<RegistroFinanceiro> registros;

    @ManyToMany
    private List<Reuniao> reunioes;

    public AdvogadoFisico() {
    }

    public AdvogadoFisico(Integer oab) {
        this.oab = oab;
    }

    public AdvogadoFisico(String nome, String cpf, String rg, Integer oab) {
        super(nome, cpf, rg);
        this.oab = oab;
    }

    public AdvogadoFisico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String nome, String cpf, String rg, Integer oab) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto, nome, cpf, rg);
        this.oab = oab;
    }

    public AdvogadoFisico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String nome, String cpf, String rg, Integer oab) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto, nome, cpf, rg);
        this.oab = oab;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }
}
