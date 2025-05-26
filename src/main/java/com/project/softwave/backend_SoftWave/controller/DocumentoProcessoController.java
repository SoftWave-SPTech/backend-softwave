package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.DocumentoProcessoCadastroDto;
import com.project.softwave.backend_SoftWave.dto.DocumentoProcessoDto;
import com.project.softwave.backend_SoftWave.entity.DocumentosProcesso;
import com.project.softwave.backend_SoftWave.service.DocumentoProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("documentos-processos")
public class DocumentoProcessoController {

    @Autowired
    private DocumentoProcessoService service;

    @Operation(summary = "Upload de documentos do processo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Upload de documento do processo realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Documento do processo já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> cadastrar(@Valid @RequestBody DocumentoProcessoCadastroDto request) throws IOException {
        String documentoSalvo = service.cadastrarDocumento(request);
        return ResponseEntity.status(201).body(documentoSalvo);
    }

    @Operation(summary = "Busca por todos os documentos do processo cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos documentos do processo realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há documentos do processo cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<DocumentoProcessoDto>> listar(){

        List<DocumentosProcesso> documentos = service.listar();
        List<DocumentoProcessoDto> documentosDtos = documentos.stream()
                .map(DocumentoProcessoDto::toResponseDto).toList();

        return ResponseEntity.status(200).body(documentosDtos);
    }

    @Operation(summary = "Busca por ID de um documento do processo cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de documento do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento do processo não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há documento do processo cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DocumentoProcessoDto> buscarPorID(@PathVariable Integer id){
        DocumentosProcesso documento = service.buscarPorId(id);

        return ResponseEntity.status(200).body(DocumentoProcessoDto.toResponseDto(documento));
    }

    @Operation(summary = "Exclusão de documentos do processo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de documento do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Documento do processo não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarDocumento(@PathVariable Integer id) throws IOException {
        service.deletarDocumento(id);
        return ResponseEntity.status(204).build();
    }
}
