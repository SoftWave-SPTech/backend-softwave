package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class AdvogadoFisicoDTO {

    
    @NotNull
    private Integer oab;
    
    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String senha;

    @CPF
    private String cpf;

    @NotNull
    private String rg;

    public AdvogadoFisicoDTO( Integer oab, String nome, String email, String senha, String cpf, String rg) {
        this.oab = oab;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
    }

    public AdvogadoFisicoDTO(AdvogadoFisico advogadoFisico) {
        this.nome = advogadoFisico.getNome();
        this.email = advogadoFisico.getEmail();
        this.senha = advogadoFisico.getSenha();
        this.cpf = advogadoFisico.getCpf();
        this.rg = advogadoFisico.getRg();
        this.oab = advogadoFisico.getOab();

    }

    public AdvogadoFisicoDTO() {
    }

    public static AdvogadoFisico toEntity(AdvogadoFisicoDTO dto) {
        if (dto == null) {
            return null;
        }
        AdvogadoFisico advogadoFisico = new AdvogadoFisico();
        advogadoFisico.setNome(dto.getNome());
        advogadoFisico.setEmail(dto.getEmail());
        advogadoFisico.setSenha(dto.getSenha());
        advogadoFisico.setCpf(dto.getCpf());
        advogadoFisico.setRg(dto.getRg());
        advogadoFisico.setOab(dto.getOab());
        return advogadoFisico;
    }

        public static  AdvogadoFisicoDTO toResponseDTO(AdvogadoFisico advogado) {
        if(advogado == null) {
            return null;
        }
        AdvogadoFisicoDTO dto = new AdvogadoFisicoDTO();
        dto.setOab(advogado.getOab());
        dto.setNome(advogado.getNome());
        dto.setEmail(advogado.getEmail());
        dto.setSenha(advogado.getSenha());
        dto.setCpf(advogado.getCpf());
        dto.setRg(advogado.getRg());
        return dto;
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

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }
}
