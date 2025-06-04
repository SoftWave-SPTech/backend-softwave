package com.project.softwave.backend_SoftWave.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoPessoalCadastroDto {
    private String nomeArquivo;
    private MultipartFile documentoPessoal;
    private Integer idUsuario;

    public DocumentoPessoalCadastroDto() {
    }

    public DocumentoPessoalCadastroDto(String nomeArquivo, MultipartFile documentoPessoal) {
        this.nomeArquivo = nomeArquivo;
        this.documentoPessoal = documentoPessoal;
    }

    public DocumentoPessoalCadastroDto(String nomeArquivo, MultipartFile documentoPessoal, Integer idUsuario) {
        this.nomeArquivo = nomeArquivo;
        this.documentoPessoal = documentoPessoal;
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public MultipartFile getDocumentoPessoal() {
        return documentoPessoal;
    }

    public void setDocumentoPessoal(MultipartFile documentoPessoal) {
        this.documentoPessoal = documentoPessoal;
    }
}
