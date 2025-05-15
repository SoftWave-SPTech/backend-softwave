package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("usuario_juridico")
public class UsuarioJuridico extends Usuario {

    @Column(unique = true)
    private String cnpj;

    private String nomeFantasia;
    private String razaoSocial;

    public UsuarioJuridico(String cnpj, String nomeFantasia, String razaoSocial) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String cnpj, String nomeFantasia, String razaoSocial) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String cnpj, String nomeFantasia, String razaoSocial) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }


}
