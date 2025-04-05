package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ComentarioProcessoDTO;
import com.project.softwave.backend_SoftWave.service.ComentarioProcessoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentariosProcessos")
public class ComentarioProcessoController {

    @Autowired
    private ComentarioProcessoService comentarioProcessoService;

    @PostMapping
    public ResponseEntity<ComentarioProcessoDTO> criarComentario(@Valid @RequestBody ComentarioProcessoDTO dto) {
        ComentarioProcessoDTO novoComentario = comentarioProcessoService.criarComentario(dto);
        return ResponseEntity.status(201).body(novoComentario);
    }

    @GetMapping
    public ResponseEntity<List<ComentarioProcessoDTO>> listarComentarios() {
        List<ComentarioProcessoDTO> comentarios = comentarioProcessoService.listarComentarios();
        return comentarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComentarioProcessoDTO> buscarComentarioPorId(@Valid @PathVariable Long id) {
        try {
            ComentarioProcessoDTO comentario = comentarioProcessoService.buscarComentarioPorId(id);
            return ResponseEntity.ok(comentario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioProcessoDTO> atualizarComentario(@Valid @PathVariable Long id, @RequestBody ComentarioProcessoDTO dto) {
        try {
            ComentarioProcessoDTO comentarioAtualizado = comentarioProcessoService.atualizarComentario(id, dto);
            return ResponseEntity.ok(comentarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@Valid @PathVariable Long id) {
        try {
            comentarioProcessoService.deletarComentario(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
