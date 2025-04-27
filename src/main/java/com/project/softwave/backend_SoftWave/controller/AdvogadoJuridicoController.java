package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridicoDTO;
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
@RequestMapping("/advogadosJuridicos")
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
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> buscarPorId(@PathVariable Integer id) {
        AdvogadoJuridico advogado = service.buscarPorId(id);
        return ResponseEntity.ok(AdvogadoJuridicoDTO.toResponseDTO(advogado));
    }

    @Operation(summary = "Buscar advogado jurídico por OAB", method = "GET")
    @GetMapping("/oab/{oab}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> buscarPorOab(@PathVariable Integer oab) {
        AdvogadoJuridico advogado = service.buscarPorOab(oab);
        return ResponseEntity.ok(AdvogadoJuridicoDTO.toResponseDTO(advogado));
    }

    @Operation(summary = "Atualizar advogado jurídico", method = "PUT")
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody AdvogadoJuridicoDTO request) {
        AdvogadoJuridico advogado = AdvogadoJuridicoDTO.toEntity(request);
        AdvogadoJuridico atualizado = service.atualizar(id, advogado);
        return ResponseEntity.ok(AdvogadoJuridicoDTO.toResponseDTO(atualizado));
    }

    @Operation(summary = "Deletar advogado jurídico", method = "DELETE")
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}