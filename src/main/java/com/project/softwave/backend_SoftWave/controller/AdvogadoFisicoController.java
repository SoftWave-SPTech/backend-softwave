package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.dto.AdvogadoFisicoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.service.AdvogadoFisicoService;
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
    @RequestMapping("/advogados-fisicos")
    public class AdvogadoFisicoController {

        @Autowired
        private AdvogadoFisicoService service;

        @Operation(summary = "Cadastro de advogado físico", method = "POST")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
                @ApiResponse(responseCode = "409", description = "Email ou CPF já existe"),
                @ApiResponse(responseCode = "400", description = "Dados inválidos")
        })
        @PostMapping
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<AdvogadoFisicoDTO> cadastrar(@Valid @RequestBody AdvogadoFisicoDTO request) {
            AdvogadoFisico advogado = AdvogadoFisicoDTO.toEntity(request);
            AdvogadoFisico cadastrado = service.cadastrar(advogado);
            return ResponseEntity.status(201).body(AdvogadoFisicoDTO.toResponseDTO(cadastrado));
        }

        @Operation(summary = "Listar todos os advogados físicos cadastrados", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                @ApiResponse(responseCode = "204", description = "Nenhum advogado físico encontrado")
        })
        @GetMapping
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<List<AdvogadoFisicoDTO>> listar() {
            List<AdvogadoFisicoDTO> lista = service.listar().stream()
                    .map(AdvogadoFisicoDTO::toResponseDTO)
                    .toList();
            return ResponseEntity.ok(lista);
        }

        @Operation(summary = "Buscar advogado físico por ID", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Advogado físico não encontrado")
        })
        @GetMapping("/{id}")
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<AdvogadoFisicoDTO> buscarPorId(@PathVariable Integer id) {
            AdvogadoFisico advogado = service.buscarPorId(id);
            return ResponseEntity.ok(AdvogadoFisicoDTO.toResponseDTO(advogado));
        }

        @Operation(summary = "Buscar advogado físico por número da OAB", method = "GET")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Advogado físico não encontrado")
        })
        @GetMapping("/oab/{oab}")
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<AdvogadoFisicoDTO> buscarPorOab(@PathVariable Integer oab) {
            AdvogadoFisico advogado = service.buscarPorOab(oab);
            return ResponseEntity.ok(AdvogadoFisicoDTO.toResponseDTO(advogado));
        }

        @Operation(summary = "Atualizar informações de um advogado físico", method = "PUT")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Advogado físico não encontrado")
        })
        @PutMapping("/{id}")
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<UsuarioFisicoAtualizacaoDTO> atualizar(
                @Valid @RequestBody UsuarioFisicoAtualizacaoDTO request,
                @PathVariable Integer id) {

            AdvogadoFisico advogadoAtualizado = service.atualizar(id, request);

            return ResponseEntity.status(200).body(UsuarioFisicoAtualizacaoDTO.toResponseAdvogadoDto(advogadoAtualizado));
        }

        @Operation(summary = "Excluir advogado físico por ID", method = "DELETE")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Exclusão realizada com sucesso"),
                @ApiResponse(responseCode = "404", description = "Advogado físico não encontrado")
        })
        @DeleteMapping("/{id}")
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<Void> deletar(@PathVariable Integer id) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }


