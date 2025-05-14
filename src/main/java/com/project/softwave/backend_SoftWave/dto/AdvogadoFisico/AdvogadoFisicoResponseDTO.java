package com.project.softwave.backend_SoftWave.dto.AdvogadoFisico;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta para dados do advogado físico")
public class AdvogadoFisicoResponseDTO {

    @Schema(description = "Identificador único do advogado", example = "1")
    private Integer id;

    @Schema(description = "Número da OAB", example = "123456")
    private Integer oab;

    @Schema(description = "Nome completo do advogado", example = "João Carlos Mendes")
    private String nome;

    @Schema(description = "Email do advogado", example = "joao.mendes@advocacia.com")
    private String email;

    @Schema(description = "CPF do advogado", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "RG do advogado", example = "12.345.678-9")
    private String rg;

    @Schema(description = "CEP do endereço", example = "04567000")
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Rua das Laranjeiras")
    private String logradouro;

    @Schema(description = "Bairro do endereço", example = "Jardim Paulista")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Complemento do endereço", example = "Apto 102, Bloco B")
    private String complemento;

    @Schema(description = "Telefone de contato", example = "(11) 98765-4321")
    private String telefone;

    public static AdvogadoFisicoResponseDTO toResponseDTO(AdvogadoFisico advogado) {
        if (advogado == null) {
            return null;
        }
        AdvogadoFisicoResponseDTO dto = new AdvogadoFisicoResponseDTO();
        dto.setId(advogado.getId());
        dto.setOab(advogado.getOab());
        dto.setNome(advogado.getNome());
        dto.setEmail(advogado.getEmail());
        dto.setCpf(advogado.getCpf());
        dto.setRg(advogado.getRg());
        dto.setCep(advogado.getCep());
        dto.setLogradouro(advogado.getLogradouro());
        dto.setCidade(advogado.getCidade());
        dto.setTelefone(advogado.getTelefone());
        dto.setBairro(advogado.getBairro());
        dto.setComplemento(advogado.getComplemento());
        return dto;
    }


    // Getters e Setters omitidos por brevidade (adicione-os conforme necessário)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
