package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoResponseDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.service.AdvogadoJuridicoService;
import com.project.softwave.backend_SoftWave.service.FotoPerfilService;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoRequestDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/advogados-juridicos")
public class AdvogadoJuridicoController {

    @Autowired
    private AdvogadoJuridicoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FotoPerfilService fotoPerfilService;

    @Operation(summary = "Cadastro de advogado jurídico", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Advogado já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoResponseDTO> cadastrar(@Valid @RequestBody AdvogadoJuridicoRequestDTO request) {
        AdvogadoJuridico advogado = AdvogadoJuridicoRequestDTO.toEntity(request);
        AdvogadoJuridico salvo = service.cadastrar(advogado);
        return ResponseEntity.status(201).body(AdvogadoJuridicoResponseDTO.toResponseDto(salvo));
    }

    @Operation(summary = "Listar todos os advogados jurídicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum advogado jurídico encontrado")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AdvogadoJuridicoResponseDTO>> listar() {
        List<AdvogadoJuridico> advogados = service.listar();
        List<AdvogadoJuridicoResponseDTO> dtos = advogados.stream()
                .map(AdvogadoJuridicoResponseDTO::toResponseDto)
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
    public ResponseEntity<AdvogadoJuridicoResponseDTO> buscarPorId(@PathVariable Integer id) {
        AdvogadoJuridico advogado = service.buscarPorId(id);
        return ResponseEntity.ok(AdvogadoJuridicoResponseDTO.toResponseDto(advogado));
    }

    @Operation(summary = "Buscar advogado jurídico por OAB", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Advogado jurídico não encontrado")
    })
    @GetMapping("/oab/{oab}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AdvogadoJuridicoResponseDTO> buscarPorOab(@PathVariable Integer oab) {
        AdvogadoJuridico advogado = service.buscarPorOab(oab);
        return ResponseEntity.ok(AdvogadoJuridicoResponseDTO.toResponseDto(advogado));
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

    @Operation(summary = "Atualizar a foto de perfil dos usuários", method = "PUT")
    @PutMapping("foto-perfil/")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> atualizarFotoPerfil(
            @Valid @RequestBody UsuarioFotoPerfilDTO usuarioFotoPerfilDTO
    ) throws IOException {

        String fotoPerfilUrl = fotoPerfilService.atualizarFotoPerfil(usuarioFotoPerfilDTO);

        return ResponseEntity.status(200).body(fotoPerfilUrl);
    }

    @Operation(summary = "Deletar a foto de perfil dos usuários", method = "DELETE")
    @DeleteMapping("foto-perfil/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> deletarFotoPerfil(
            @PathVariable Integer id
    ) throws IOException {

        fotoPerfilService.deletarFotoPerfil(id);

        return ResponseEntity.status(200).build();
    }
}