package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public class UsuarioJuridicoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nomeFantasia;

    @Email
    private String email;

    @NotBlank
    private String senha;

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
    private String complemento;



    public UsuarioJuridicoDTO() {
    }

    public UsuarioJuridicoDTO(Integer id, String nomeFantasia, String email, String senha, String razaoSocial, String cnpj, Integer cep, String logradouro, String bairro, String cidade, String telefone, String complemento) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.senha = senha;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.telefone = telefone;
        this.complemento = complemento;
    }

    public static UsuarioJuridico toEntity(UsuarioJuridicoDTO dto) {
        if(dto == null) {
            return null;
        }
        UsuarioJuridico usuarioJuridico = new UsuarioJuridico();
        usuarioJuridico.setId(dto.getId());
        usuarioJuridico.setNomeFantasia(dto.getNomeFantasia());
        usuarioJuridico.setEmail(dto.getEmail());
        usuarioJuridico.setSenha(dto.getSenha());
        usuarioJuridico.setRazaoSocial(dto.getRazaoSocial());
        usuarioJuridico.setCnpj(dto.getCnpj());
        usuarioJuridico.setLogradouro(dto.getLogradouro());
        usuarioJuridico.setBairro(dto.getBairro());
        usuarioJuridico.setCidade(dto.getCidade());
        usuarioJuridico.setTelefone(dto.getTelefone());
        usuarioJuridico.setCep(dto.getCep());
        usuarioJuridico.setComplemento(dto.getComplemento());

        return usuarioJuridico;
    }

    public static UsuarioJuridicoDTO toResponseDto(UsuarioJuridico usuarioJuridico) {
        UsuarioJuridicoDTO responseDto = new UsuarioJuridicoDTO();

        responseDto.setId(usuarioJuridico.getId());
        responseDto.setNomeFantasia(usuarioJuridico.getNomeFantasia());
        responseDto.setEmail(usuarioJuridico.getEmail());
        responseDto.setSenha(usuarioJuridico.getSenha());
        responseDto.setRazaoSocial(usuarioJuridico.getRazaoSocial());
        responseDto.setCnpj(usuarioJuridico.getCnpj());
        responseDto.setLogradouro(usuarioJuridico.getLogradouro());
        responseDto.setBairro(usuarioJuridico.getBairro());
        responseDto.setCidade(usuarioJuridico.getCidade());
        responseDto.setTelefone(usuarioJuridico.getTelefone());
        responseDto.setCep(usuarioJuridico.getCep());
        responseDto.setComplemento(usuarioJuridico.getComplemento());


        return responseDto;
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
