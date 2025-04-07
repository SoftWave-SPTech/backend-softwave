package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
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
@RequestMapping("/usuariosFisicos")
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
    public ResponseEntity<UsuarioFisicoDTO> cadastrar(@Valid @RequestBody UsuarioFisicoDTO usuarioFisicoDTO){

        UsuarioFisicoDTO usuarioNovo = usuarioFisicoService.cadastrar(usuarioFisicoDTO);

        if(usuarioNovo != null){
            return ResponseEntity.status(201).body(usuarioNovo);
        }
        return ResponseEntity.status(409).build();
    }

    @Operation(summary = "Login dos usuários físicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login do usuário físico realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Email e/ou senha inválida")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioFisicoDTO> login(@Valid @RequestBody UsuarioFisicoDTO usuarioFisicoDTO){
        UsuarioFisicoDTO usuarioAutenticado = usuarioFisicoService.login(usuarioFisicoDTO);
        if(usuarioAutenticado != null){
            return ResponseEntity.status(200).body(usuarioAutenticado);
        }
        return ResponseEntity.status(409).build();
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
    public ResponseEntity<UsuarioFisicoDTO> atualizar(
          @Valid  @PathVariable Integer id,
            @RequestBody UsuarioFisicoDTO usuarioFisicoDTO
    ){
        UsuarioFisicoDTO usuarioAtualizado = usuarioFisicoService.atualizar(id,usuarioFisicoDTO);

        if(usuarioAtualizado != null){
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(409).build();
    }

    @Operation(summary = "Busca por todos dos usuários físicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos usuários físicos realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários físicos cadastrados")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioFisicoDTO>> listar(){
        List<UsuarioFisicoDTO> usuarios = usuarioFisicoService.listar();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);

    }
}
