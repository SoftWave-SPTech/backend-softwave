package com.project.softwave.backend_SoftWave.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoPessoalCadastroDto {
    private String nomeArquivo;
    private MultipartFile documentoPessoal;

    public DocumentoPessoalCadastroDto() {
    }

    public DocumentoPessoalCadastroDto(String nomeArquivo, MultipartFile documentoPessoal) {
        this.nomeArquivo = nomeArquivo;
        this.documentoPessoal = documentoPessoal;
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
