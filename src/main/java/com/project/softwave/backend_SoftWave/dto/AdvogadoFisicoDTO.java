package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class AdvogadoFisicoDTO {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    public AdvogadoFisicoDTO(Integer id, Integer oab, String nome, String email, String senha, String cpf, String rg, Integer cep, String logradouro, String bairro, String cidade, String telefone) {
        this.id = id;
        this.oab = oab;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.rg = rg;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public AdvogadoFisicoDTO(AdvogadoFisico advogadoFisico) {
        this.id = advogadoFisico.getId();
        this.nome = advogadoFisico.getNome();
        this.email = advogadoFisico.getEmail();
        this.senha = advogadoFisico.getSenha();
        this.cpf = advogadoFisico.getCpf();
        this.rg = advogadoFisico.getRg();
        this.oab = advogadoFisico.getOab();
        this.cep = advogadoFisico.getCep();
        this.logradouro = advogadoFisico.getLogradouro();
        this.bairro = advogadoFisico.getBairro();
        this.cidade = advogadoFisico.getCidade();
        this.telefone = advogadoFisico.getTelefone();


    }

    public AdvogadoFisicoDTO() {
    }

    public static AdvogadoFisico toEntity(AdvogadoFisicoDTO dto) {
        if (dto == null) {
            return null;
        }
        AdvogadoFisico advogadoFisico = new AdvogadoFisico();
        advogadoFisico.setId(dto.getId());
        advogadoFisico.setNome(dto.getNome());
        advogadoFisico.setEmail(dto.getEmail());
        advogadoFisico.setSenha(dto.getSenha());
        advogadoFisico.setCpf(dto.getCpf());
        advogadoFisico.setRg(dto.getRg());
        advogadoFisico.setOab(dto.getOab());
        advogadoFisico.setBairro(dto.getBairro());
        advogadoFisico.setCidade(dto.getCidade());
        advogadoFisico.setTelefone(dto.getTelefone());
        advogadoFisico.setLogradouro(dto.getLogradouro());
        advogadoFisico.setCep(dto.getCep());

        return advogadoFisico;
    }

        public static  AdvogadoFisicoDTO toResponseDTO(AdvogadoFisico advogado) {
        if(advogado == null) {
            return null;
        }
        AdvogadoFisicoDTO dto = new AdvogadoFisicoDTO();
        dto.setId(advogado.getId());
        dto.setOab(advogado.getOab());
        dto.setNome(advogado.getNome());
        dto.setEmail(advogado.getEmail());
        dto.setSenha(advogado.getSenha());
        dto.setCpf(advogado.getCpf());
        dto.setRg(advogado.getRg());
        dto.setCep(advogado.getCep());
        dto.setLogradouro(advogado.getLogradouro());
        dto.setCidade(advogado.getCidade());
        dto.setTelefone(advogado.getTelefone());
        dto.setBairro(advogado.getBairro());
        return dto;
    }

    public Integer getId() {
        return id;
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
