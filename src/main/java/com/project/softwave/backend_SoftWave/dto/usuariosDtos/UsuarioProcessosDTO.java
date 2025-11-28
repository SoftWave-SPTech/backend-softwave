package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import com.project.softwave.backend_SoftWave.dto.ProcessoSimplesDTO;
import com.project.softwave.backend_SoftWave.entity.*;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public class UsuarioProcessosDTO {

    private Integer id;

    private String tipoUsuario;

    private String email;

    private Role role;

    private String telefone;

    private String foto;

    private Boolean ativo;

    private String tokenPrimeiroAcesso;

    private String nome;

    private String cpf;

    private String rg;

    private String cnpj;

    private String nomeFantasia;

    private String razaoSocial;

    private String representante;

    private Integer oab;

    private Boolean status;

    private List<ProcessoSimplesDTO> procesos;

    public UsuarioProcessosDTO() {
    }

    public UsuarioProcessosDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.tipoUsuario = usuario.getTipoUsuario();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
        this.telefone = usuario.getTelefone();
        this.foto = usuario.getFoto();
        this.ativo = usuario.getAtivo();
        this.tokenPrimeiroAcesso = usuario.getTokenPrimeiroAcesso();
        this.status = usuario.getAtivo();



        // Verificar o tipo de usuário e preencher os campos específicos
        if (usuario instanceof UsuarioFisico usuarioFisico) {
            this.nome = usuarioFisico.getNome();
            this.cpf = usuarioFisico.getCpf();
            this.rg = usuarioFisico.getRg();

            if (usuarioFisico instanceof AdvogadoFisico advogadoFisico) {
                this.oab = advogadoFisico.getOab();
            }

        } else if (usuario instanceof UsuarioJuridico usuarioJuridico) {
            this.cnpj = usuarioJuridico.getCnpj();
            this.nomeFantasia = usuarioJuridico.getNomeFantasia();
            this.razaoSocial = usuarioJuridico.getRazaoSocial();
            this.representante = usuarioJuridico.getRepresentante();

            if (usuarioJuridico instanceof AdvogadoJuridico advogadoJuridico) {
                this.oab = advogadoJuridico.getOab();
            }
        }
    }


    public UsuarioProcessosDTO(
            Integer id,
            String tipoUsuario,
            String email,
            Role role,
            String telefone,
            String foto,
            Boolean ativo,
            String tokenPrimeiroAcesso,
            String nome,
            String cpf,
            String rg,
            String cnpj,
            String nomeFantasia,
            String razaoSocial,
            String representante,
            Integer oab,
            List<ProcessoSimplesDTO> procesos
    ) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.email = email;
        this.role = role;
        this.telefone = telefone;
        this.foto = foto;
        this.ativo = ativo;
        this.tokenPrimeiroAcesso = tokenPrimeiroAcesso;
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.representante = representante;
        this.oab = oab;
        this.procesos = procesos;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTokenPrimeiroAcesso() {
        return tokenPrimeiroAcesso;
    }

    public void setTokenPrimeiroAcesso(String tokenPrimeiroAcesso) {
        this.tokenPrimeiroAcesso = tokenPrimeiroAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public Integer getOab() {
        return oab;
    }

    public void setOab(Integer oab) {
        this.oab = oab;
    }

    public List<ProcessoSimplesDTO> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<ProcessoSimplesDTO> procesos) {
        this.procesos = procesos;
    }
}
