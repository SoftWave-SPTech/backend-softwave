package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.DocumentosProcesso;
import jakarta.validation.constraints.NotBlank;

public class DocumentoProcessoDto {

    private Integer id;
    @NotBlank
    private String nomeArquivo;
    @NotBlank
    private String urlArquivo;
    private String s3Key;


    public DocumentoProcessoDto() {
    }

    public DocumentoProcessoDto(Integer id, String nomeArquivo, String urlArquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public static DocumentosProcesso toEntity(DocumentoProcessoDto dto) {
        if (dto == null) {
            return null;
        }

        DocumentosProcesso documento = new DocumentosProcesso();
        documento.setNomeArquivo(dto.getNomeArquivo());
        documento.setUrlArquivo(dto.getUrlArquivo());
//        documento.setFkCliente(dto.getFkCliente());
        return documento;
    }

    public static DocumentoProcessoDto toResponseDto(DocumentosProcesso documento) {
        if (documento == null) {
            return null;
        }
        DocumentoProcessoDto dto = new DocumentoProcessoDto();
        dto.setId(documento.getId());
        dto.setNomeArquivo(documento.getNomeArquivo());
        dto.setUrlArquivo(documento.getUrlArquivo());
        dto.setS3Key(documento.getS3Key());
//        dto.setFkCliente(documento.getFkCliente());
        return dto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public String getS3Key() {
        return s3Key;
    }

    public void setS3Key(String s3Key) {
        this.s3Key = s3Key;
    }
}
