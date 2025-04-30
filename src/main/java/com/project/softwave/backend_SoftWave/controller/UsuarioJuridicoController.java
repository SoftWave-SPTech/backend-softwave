package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.service.UsuarioJuridicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios-juridicos")
public class UsuarioJuridicoController {

    @Autowired
    private UsuarioJuridicoService usuarioJuridicoService;


    @Operation(summary = "Cadastro dos usuários jurídicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro do usuário jurídico realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email ou CNPJ já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioJuridicoDTO> cadastrar(@Valid @RequestBody UsuarioJuridicoDTO request){
        UsuarioJuridico usuarioJuridico = UsuarioJuridicoDTO.toEntity(request);
        UsuarioJuridico usuarioNovo = usuarioJuridicoService.cadastrar(usuarioJuridico);
        return ResponseEntity.status(201).body(UsuarioJuridicoDTO.toResponseDto(usuarioNovo));
    }


    @Operation(summary = "Exclusão de um usuário jurídico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão do usuário jurídico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário Jurídico não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Integer id){

        if(usuarioJuridicoService.deletar(id)){
            return  ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualização dos dados dos usuários jurídicos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do usuário jurídico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário Jurídico não encontrado")
    })

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioJuridicoAtualizacaoDTO> atualizar(
            @Valid @RequestBody UsuarioJuridicoAtualizacaoDTO request,
            @PathVariable Integer id) {
        UsuarioJuridico usuarioAtualizado = usuarioJuridicoService.atualizar(id, request);

        return ResponseEntity.status(200).body(UsuarioJuridicoAtualizacaoDTO.toResponseDto(usuarioAtualizado));
    }

    @Operation(summary = "Busca por todos dos usuários jurídicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos usuários jurídicos realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários jurídicos cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioJuridicoDTO>> listar(){
        List<UsuarioJuridico> usuariosJuridicos = usuarioJuridicoService.listar();

        List<UsuarioJuridicoDTO> usuarioJuridicoDtos = usuariosJuridicos.stream()
                .map(UsuarioJuridicoDTO::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(usuarioJuridicoDtos);
    }

    @Operation(summary = "Buscar usuário jurídico por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário jurídico não encontrado")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioJuridicoDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioJuridico usuario = usuarioJuridicoService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioJuridicoDTO.toResponseDto(usuario));
    }

}
