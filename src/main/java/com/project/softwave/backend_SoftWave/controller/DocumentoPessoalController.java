package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.DocumentoPessoalDTO;
import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;
import com.project.softwave.backend_SoftWave.service.DocumentoPessoalService;
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

    @PostMapping
    public ResponseEntity<DocumentoPessoalDTO> cadastrarDocumento(@Valid @RequestBody DocumentoPessoalDTO request) {
        DocumentoPessoal documento = DocumentoPessoalDTO.toEntity(request);
        DocumentoPessoal documentoSalvo = service.cadastrarDocumento(documento);
        return ResponseEntity.status(201).body(DocumentoPessoalDTO.toResponseDto(documentoSalvo));
    }

    @GetMapping
    public ResponseEntity<List<DocumentoPessoalDTO>> listarDocumentos() {
        List<DocumentoPessoal> documentos = service.listarDocumentos();
        List<DocumentoPessoalDTO> documentoDtos = documentos.stream()
                .map(DocumentoPessoalDTO::toResponseDto)
                .toList();
        return ResponseEntity.status(200).body(documentoDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoPessoalDTO> buscarPorId(@PathVariable Integer id) {
        DocumentoPessoal documento = service.buscarPorId(id);
        return ResponseEntity.status(200).body(DocumentoPessoalDTO.toResponseDto(documento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoPessoalDTO> atualizarDocumento(@Valid @RequestBody DocumentoPessoal documento, @PathVariable Integer id) {
        DocumentoPessoal documentoAtualizado = service.atualizarDocumento(documento, id);
        return ResponseEntity.status(200).body(DocumentoPessoalDTO.toResponseDto(documentoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDocumento(@PathVariable Integer id) {
        service.deletarDocumento(id);
        return ResponseEntity.status(204).build();
    }
}

