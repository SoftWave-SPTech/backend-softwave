package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoRequestDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoResponseDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.service.FotoPerfilService;
import com.project.softwave.backend_SoftWave.service.UsuarioFisicoService;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
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
@RequestMapping("/usuarios-fisicos")
public class UsuarioFisicoController {

    @Autowired
    private UsuarioFisicoService usuarioFisicoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FotoPerfilService fotoPerfilService;

    @Operation(summary = "Cadastro dos usuários físicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro do usuário físico realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email ou CNPJ já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioFisicoResponseDTO> cadastrar(@Valid @RequestBody UsuarioFisicoRequestDTO request){
        UsuarioFisico usuarioFisico = UsuarioFisicoRequestDTO.toEntity(request);
        UsuarioFisico usuarioNovo = usuarioFisicoService.cadastrar(usuarioFisico);
        return ResponseEntity.status(201).body(UsuarioFisicoResponseDTO.toResponseDto(usuarioNovo));
    }

    @Operation(summary = "Exclusão de um usuário físico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão do usuário físico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário físico não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Integer id){

        if(usuarioFisicoService.deletar(id)){
            return  ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualização dos dados dos usuários físicos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do usuário físico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário físico não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioFisicoAtualizacaoDTO> atualizar(
          @Valid @PathVariable Integer id,
          @RequestBody UsuarioFisicoAtualizacaoDTO request
    ){
        UsuarioFisico usuarioAtualizado = usuarioFisicoService.atualizar(id, request);

        return ResponseEntity.status(200).body(UsuarioFisicoAtualizacaoDTO.toResponseDto(usuarioAtualizado));
    }

    @Operation(summary = "Busca por todos dos usuários físicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos usuários físicos realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários físicos cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioFisicoResponseDTO>> listar(){
        List<UsuarioFisico> usuariosFisicos = usuarioFisicoService.listar();

        List<UsuarioFisicoResponseDTO> usuarioFisicoDtos = usuariosFisicos.stream()
                .map(UsuarioFisicoResponseDTO::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(usuarioFisicoDtos);
    }

    @Operation(summary = "Buscar usuário físico por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário físico não encontrado")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioFisicoResponseDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioFisico usuario = usuarioFisicoService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioFisicoResponseDTO.toResponseDto(usuario));
    }

}
