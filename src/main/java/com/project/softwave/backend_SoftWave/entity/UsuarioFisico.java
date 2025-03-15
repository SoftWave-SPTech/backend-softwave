package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import jakarta.persistence.Entity;

@Entity
public class UsuarioFisico extends Usuario{
    private String nome;
    private String cpf;
    private String rg;

    public UsuarioFisico(Integer id, String senha, String email, String nome, String cpf, String rg) {
        super(id, senha, email);
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisico(UsuarioFisicoDTO  usuarioFisicoDTO) {
        super(null, usuarioFisicoDTO.getSenha(), usuarioFisicoDTO.getEmail());
        this.nome = usuarioFisicoDTO.getNome();
        this.cpf = usuarioFisicoDTO.getCpf();
        this.rg = usuarioFisicoDTO.getRg();
    }

    public UsuarioFisico() {
    }

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
