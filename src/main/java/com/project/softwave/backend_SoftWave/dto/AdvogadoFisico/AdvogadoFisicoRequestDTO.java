package com.project.softwave.backend_SoftWave.dto.AdvogadoFisico;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Schema(description = "DTO para cadastro de advogado físico")
public class AdvogadoFisicoRequestDTO{

    @NotNull
    @Schema(description = "Número da OAB", example = "123456")
    private Integer oab;

    @NotBlank
    @Schema(description = "Nome completo do advogado", example = "João Carlos Mendes")
    private String nome;

    @Email
    @Schema(description = "Email do advogado", example = "joao.mendes@advocacia.com")
    private String email;

    @NotBlank
    @Schema(description = "Senha de acesso ao sistema", example = "SenhaSegura@2024")
    private String senha;

    @CPF
    @Schema(description = "CPF do advogado", example = "90869258044")
    private String cpf;

    @NotBlank
    @Schema(description = "RG do advogado", example = "12.345.678-9")
    private String rg;

    @NotBlank
    @Schema(description = "CEP do endereço", example = "04567000")
    private String cep;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Rua das Laranjeiras")
    private String logradouro;

    @NotBlank
    @Schema(description = "Bairro do endereço", example = "Jardim Paulista")
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Complemento do endereço", example = "Apto 102, Bloco B")
    private String complemento;

    @NotBlank
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @NotBlank
    @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
    private String telefone;

    public static AdvogadoFisico toEntity(AdvogadoFisicoRequestDTO dto) {
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
        advogadoFisico.setBairro(dto.getBairro());
        advogadoFisico.setCidade(dto.getCidade());
        advogadoFisico.setTelefone(dto.getTelefone());
        advogadoFisico.setLogradouro(dto.getLogradouro());
        advogadoFisico.setCep(dto.getCep());
        advogadoFisico.setComplemento(dto.getComplemento());
        advogadoFisico.setNumero(dto.getNumero());
        return advogadoFisico;
    }



    // Getters e Setters omitidos por brevidade (adicione-os conforme necessário)
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
