package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String senha;
    private String email;
    private Role role;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String complemento;
    private String telefone;
    //private String foto;

    public Usuario() {
    }

    public Usuario(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone) {
        this.id = id;
        this.senha = senha;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.telefone = telefone;
    }

    public Usuario(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone) {
        this.senha = senha;
        this.email = email;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.telefone = telefone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

//    public String getFoto() {
//        return foto;
//    }
//
//    public void setFoto(String foto) {
//        this.foto = foto;
//    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
