package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public class AdvogadoJuridicoDTO {



    @NotNull
    private Integer oab;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String nomeFantasia;

    @NotBlank
    private String razaoSocial;

    @CNPJ
    private String cnpj;

    public AdvogadoJuridicoDTO() {
    }

    public AdvogadoJuridicoDTO( Integer oab, String nomeFantasia, String email, String senha, String razaoSocial, String cnpj) {
        this.oab = oab;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.senha = senha;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
    }

    public AdvogadoJuridicoDTO(AdvogadoJuridico advogadoJuridico) {
        this.nomeFantasia = advogadoJuridico.getNomeFantasia();
        this.email = advogadoJuridico.getEmail();
        this.senha = advogadoJuridico.getSenha();
        this.razaoSocial = advogadoJuridico.getRazaoSocial();
        this.cnpj = advogadoJuridico.getCnpj();
        this.oab = advogadoJuridico.getOab();
    }

    public static AdvogadoJuridico toEntity(AdvogadoJuridicoDTO dto) {
        if(dto == null) {
            return null;
        }
        AdvogadoJuridico advogadoJuridico = new AdvogadoJuridico();
        advogadoJuridico.setOab(dto.getOab());
        advogadoJuridico.setNomeFantasia(dto.getNomeFantasia());
        advogadoJuridico.setEmail(dto.getEmail());
        advogadoJuridico.setSenha(dto.getSenha());
        advogadoJuridico.setRazaoSocial(dto.getRazaoSocial());
        advogadoJuridico.setCnpj(dto.getCnpj());
        return advogadoJuridico;
    }

    public static  AdvogadoJuridicoDTO toResponseDTO(AdvogadoJuridico advogado) {
        if(advogado == null) {
            return null;
        }
        AdvogadoJuridicoDTO dto = new AdvogadoJuridicoDTO();
        dto.setOab(advogado.getOab());
        dto.setNomeFantasia(advogado.getNomeFantasia());
        dto.setEmail(advogado.getEmail());
        dto.setSenha(advogado.getSenha());
        dto.setRazaoSocial(advogado.getRazaoSocial());
        dto.setCnpj(advogado.getCnpj());
        return dto;
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


    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }
}
