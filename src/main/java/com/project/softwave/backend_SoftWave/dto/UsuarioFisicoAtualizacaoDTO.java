package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioFisicoAtualizacaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;

    @NotBlank
    @Schema(description = "Nome completo do usuário", example = "Maria Helena Costa")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "maria.helena@gmail.com")
    private String email;

    @NotNull
    @Schema(description = "CEP do endereço", example = "01234000")
    private String cep;

    @NotBlank
    @Schema(description = "Logradouro do endereço", example = "Rua dos Jacarandás")
    private String logradouro;

    @NotBlank
    @Schema(description = "Bairro do endereço", example = "Vila Nova")
    private String bairro;

    @NotBlank
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Complemento do endereço", example = "Apto 303, Torre Sul")
    private String complemento;

    @NotBlank
    @Schema(description = "Telefone de contato", example = "(11) 91234-5678")
    private String telefone;


   // private String foto;



    public static UsuarioFisico toEntity (UsuarioFisicoAtualizacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioFisico usuarioFisico = new UsuarioFisico();
        usuarioFisico.setId(dto.getId());
        usuarioFisico.setNome(dto.getNome());
        usuarioFisico.setEmail(dto.getEmail());
        usuarioFisico.setCep(dto.getCep());
        usuarioFisico.setLogradouro(dto.getLogradouro());
        usuarioFisico.setBairro(dto.getBairro());
        usuarioFisico.setCidade(dto.getCidade());
        usuarioFisico.setTelefone(dto.getTelefone());
      //  usuarioFisico.setFoto(dto.getFoto());
        usuarioFisico.setComplemento(dto.getComplemento());

        return usuarioFisico;
    }

    public static UsuarioFisicoAtualizacaoDTO toResponseDto(UsuarioFisico usuarioFisico) {
        if (usuarioFisico == null) {
            return null;
        }
        UsuarioFisicoAtualizacaoDTO dto = new UsuarioFisicoAtualizacaoDTO();
        dto.setId(usuarioFisico.getId());
        dto.setNome(usuarioFisico.getNome());
        dto.setEmail(usuarioFisico.getEmail());
        dto.setCep(usuarioFisico.getCep());
        dto.setLogradouro(usuarioFisico.getLogradouro());
        dto.setBairro(usuarioFisico.getBairro());
        dto.setCidade(usuarioFisico.getCidade());
        dto.setTelefone(usuarioFisico.getTelefone());
    //    dto.setFoto(usuarioFisico.getFoto());
        dto.setComplemento(usuarioFisico.getComplemento());
        return dto;
    }

    public static AdvogadoFisico toEntityAdvogado (UsuarioFisicoAtualizacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        AdvogadoFisico advogadoFisico = new AdvogadoFisico();
        advogadoFisico.setId(dto.getId());
        advogadoFisico.setNome(dto.getNome());
        advogadoFisico.setEmail(dto.getEmail());
        advogadoFisico.setCep(dto.getCep());
        advogadoFisico.setLogradouro(dto.getLogradouro());
        advogadoFisico.setBairro(dto.getBairro());
        advogadoFisico.setCidade(dto.getCidade());
        advogadoFisico.setTelefone(dto.getTelefone());
     //   advogadoFisico.setFoto(dto.getFoto());
        advogadoFisico.setComplemento(dto.getComplemento());
        return advogadoFisico;
    }

    public static UsuarioFisicoAtualizacaoDTO toResponseAdvogadoDto(AdvogadoFisico advogadoFisico) {
        if (advogadoFisico == null) {
            return null;
        }
        UsuarioFisicoAtualizacaoDTO dto = new UsuarioFisicoAtualizacaoDTO();
        dto.setId(advogadoFisico.getId());
        dto.setNome(advogadoFisico.getNome());
        dto.setEmail(advogadoFisico.getEmail());
        dto.setCep(advogadoFisico.getCep());
        dto.setLogradouro(advogadoFisico.getLogradouro());
        dto.setBairro(advogadoFisico.getBairro());
        dto.setCidade(advogadoFisico.getCidade());
        dto.setTelefone(advogadoFisico.getTelefone());
     //   dto.setFoto(advogadoFisico.getFoto());
        dto.setComplemento(advogadoFisico.getComplemento());
        return dto;
    }


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


    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
//
//    public String getFoto() {
//        return foto;
//    }
//
//    public void setFoto(String foto) {
//        this.foto = foto;
//    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
