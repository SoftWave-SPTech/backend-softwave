package com.project.softwave.backend_SoftWave.entity;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import jakarta.persistence.Entity;

@Entity
public class UsuarioJuridico extends Usuario{
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;

    public UsuarioJuridico(Integer id, String senha, String email, String cnpj, String nomeFantasia, String razaoSocial) {
        super(id, senha, email);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
    }

    public UsuarioJuridico(UsuarioJuridicoDTO usuarioJuridicoDTO) {
        super(null, usuarioJuridicoDTO.getSenha(), usuarioJuridicoDTO.getEmail());
        this.cnpj = usuarioJuridicoDTO.getCnpj();
        this.nomeFantasia = usuarioJuridicoDTO.getNomeFantasia();
        this.razaoSocial = usuarioJuridicoDTO.getRazaoSocial();
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
