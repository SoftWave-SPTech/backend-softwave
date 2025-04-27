package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
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
@RequestMapping("/usuariosJuridicos")
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

        if(usuarioNovo != null){
            return ResponseEntity.status(201).body(new UsuarioJuridicoDTO(usuarioNovo));
        }
          return ResponseEntity.status(409).build();
    }

    @Operation(summary = "Login dos usuários jurídicos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login do usuário jurídico realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Email e/ou senha inválida")
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioJuridicoDTO> login(@Valid @RequestBody UsuarioJuridicoDTO request){
        UsuarioJuridicoDTO usuarioAutenticado = usuarioJuridicoService.login(request);
        if(usuarioAutenticado != null){
            return ResponseEntity.status(200).body(usuarioAutenticado);
        }
        return ResponseEntity.status(409).build();
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

//    Com RequestParam ao invés de PathVariable
//    @DeleteMapping("/id")
//    public ResponseEntity deletar(@RequestParam Integer id){
//        if(usuarioJuridicoService.deletar(id)){
//            return  ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @Operation(summary = "Atualização dos dados dos usuários jurídicos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do usuário jurídico realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário Jurídico não encontrado")
    })

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UsuarioJuridicoDTO> atualizar(
           @Valid @PathVariable Integer id,
           @Valid @RequestBody UsuarioJuridicoDTO usuarioJuridicoDTO
    ){
        UsuarioJuridicoDTO usuarioAtualizado = usuarioJuridicoService.atualizar(id,usuarioJuridicoDTO);

        if(usuarioAtualizado != null){
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }


    @Operation(summary = "Busca por todos dos usuários jurídicos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos usuários jurídicos realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há usuários jurídicos cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UsuarioJuridicoDTO>> listar(){
        List<UsuarioJuridicoDTO> usuarios = usuarioJuridicoService.listar();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);

    }

}
