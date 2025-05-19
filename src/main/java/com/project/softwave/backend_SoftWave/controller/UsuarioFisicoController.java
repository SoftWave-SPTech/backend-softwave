package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
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

    @Operation(summary = "Cadastro dos usuários físicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro do usuário físico realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email ou CNPJ já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioFisicoDTO> cadastrar(@Valid @RequestBody UsuarioFisicoDTO request){
        UsuarioFisico usuarioFisico = UsuarioFisicoDTO.toEntity(request);
        UsuarioFisico usuarioNovo = usuarioFisicoService.cadastrar(usuarioFisico);

        return ResponseEntity.status(201).body(UsuarioFisicoDTO.toResponseDto(usuarioNovo));
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
            return  ResponseEntity.status(200).build();
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
    public ResponseEntity<List<UsuarioFisicoDTO>> listar(){
        List<UsuarioFisico> usuariosFisicos = usuarioFisicoService.listar();

        List<UsuarioFisicoDTO> usuarioFisicoDtos = usuariosFisicos.stream()
                .map(UsuarioFisicoDTO::toResponseDto)
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
    public ResponseEntity<UsuarioFisicoDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioFisico usuario = usuarioFisicoService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioFisicoDTO.toResponseDto(usuario));
    }

    @Operation(summary = "Atualizar a foto de perfil dos usuários", method = "PUT")
    @PutMapping("foto-perfil/")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> atualizarFotoPerfil(
            @Valid @RequestBody UsuarioFotoPerfilDTO usuarioFotoPerfilDTO
    ) throws IOException {

        String fotoPerfilUrl = usuarioService.atualizarFotoPerfil(usuarioFotoPerfilDTO);

        return ResponseEntity.status(200).body(fotoPerfilUrl);
    }

    @Operation(summary = "Deletar a foto de perfil dos usuários", method = "DELETE")
    @DeleteMapping("foto-perfil/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> deletarFotoPerfil(
            @PathVariable Integer id
    ) throws IOException {

        usuarioService.deletarFotoPerfil(id);

        return ResponseEntity.status(200).build();
    }
}
