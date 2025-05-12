package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridicoDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.service.AdvogadoJuridicoService;
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
@RequestMapping("/advogados-juridicos")
public class AdvogadoJuridicoController {

    @Autowired
    private AdvogadoJuridicoService service;

    @Operation(summary = "Cadastro de advogado jurídico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Advogado já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> cadastrar(@Valid @RequestBody AdvogadoJuridicoDTO request) {
        AdvogadoJuridico advogado = AdvogadoJuridicoDTO.toEntity(request);
        AdvogadoJuridico salvo = service.cadastrar(advogado);
        return ResponseEntity.status(201).body(AdvogadoJuridicoDTO.toResponseDTO(salvo));
    }

    @Operation(summary = "Listar todos os advogados jurídicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum advogado jurídico encontrado")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AdvogadoJuridicoDTO>> listar() {
        List<AdvogadoJuridico> advogados = service.listar();
        List<AdvogadoJuridicoDTO> dtos = advogados.stream()
                .map(AdvogadoJuridicoDTO::toResponseDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Buscar advogado jurídico por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Advogado jurídico não encontrado")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> buscarPorId(@PathVariable Integer id) {
        AdvogadoJuridico advogado = service.buscarPorId(id);
        return ResponseEntity.ok(AdvogadoJuridicoDTO.toResponseDTO(advogado));
    }

    @Operation(summary = "Buscar advogado jurídico por OAB", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Advogado jurídico não encontrado")
    })
    @GetMapping("/oab/{oab}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> buscarPorOab(@PathVariable Integer oab) {
        AdvogadoJuridico advogado = service.buscarPorOab(oab);
        return ResponseEntity.ok(AdvogadoJuridicoDTO.toResponseDTO(advogado));
    }

    @Operation(summary = "Atualizar advogado jurídico", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Advogado jurídico não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioJuridicoAtualizacaoDTO> atualizar(
            @Valid @RequestBody UsuarioJuridicoAtualizacaoDTO request,
            @PathVariable Integer id) {

        AdvogadoJuridico advogadoAtualizado = service.atualizar(id, request);

        return ResponseEntity.status(200).body(UsuarioJuridicoAtualizacaoDTO.toResponseAdvogadoDto(advogadoAtualizado));
    }

    @Operation(summary = "Deletar advogado jurídico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Advogado jurídico não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}