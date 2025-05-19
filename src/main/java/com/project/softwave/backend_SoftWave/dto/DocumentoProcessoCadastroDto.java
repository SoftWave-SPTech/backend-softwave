package com.project.softwave.backend_SoftWave.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoProcessoCadastroDto {

    private String nomeArquivo;
    private MultipartFile documentoProcesso;

    public DocumentoProcessoCadastroDto() {
    }

    public DocumentoProcessoCadastroDto(String nomeArquivo, MultipartFile documentoProcesso) {
        this.nomeArquivo = nomeArquivo;
        this.documentoProcesso = documentoProcesso;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public MultipartFile getDocumentoProcesso() {
        return documentoProcesso;
    }

    public void setDocumentoProcesso(MultipartFile documentoProcesso) {
        this.documentoProcesso = documentoProcesso;
    }
}
