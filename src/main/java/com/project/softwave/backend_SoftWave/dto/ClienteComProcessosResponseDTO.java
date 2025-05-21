package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.Usuario;

import java.util.List;

public class ClienteComProcessosResponseDTO {

    private Integer id;
    private String nome;
    private String nomeFantasia;
    private String email;
    private String telefone;
    private String tipoUsuario;
    private List<ProcessoSimplesDTO> processos;

    public ClienteComProcessosResponseDTO(Integer id, String nome,String nomeFantasia, String email, String telefone, String tipoUsuario, List<ProcessoSimplesDTO> list) {
        this.id = id;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.email = email;
        this.telefone = telefone;
        this.tipoUsuario = tipoUsuario;
        this.processos = list;
    }

    public static ClienteComProcessosResponseDTO toClienteComProcessosResponseDTO(Usuario usuario) {
        return new ClienteComProcessosResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getNomeFantasia(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getTipoUsuario(),
                usuario.getProcessos().stream()
                        .map(p -> new ProcessoSimplesDTO(p.getId(), p.getNumeroProcesso()))
                        .toList()
        );
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<ProcessoSimplesDTO> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoSimplesDTO> processos) {
        this.processos = processos;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}