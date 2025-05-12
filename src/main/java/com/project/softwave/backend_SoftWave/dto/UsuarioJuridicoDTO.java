package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;
    @NotBlank
    @Schema(description = "Nome fantasia da empresa", example = "Empresa XPTO")
    private String nomeFantasia;

    @Email
    @Schema(description = "Email do usuário", example = "usuario@exemplo.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "Senha123@")
    private String senha;

    @NotBlank
    @Schema(description = "Razão social da empresa", example = "XPTO LTDA")
    private String razaoSocial;

    @Schema(description = "CNPJ da empresa", example = "15529313000109")
    @CNPJ
    private String cnpj;

    @NotNull
    @Schema(description = "CEP do endereço", example = "03471047")
    private String cep;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Rua das Flores")
    private String logradouro;

    @NotBlank
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotBlank
    @Schema(description = "Telefone para contato", example = "(11) 91234-5678")
    private String telefone;

    @Schema(description = "Complemento do endereço", example = "Sala 3, Edifício Central")
    private String complemento;




    public UsuarioJuridicoDTO() {
    }

    public UsuarioJuridicoDTO(Integer id, String nomeFantasia, String email, String senha, String razaoSocial, String cnpj, String cep, String logradouro, String bairro, String cidade, String telefone, String complemento) {
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
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
