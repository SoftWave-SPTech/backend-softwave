package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import com.project.softwave.backend_SoftWave.dto.DocumentoPessoalDTO;

import java.util.List;

public class UsuarioDocumentosDTO {
    private Integer id;
    private String nome;
    private String foto;
    private String nomeFantasia; // ou private String razaoSocial;
    private Boolean ativo;
    private String telefone;
    private String email;
    private List<DocumentoPessoalDTO> documentos;

    public UsuarioDocumentosDTO() {
    }

    public UsuarioDocumentosDTO(
            Integer id,
            String nome,
            String nomeFantasia,
            Boolean ativo,
            String telefone,
            String email,
            String foto,
            List<DocumentoPessoalDTO> documentos
    ) {
        this.id = id;
        this.nome = nome;
        this.nomeFantasia = nomeFantasia;
        this.ativo = ativo;
        this.telefone = telefone;
        this.email = email;
        this.foto = foto;
        this.documentos = documentos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DocumentoPessoalDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoPessoalDTO> documentos) {
        this.documentos = documentos;
    }
}
