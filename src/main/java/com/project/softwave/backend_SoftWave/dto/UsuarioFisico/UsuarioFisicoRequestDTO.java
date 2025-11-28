package com.project.softwave.backend_SoftWave.dto.UsuarioFisico;

import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "DTO para criação e atualização de usuários físicos")
public class UsuarioFisicoRequestDTO {

    @NotBlank
    @Schema(description = "Nome completo do usuário", example = "Maria Helena Costa")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "maria.costa@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "Token de primeiro acesso", example = "49454487")
    private String tokenPrimeiroAcesso;

    @CPF(message= "O CPF informado não é válido")
    @Schema(description = "CPF do usuário", example = "43293406238")
    private String cpf;

    @NotNull
    @Schema(description = "RG do usuário", example = "12.345.678-9")
    private String rg;

    @NotNull
    @Schema(description = "CEP do endereço", example = "04567000")
    private String cep;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Rua das Acácias")
    private String logradouro;

    @NotBlank
    @Schema(description = "Bairro do endereço", example = "Vila Mariana")
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @NotBlank
    @Schema(description = "Telefone para contato", example = "(11) 98877-6655")
    private String telefone;

    @Schema(description = "Complemento do endereço, se houver", example = "Apto 12, Bloco A")
    private String complemento;

    @NotBlank
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    public static UsuarioFisico toEntity(UsuarioFisicoRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioFisico entity = new UsuarioFisico();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha("SenhaTemporaria123!");
        entity.setTokenPrimeiroAcesso(dto.getTokenPrimeiroAcesso());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setLogradouro(dto.getLogradouro());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setTelefone(dto.getTelefone());
        entity.setCep(dto.getCep());
        entity.setComplemento(dto.getComplemento());
        entity.setNumero(dto.getNumero());
        return entity;
    }

    // Getters e Setters
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenPrimeiroAcesso() {
        return tokenPrimeiroAcesso;
    }

    public void setTokenPrimeiroAcesso(String tokenPrimeiroAcesso) {
        this.tokenPrimeiroAcesso = tokenPrimeiroAcesso;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


    // ... (gerar automaticamente ou colar os mesmos da DTO original, menos o ID)
}
