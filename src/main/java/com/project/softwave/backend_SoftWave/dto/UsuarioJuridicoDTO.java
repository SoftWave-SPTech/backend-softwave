package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

public class UsuarioJuridicoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nomeFantasia;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String razaoSocial;

    @CNPJ
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
