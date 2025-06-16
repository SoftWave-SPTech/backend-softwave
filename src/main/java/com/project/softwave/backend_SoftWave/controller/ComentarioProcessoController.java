package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ComentarioProcessoDTO;
import com.project.softwave.backend_SoftWave.service.ComentarioProcessoService;
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
@RequestMapping("/comentarios-processos")
public class ComentarioProcessoController {

    @Autowired
    private ComentarioProcessoService comentarioProcessoService;

    @Operation(summary = "Criação de comentário sobre o processo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criação de comentário sobre o processo realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/processo")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ComentarioProcessoDTO> criarComentarioProcesso(@Valid @RequestBody ComentarioProcessoDTO dto) {
        ComentarioProcessoDTO novoComentario = comentarioProcessoService.criarComentarioProcesso(dto);
        return ResponseEntity.status(201).body(novoComentario);
    }

    @Operation(summary = "Criação de comentário sobre o processo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criação de comentário sobre o processo realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/movimentacao")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ComentarioProcessoDTO> criarComentarioMovimentacao(@Valid @RequestBody ComentarioProcessoDTO dto) {
        ComentarioProcessoDTO novoComentario = comentarioProcessoService.criarComentarioUltimaMovimentacao(dto);
        return ResponseEntity.status(201).body(novoComentario);
    }

    @Operation(summary = "Busca por todos os comentários dos processos cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos comentários dos processos realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há comentários dos processos cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ComentarioProcessoDTO>> listarComentarios() {
        List<ComentarioProcessoDTO> comentarios = comentarioProcessoService.listarComentarios();
        return comentarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comentarios);
    }

    @Operation(summary = "Busca por ID de um comentário do processo cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de comentário do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentário do processo não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há comentário do processo cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ComentarioProcessoDTO> buscarComentarioPorId(@Valid @PathVariable Long id) {
        try {
            ComentarioProcessoDTO comentario = comentarioProcessoService.buscarComentarioPorId(id);
            return ResponseEntity.ok(comentario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }


    @Operation(summary = "Atualização dos comentários do processo", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do comentário do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentário do processo não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ComentarioProcessoDTO> atualizarComentario(@Valid @PathVariable Long id, @RequestBody ComentarioProcessoDTO dto) {
        try {
            ComentarioProcessoDTO comentarioAtualizado = comentarioProcessoService.atualizarComentario(id, dto);
            return ResponseEntity.ok(comentarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }


    @Operation(summary = "Exclusão de comentários do processo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de comentário do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentário do processo não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarComentario(@Valid @PathVariable Long id) {
        try {
            comentarioProcessoService.deletarComentario(id);
            return ResponseEntity.status(204).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @Operation(summary = "Listagem de comentarios por id movimentação", method = "DELETE")
    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Exclusão de comentário do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentário do processo não encontrado")
    })
    @GetMapping("/buscar-por-ultima-movimentacao/{ultimaMovimentacaoId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ComentarioProcessoDTO>> listarComentariosPorUltimaMovimentacaoId(@Valid @PathVariable Integer ultimaMovimentacaoId) {
        List<ComentarioProcessoDTO> comentarios = comentarioProcessoService.listarComentariosPorUltimaMovimentacaoId(ultimaMovimentacaoId);
        return comentarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comentarios);
    }

    @Operation(summary = "Listagem de comentarios por id processo", method = "DELETE")
    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Exclusão de comentário do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentário do processo não encontrado")
    })
    @GetMapping("/buscar-por-proceso/{processoId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ComentarioProcessoDTO>> listarComentariosPorProcessoId(@Valid @PathVariable Integer processoId) {
        List<ComentarioProcessoDTO> comentarios = comentarioProcessoService.listarComentariosPorProcessoId(processoId);
        return comentarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(comentarios);
    }
}
