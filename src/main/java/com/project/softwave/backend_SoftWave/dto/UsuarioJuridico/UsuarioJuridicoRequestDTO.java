package com.project.softwave.backend_SoftWave.dto.UsuarioJuridico;

import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

public class UsuarioJuridicoRequestDTO {

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

    @Schema(description = "CNPJ da empresa", example = "17330474000102")
    @CNPJ
    @NotBlank
    private String cnpj;

    @NotNull
    @Schema(description = "CEP do endereço", example = "03942030")
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

    @NotBlank
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Representante", example = "João Carlos")
    private String representante;

    public static UsuarioJuridico toEntity(UsuarioJuridicoRequestDTO dto) {
        UsuarioJuridico entity = new UsuarioJuridico();
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setCnpj(dto.getCnpj());
        entity.setCep(dto.getCep());
        entity.setLogradouro(dto.getLogradouro());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setTelefone(dto.getTelefone());
        entity.setComplemento(dto.getComplemento());
        entity.setNumero(dto.getNumero());
        entity.setRepresentante(dto.getRepresentante());
        return entity;
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getRazaoSocial() { return razaoSocial; }
    public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
}
