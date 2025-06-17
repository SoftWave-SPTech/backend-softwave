package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentoPessoalDTO {

    private Integer id;

    @NotBlank
    private String nomeArquivo;

    @NotBlank
    private String urlArquivo;

    @NotNull
    private Integer fkCliente;

    public static DocumentoPessoal toEntity(DocumentoPessoalDTO dto) {
        if (dto == null) {
            return null;
        }
        DocumentoPessoal documento = new DocumentoPessoal();
        documento.setNomeArquivo(dto.getNomeArquivo());
        documento.setUrlArquivo(dto.getUrlArquivo());
//        documento.setFkCliente(dto.getFkCliente());
        return documento;
    }

    public DocumentoPessoalDTO(Integer id, String nomeArquivo, String urlArquivo, Integer fkCliente) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
        this.fkCliente = fkCliente;
    }

    public DocumentoPessoalDTO(Integer id, String nomeArquivo, String urlArquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.urlArquivo = urlArquivo;
    }

    public DocumentoPessoalDTO() {
    }

    public static DocumentoPessoalDTO toResponseDto(DocumentoPessoal documento) {
        if (documento == null) {
            return null;
        }
        DocumentoPessoalDTO dto = new DocumentoPessoalDTO();
        dto.setId(documento.getId());
        dto.setNomeArquivo(documento.getNomeArquivo());
        dto.setUrlArquivo(documento.getUrlArquivo());
//        dto.setFkCliente(documento.getFkCliente());
        return dto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank String getUrlArquivo() {
        return urlArquivo;
    }

    public void setUrlArquivo(@NotBlank String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public @NotBlank String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(@NotBlank String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }


    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }
}
