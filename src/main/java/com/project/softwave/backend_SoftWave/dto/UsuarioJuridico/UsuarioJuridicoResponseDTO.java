package com.project.softwave.backend_SoftWave.dto.UsuarioJuridico;

import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public class UsuarioJuridicoResponseDTO {

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
    @Schema(description = "Razão social da empresa", example = "XPTO LTDA")
    private String razaoSocial;

    @Schema(description = "CNPJ da empresa", example = "15529313000109")
    @CNPJ
    @NotBlank
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

    public static UsuarioJuridicoResponseDTO fromEntity(UsuarioJuridico entity) {
        UsuarioJuridicoResponseDTO dto = new UsuarioJuridicoResponseDTO();
        dto.setId(entity.getId());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setEmail(entity.getEmail());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setCnpj(entity.getCnpj());
        dto.setCep(entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setBairro(entity.getBairro());
        dto.setCidade(entity.getCidade());
        dto.setTelefone(entity.getTelefone());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNomeFantasia() { return nomeFantasia; }
    public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

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
}
