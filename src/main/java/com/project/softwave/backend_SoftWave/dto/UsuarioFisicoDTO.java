package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class UsuarioFisicoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    private String complemento;


    public UsuarioFisicoDTO(Integer id, String nome, String email, String senha, String cpf, String rg, Integer cep, String logradouro, String bairro, String cidade, String telefone, String complemento) {
        this.id = id;
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
        this.complemento = complemento;
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
        usuarioFisico.setLogradouro(dto.getLogradouro());
        usuarioFisico.setBairro(dto.getBairro());
        usuarioFisico.setCidade(dto.getCidade());
        usuarioFisico.setTelefone(dto.getTelefone());
        usuarioFisico.setCep(dto.getCep());
        usuarioFisico.setComplemento(dto.getComplemento());
        return usuarioFisico;
    }

    public static UsuarioFisicoDTO toResponseDto(UsuarioFisico usuarioFisico) {
        UsuarioFisicoDTO responseDto = new UsuarioFisicoDTO();
        responseDto.setId(usuarioFisico.getId());
        responseDto.setNome(usuarioFisico.getNome());
        responseDto.setEmail(usuarioFisico.getEmail());
        responseDto.setSenha(usuarioFisico.getSenha());
        responseDto.setCpf(usuarioFisico.getCpf());
        responseDto.setRg(usuarioFisico.getRg());
        responseDto.setLogradouro(usuarioFisico.getLogradouro());
        responseDto.setBairro(usuarioFisico.getBairro());
        responseDto.setCidade(usuarioFisico.getCidade());
        responseDto.setTelefone(usuarioFisico.getTelefone());
        responseDto.setCep(usuarioFisico.getCep());
        responseDto.setComplemento(usuarioFisico.getComplemento());

        return responseDto;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
