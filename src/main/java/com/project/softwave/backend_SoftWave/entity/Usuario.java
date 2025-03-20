package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String senha;
    private String email;

    public Usuario() {
    }

    public Usuario(Integer id, String senha, String email) {
        this.id = id;
        this.senha = senha;
        this.email = email;
    }

    public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
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
}
