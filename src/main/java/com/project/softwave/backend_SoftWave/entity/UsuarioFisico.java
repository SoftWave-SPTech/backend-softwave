package com.project.softwave.backend_SoftWave.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("usuario_fisico")
public class UsuarioFisico extends Usuario{

    private String nome;

    @Column(unique = true, nullable = false)
    private String cpf;

    private String rg;

    public UsuarioFisico() {
    }

    public UsuarioFisico(String nome, String cpf, String rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisico(Integer id, String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg) {
        super(id, senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisico(String senha, String email, String cep, String logradouro, String bairro, String cidade, String complemento, String telefone, String nome, String cpf, String rg) {
        super(senha, email, cep, logradouro, bairro, cidade, complemento, telefone);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    //
//    public UsuarioFisico(UsuarioFisicoDTO  usuarioFisicoDTO) {
//        super(null, usuarioFisicoDTO.getSenha(), usuarioFisicoDTO.getEmail());
//        this.nome = usuarioFisicoDTO.getNome();
//        this.cpf = usuarioFisicoDTO.getCpf();
//        this.rg = usuarioFisicoDTO.getRg();
//    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }




}
