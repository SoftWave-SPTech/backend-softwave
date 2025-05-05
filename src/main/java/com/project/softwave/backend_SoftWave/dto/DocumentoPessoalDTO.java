package com.project.softwave.backend_SoftWave.dto;

import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DocumentoPessoalDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String documento;

    @NotBlank
    private String conteudo;

    @NotNull
    private Integer fkCliente;

    public static DocumentoPessoal toEntity(DocumentoPessoalDTO dto) {
        if (dto == null) {
            return null;
        }
        DocumentoPessoal documento = new DocumentoPessoal();
        documento.setDocumento(dto.getDocumento());
        documento.setConteudo(dto.getConteudo());
//        documento.setFkCliente(dto.getFkCliente());
        return documento;
    }

    public static DocumentoPessoalDTO toResponseDto(DocumentoPessoal documento) {
        if (documento == null) {
            return null;
        }
        DocumentoPessoalDTO dto = new DocumentoPessoalDTO();
        dto.setId(documento.getId());
        dto.setDocumento(documento.getDocumento());
        dto.setConteudo(documento.getConteudo());
//        dto.setFkCliente(documento.getFkCliente());
        return dto;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }
}
