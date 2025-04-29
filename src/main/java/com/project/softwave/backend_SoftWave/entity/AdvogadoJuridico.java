package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class AdvogadoJuridico  extends UsuarioJuridico {




    private Integer oab;

    @ManyToMany
    private List<Processo> processos;

    @OneToMany
    private List<RegistroFinanceiro> registros;

    @ManyToMany
    private List<Reuniao> reunioes;

    @OneToMany
    private List<ComentarioProcesso> comentarios;

    public AdvogadoJuridico() {

    }

    public AdvogadoJuridico(String cnpj, String nomeFantasia, String razaoSocial, Integer oab) {
        super(cnpj, nomeFantasia, razaoSocial);
        this.oab = oab;
    }

    public AdvogadoJuridico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String cnpj, String nomeFantasia, String razaoSocial, Integer oab) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto, cnpj, nomeFantasia, razaoSocial);
        this.oab = oab;
    }

    public AdvogadoJuridico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String foto, String cnpj, String nomeFantasia, String razaoSocial, Integer oab) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone, foto, cnpj, nomeFantasia, razaoSocial);
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
