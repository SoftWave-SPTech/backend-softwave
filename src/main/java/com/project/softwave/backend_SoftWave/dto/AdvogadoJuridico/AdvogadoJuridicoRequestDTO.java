package com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Schema(description = "DTO para cadastro de advogado jurídico")
public class AdvogadoJuridicoRequestDTO {

    @NotNull
    @Size(min = 6, max = 6, message = "O número da OAB deve ter exatamente 6 dígitos")
    @Pattern(regexp = "\\d+", message = "O número da OAB deve conter apenas números")
    @Schema(description = "Número da OAB do advogado responsável", example = "567899")
    private String oab;
  
    @Email
    @Schema(description = "Email da empresa/advogado", example = "advocacia.exemplo@empresa.com")
    private String email;

    @NotBlank
    @Schema(description = "Token de primeiro acesso", example = "49454487")
    private String tokenPrimeiroAcesso;

    @NotBlank
    @Schema(description = "Nome fantasia da empresa de advocacia", example = "Advocacia Pereira & Associados")
    private String nomeFantasia;

    @NotBlank
    @Schema(description = "Razão social da empresa", example = "Pereira e Associados Sociedade de Advogados LTDA")
    private String razaoSocial;

    @CNPJ(message = "O CNPJ informado não é válido")
    @Schema(description = "CNPJ da empresa", example = "96678175000121")
    private String cnpj;

    @NotNull
    @Schema(description = "CEP do endereço comercial", example = "01310930")
    private String cep;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Avenida Paulista")
    private String logradouro;

    @NotBlank
    @Schema(description = "Bairro do endereço", example = "Bela Vista")
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Complemento do endereço", example = "Sala 801, Torre Oeste")
    private String complemento;

    @NotBlank
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @NotBlank
    @Schema(description = "Telefone comercial de contato", example = "(11) 97654-3210")
    private String telefone;

    @NotBlank
    @Schema(description = "Representante", example = "João Carlos")
    private String representante;

    public static AdvogadoJuridico toEntity(AdvogadoJuridicoRequestDTO dto) {
        if(dto == null) {
            return null;
        }
        AdvogadoJuridico usuarioJuridico = new AdvogadoJuridico();
        usuarioJuridico.setNomeFantasia(dto.getNomeFantasia());
        usuarioJuridico.setEmail(dto.getEmail());
        usuarioJuridico.setSenha("SenhaTemporaria123!");
        usuarioJuridico.setTokenPrimeiroAcesso(dto.getTokenPrimeiroAcesso());
        usuarioJuridico.setRazaoSocial(dto.getRazaoSocial());
        usuarioJuridico.setCnpj(dto.getCnpj());
        usuarioJuridico.setLogradouro(dto.getLogradouro());
        usuarioJuridico.setBairro(dto.getBairro());
        usuarioJuridico.setCidade(dto.getCidade());
        usuarioJuridico.setTelefone(dto.getTelefone());
        usuarioJuridico.setCep(dto.getCep());
        usuarioJuridico.setComplemento(dto.getComplemento());
        usuarioJuridico.setNumero(dto.getNumero());
        usuarioJuridico.setOab(Integer.parseInt(dto.getOab()));
        usuarioJuridico.setRepresentante(dto.getRepresentante());

        return usuarioJuridico;
    }

    // Getters e Setters omitidos por brevidade (adicione-os conforme necessário)

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }
}
