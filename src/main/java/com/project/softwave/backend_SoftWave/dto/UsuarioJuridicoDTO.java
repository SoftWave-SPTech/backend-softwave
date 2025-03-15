package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;

public class UsuarioJuridicoDTO {
    private Integer id;
    private String nomeFantasia;
    private String email;
    private String senha;
    private String razaoSocial;
    private String cnpj;

    public UsuarioJuridicoDTO() {
    }

    public UsuarioJuridicoDTO(Integer id, String nomeFantasia, String email, String senha, String razaoSocial, String cnpj) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.senha = senha;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public UsuarioJuridicoDTO(UsuarioJuridico usuarioJuridico) {
        this.id = usuarioJuridico.getId();
        this.nomeFantasia = usuarioJuridico.getNomeFantasia();
        this.email = usuarioJuridico.getEmail();
        this.senha = usuarioJuridico.getSenha();
        this.razaoSocial = usuarioJuridico.getRazaoSocial();
        this.cnpj = usuarioJuridico.getCnpj();
    }

    public Integer getId() {
        return id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
