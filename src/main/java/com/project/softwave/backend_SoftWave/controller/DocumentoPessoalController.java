package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.DocumentoPessoalDTO;
import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import com.project.softwave.backend_SoftWave.service.DocumentoPessoalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos-pessoais")
public class DocumentoPessoalController {

    @Autowired
    private DocumentoPessoalService service;

    @Operation(summary = "Upload de documentos pessoais", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Upload de documento pessoal realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Documento pessoal já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DocumentoPessoalDTO> cadastrarDocumento(@Valid @RequestBody DocumentoPessoalDTO request) {
        DocumentoPessoal documento = DocumentoPessoalDTO.toEntity(request);
        DocumentoPessoal documentoSalvo = service.cadastrarDocumento(documento);
        return ResponseEntity.status(201).body(DocumentoPessoalDTO.toResponseDto(documentoSalvo));
    }

    @Operation(summary = "Busca por todos os documentos pessoais cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos documentos pessoais realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há documentos pessoais cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<DocumentoPessoalDTO>> listarDocumentos() {
        List<DocumentoPessoal> documentos = service.listarDocumentos();
        List<DocumentoPessoalDTO> documentoDtos = documentos.stream()
                .map(DocumentoPessoalDTO::toResponseDto)
                .toList();
        return ResponseEntity.status(200).body(documentoDtos);
    }

    @Operation(summary = "Busca por ID de um documento pessoal cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de documento pessoal realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento pessoal não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há documento pessoal cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DocumentoPessoalDTO> buscarPorId(@PathVariable Integer id) {
        DocumentoPessoal documento = service.buscarPorId(id);
        return ResponseEntity.status(200).body(DocumentoPessoalDTO.toResponseDto(documento));
    }

    @Operation(summary = "Atualização das informações dos documentos pessoais", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do documento pessoal realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento pessoal não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DocumentoPessoalDTO> atualizarDocumento(@Valid @RequestBody DocumentoPessoal documento, @PathVariable Integer id) {
        DocumentoPessoal documentoAtualizado = service.atualizarDocumento(documento, id);
        return ResponseEntity.status(200).body(DocumentoPessoalDTO.toResponseDto(documentoAtualizado));
    }

    @Operation(summary = "Exclusão de documentos pessoais", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de documento pessoal realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento pessoal não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarDocumento(@PathVariable Integer id) {
        service.deletarDocumento(id);
        return ResponseEntity.status(204).build();
    }
}

