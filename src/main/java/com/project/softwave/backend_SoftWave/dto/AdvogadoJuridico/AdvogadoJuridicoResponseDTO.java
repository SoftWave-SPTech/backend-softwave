package com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta para dados do advogado jurídico")
public class AdvogadoJuridicoResponseDTO {

    @Schema(description = "Identificador único do advogado jurídico", example = "1")
    private Integer id;

    @Schema(description = "Número da OAB do advogado responsável", example = "56789")
    private Integer oab;

    @Schema(description = "Email da empresa/advogado", example = "advocacia.exemplo@empresa.com")
    private String email;

    @Schema(description = "Nome fantasia da empresa de advocacia", example = "Advocacia Pereira & Associados")
    private String nomeFantasia;

    @Schema(description = "Razão social da empresa", example = "Pereira e Associados Sociedade de Advogados LTDA")
    private String razaoSocial;

    @Schema(description = "CNPJ da empresa", example = "12.345.678/0001-90")
    private String cnpj;

    @Schema(description = "CEP do endereço comercial", example = "01310930")
    private String cep;

    @Schema(description = "Logradouro do endereço", example = "Avenida Paulista")
    private String logradouro;

    @Schema(description = "Bairro do endereço", example = "Bela Vista")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Complemento do endereço", example = "Sala 801, Torre Oeste")
    private String complemento;

    @Schema(description = "Telefone comercial de contato", example = "(11) 97654-3210")
    private String telefone;

    public static AdvogadoJuridicoResponseDTO toResponseDto(AdvogadoJuridico usuarioJuridico) {
        AdvogadoJuridicoResponseDTO responseDto = new AdvogadoJuridicoResponseDTO();

        responseDto.setId(usuarioJuridico.getId());
        responseDto.setNomeFantasia(usuarioJuridico.getNomeFantasia());
        responseDto.setEmail(usuarioJuridico.getEmail());
        responseDto.setRazaoSocial(usuarioJuridico.getRazaoSocial());
        responseDto.setCnpj(usuarioJuridico.getCnpj());
        responseDto.setLogradouro(usuarioJuridico.getLogradouro());
        responseDto.setBairro(usuarioJuridico.getBairro());
        responseDto.setCidade(usuarioJuridico.getCidade());
        responseDto.setTelefone(usuarioJuridico.getTelefone());
        responseDto.setCep(usuarioJuridico.getCep());
        responseDto.setComplemento(usuarioJuridico.getComplemento());
        responseDto.setOab(usuarioJuridico.getOab());

        return responseDto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
