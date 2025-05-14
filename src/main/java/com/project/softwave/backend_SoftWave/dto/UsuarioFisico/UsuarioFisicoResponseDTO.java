package com.project.softwave.backend_SoftWave.dto.UsuarioFisico;

import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta com dados de usuários físicos")
public class UsuarioFisicoResponseDTO {

    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;

    @Schema(description = "Nome completo do usuário", example = "Maria Helena Costa")
    private String nome;

    @Schema(description = "Email do usuário", example = "maria.costa@gmail.com")
    private String email;

    @Schema(description = "CPF do usuário", example = "43293406238")
    private String cpf;

    @Schema(description = "RG do usuário", example = "12.345.678-9")
    private String rg;

    @Schema(description = "CEP do endereço", example = "04567000")
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Rua das Acácias")
    private String logradouro;

    @Schema(description = "Bairro do endereço", example = "Vila Mariana")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Telefone para contato", example = "(11) 98877-6655")
    private String telefone;

    @Schema(description = "Complemento do endereço, se houver", example = "Apto 12, Bloco A")
    private String complemento;

    public static UsuarioFisicoResponseDTO toResponseDto(UsuarioFisico usuarioFisico) {
        UsuarioFisicoResponseDTO Dto = new UsuarioFisicoResponseDTO();
        Dto.setId(usuarioFisico.getId());
        Dto.setNome(usuarioFisico.getNome());
        Dto.setEmail(usuarioFisico.getEmail());
        Dto.setCpf(usuarioFisico.getCpf());
        Dto.setRg(usuarioFisico.getRg());
        Dto.setLogradouro(usuarioFisico.getLogradouro());
        Dto.setBairro(usuarioFisico.getBairro());
        Dto.setCidade(usuarioFisico.getCidade());
        Dto.setTelefone(usuarioFisico.getTelefone());
        Dto.setCep(usuarioFisico.getCep());
        Dto.setComplemento(usuarioFisico.getComplemento());

        return Dto;
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    // ... (mesmos da DTO original, sem o campo "senha")
}
