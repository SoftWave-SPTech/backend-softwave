package com.project.softwave.backend_SoftWave.dto;


import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public class UsuarioJuridicoAtualizacaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nomeFantasia;
    
    @NotBlank
    private String razaoSocial;

    @CNPJ
    private String cnpj;

    @Email
    private String email;

    @NotBlank
    private String senha;


    @NotNull
    private Integer cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String telefone;
    private String complemento;

    private String foto;



    public static UsuarioJuridico toEntity (UsuarioJuridicoAtualizacaoDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioJuridico usuarioJuridico = new UsuarioJuridico();
        usuarioJuridico.setId(dto.getId());
        usuarioJuridico.setEmail(dto.getEmail());
        usuarioJuridico.setSenha(dto.getSenha());
        usuarioJuridico.setCnpj(dto.getCnpj());
        usuarioJuridico.setCep(dto.getCep());
        usuarioJuridico.setLogradouro(dto.getLogradouro());
        usuarioJuridico.setBairro(dto.getBairro());
        usuarioJuridico.setCidade(dto.getCidade());
        usuarioJuridico.setTelefone(dto.getTelefone());
        usuarioJuridico.setFoto(dto.getFoto());
        usuarioJuridico.setNomeFantasia(dto.getNomeFantasia());
        usuarioJuridico.setRazaoSocial(dto.getRazaoSocial());
        usuarioJuridico.setComplemento(dto.getComplemento());

        return usuarioJuridico;
    }

    public static UsuarioJuridicoAtualizacaoDTO toResponseDto(UsuarioJuridico usuarioJuridico) {
        if (usuarioJuridico == null) {
            return null;
        }
        UsuarioJuridicoAtualizacaoDTO dto = new UsuarioJuridicoAtualizacaoDTO();
        dto.setId(usuarioJuridico.getId());
        dto.setNomeFantasia(usuarioJuridico.getNomeFantasia());
        dto.setRazaoSocial(usuarioJuridico.getRazaoSocial());
        dto.setCnpj(usuarioJuridico.getCnpj());
        dto.setEmail(usuarioJuridico.getEmail());
        dto.setSenha(usuarioJuridico.getSenha());
        dto.setCep(usuarioJuridico.getCep());
        dto.setLogradouro(usuarioJuridico.getLogradouro());
        dto.setBairro(usuarioJuridico.getBairro());
        dto.setCidade(usuarioJuridico.getCidade());
        dto.setTelefone(usuarioJuridico.getTelefone());
        dto.setFoto(usuarioJuridico.getFoto());
        dto.setComplemento(usuarioJuridico.getComplemento());

        return dto;
    }

    public static AdvogadoJuridico toEntityAdvogado(UsuarioJuridicoAtualizacaoDTO dto, AdvogadoJuridico advogadoExistente) {
        if (dto == null) {
            return null;
        }

        AdvogadoJuridico advogadoJuridico = advogadoExistente;

        advogadoJuridico.setId(dto.getId());
        advogadoJuridico.setEmail(dto.getEmail());
        advogadoJuridico.setSenha(dto.getSenha());
        advogadoJuridico.setCnpj(dto.getCnpj());
        advogadoJuridico.setCep(dto.getCep());
        advogadoJuridico.setLogradouro(dto.getLogradouro());
        advogadoJuridico.setBairro(dto.getBairro());
        advogadoJuridico.setCidade(dto.getCidade());
        advogadoJuridico.setTelefone(dto.getTelefone());
        advogadoJuridico.setFoto(dto.getFoto());
        advogadoJuridico.setNomeFantasia(dto.getNomeFantasia());
        advogadoJuridico.setRazaoSocial(dto.getRazaoSocial());
        advogadoJuridico.setComplemento(dto.getComplemento());

        return advogadoJuridico;
    }


    public static UsuarioJuridicoAtualizacaoDTO toResponseAdvogadoDto(AdvogadoJuridico advogadoJuridico) {
        if (advogadoJuridico == null) {
            return null;
        }
        UsuarioJuridicoAtualizacaoDTO dto = new UsuarioJuridicoAtualizacaoDTO();
        dto.setId(advogadoJuridico.getId());
        dto.setEmail(advogadoJuridico.getEmail());
        dto.setSenha(advogadoJuridico.getSenha());
        dto.setCep(advogadoJuridico.getCep());
        dto.setLogradouro(advogadoJuridico.getLogradouro());
        dto.setBairro(advogadoJuridico.getBairro());
        dto.setCidade(advogadoJuridico.getCidade());
        dto.setTelefone(advogadoJuridico.getTelefone());
        dto.setFoto(advogadoJuridico.getFoto());
        dto.setCnpj(advogadoJuridico.getCnpj());
        dto.setNomeFantasia(advogadoJuridico.getNomeFantasia());
        dto.setRazaoSocial(advogadoJuridico.getRazaoSocial());
        dto.setComplemento(advogadoJuridico.getComplemento());
        return dto;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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
}
