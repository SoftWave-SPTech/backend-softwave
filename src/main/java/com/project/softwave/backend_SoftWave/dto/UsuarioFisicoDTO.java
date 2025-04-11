package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;

public class UsuarioFisicoDTO {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String rg;

    public UsuarioFisicoDTO(Integer id, String nome, String email, String senha, String cpf, String rg) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
    }

    public UsuarioFisicoDTO(UsuarioFisico usuarioFisico) {
        this.id = usuarioFisico.getId();
        this.nome = usuarioFisico.getNome();
        this.email = usuarioFisico.getEmail();
        this.senha = usuarioFisico.getSenha();
        this.cpf = usuarioFisico.getCpf();
        this.rg = usuarioFisico.getRg();
    }

    public UsuarioFisicoDTO() {
    }

    public static UsuarioFisico toEntity(UsuarioFisicoDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioFisico usuarioFisico = new UsuarioFisico();
        usuarioFisico.setId(dto.getId());
        usuarioFisico.setNome(dto.getNome());
        usuarioFisico.setEmail(dto.getEmail());
        usuarioFisico.setSenha(dto.getSenha());
        usuarioFisico.setCpf(dto.getCpf());
        usuarioFisico.setRg(dto.getRg());
        return usuarioFisico;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }
}
