package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public class AdvogadoJuridicoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @NotNull
    private Integer cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String telefone;

    public AdvogadoJuridicoDTO() {
    }

    public AdvogadoJuridicoDTO(Integer id, Integer oab, String email, String senha, String nomeFantasia, String razaoSocial, String cnpj, Integer cep, String logradouro, String bairro, String cidade, String telefone) {
        this.id = id;
        this.oab = oab;
        this.email = email;
        this.senha = senha;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public AdvogadoJuridicoDTO(AdvogadoJuridico advogadoJuridico) {
        this.id = advogadoJuridico.getId();
        this.nomeFantasia = advogadoJuridico.getNomeFantasia();
        this.email = advogadoJuridico.getEmail();
        this.senha = advogadoJuridico.getSenha();
        this.razaoSocial = advogadoJuridico.getRazaoSocial();
        this.cnpj = advogadoJuridico.getCnpj();
        this.oab = advogadoJuridico.getOab();
        this.cep = advogadoJuridico.getCep();
        this.logradouro = advogadoJuridico.getLogradouro();
        this.bairro = advogadoJuridico.getBairro();
        this.cidade = advogadoJuridico.getCidade();
        this.telefone = advogadoJuridico.getTelefone();

    }

    public static AdvogadoJuridico toEntity(AdvogadoJuridicoDTO dto) {
        if(dto == null) {
            return null;
        }
        AdvogadoJuridico advogadoJuridico = new AdvogadoJuridico();
        advogadoJuridico.setId(dto.getId());
        advogadoJuridico.setOab(dto.getOab());
        advogadoJuridico.setNomeFantasia(dto.getNomeFantasia());
        advogadoJuridico.setEmail(dto.getEmail());
        advogadoJuridico.setSenha(dto.getSenha());
        advogadoJuridico.setRazaoSocial(dto.getRazaoSocial());
        advogadoJuridico.setCnpj(dto.getCnpj());
        advogadoJuridico.setCep(dto.getCep());
        advogadoJuridico.setLogradouro(dto.getLogradouro());
        advogadoJuridico.setBairro(dto.getBairro());
        advogadoJuridico.setCidade(dto.getCidade());
        advogadoJuridico.setTelefone(dto.getTelefone());

        return advogadoJuridico;
    }

    public static  AdvogadoJuridicoDTO toResponseDTO(AdvogadoJuridico advogado) {
        if(advogado == null) {
            return null;
        }
        AdvogadoJuridicoDTO dto = new AdvogadoJuridicoDTO();
        dto.setId(advogado.getId());
        dto.setOab(advogado.getOab());
        dto.setNomeFantasia(advogado.getNomeFantasia());
        dto.setEmail(advogado.getEmail());
        dto.setSenha(advogado.getSenha());
        dto.setRazaoSocial(advogado.getRazaoSocial());
        dto.setCnpj(advogado.getCnpj());
        dto.setCidade(advogado.getCidade());
        dto.setTelefone(advogado.getTelefone());
        dto.setLogradouro(advogado.getLogradouro());
        dto.setBairro(advogado.getBairro());
        dto.setCep(advogado.getCep());
        return dto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
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
}
