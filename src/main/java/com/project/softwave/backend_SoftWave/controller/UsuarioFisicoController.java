package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.service.UsuarioFisicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios-fisicos")
public class UsuarioFisicoController {

    @Autowired
    private UsuarioFisicoService usuarioFisicoService;

    @Operation(summary = "Cadastro dos usuários físicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro do usuário físico realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email ou CNPJ já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioFisicoDTO> cadastrar(@Valid @RequestBody UsuarioFisicoDTO request){
        UsuarioFisico usuarioFisico = UsuarioFisicoDTO.toEntity(request);
        UsuarioFisico usuarioNovo = usuarioFisicoService.cadastrar(usuarioFisico);

        return ResponseEntity.status(201).body(UsuarioFisicoDTO.toResponseDto(usuarioNovo));
    }



    @Operation(summary = "Login dos usuários físicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login do usuário físico realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Email e/ou senha inválida")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioFisicoDTO> login(@Valid @RequestBody UsuarioLoginDTO loginDTO) {
        UsuarioFisico login = new UsuarioFisico();
        login.setEmail(loginDTO.getEmail());
        login.setSenha(loginDTO.getSenha());

        UsuarioFisico usuarioAutenticado = usuarioFisicoService.login(login);

        UsuarioFisicoDTO response = UsuarioFisicoDTO.toResponseDto(usuarioAutenticado);

        return ResponseEntity.ok(response);
    }




    @Operation(summary = "Exclusão de um usuário físico", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão do usuário físico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário físico não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@Valid @PathVariable Integer id){

        if(usuarioFisicoService.deletar(id)){
            return  ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // Com RequestParam ao invés de PathVariable
//    @DeleteMapping("/id")
//    public ResponseEntity deletar(@RequestParam Integer id){
//
//        if(usuarioFisicoService.deletar(id)){
//            return  ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @Operation(summary = "Atualização dos dados dos usuários físicos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do usuário físico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário físico não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioFisicoAtualizacaoDTO> atualizar(
            @Valid @RequestBody UsuarioFisicoAtualizacaoDTO request,
            @PathVariable Integer id) {

        UsuarioFisico usuarioAtualizado = usuarioFisicoService.atualizar(id, request);

        return ResponseEntity.status(200).body(UsuarioFisicoAtualizacaoDTO.toResponseDto(usuarioAtualizado));
    }


    @Operation(summary = "Busca por todos dos usuários físicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos usuários físicos realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários físicos cadastrados")
    })
    @GetMapping
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
    public ResponseEntity<UsuarioFisicoDTO> buscarPorId(@PathVariable Integer id) {
        UsuarioFisico usuario = usuarioFisicoService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioFisicoDTO.toResponseDto(usuario));
    }
}
