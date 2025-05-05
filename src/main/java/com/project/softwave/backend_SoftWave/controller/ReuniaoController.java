package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.dto.ReuniaoDTO;
import com.project.softwave.backend_SoftWave.entity.Reuniao;
import com.project.softwave.backend_SoftWave.service.ReuniaoService;
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
@RequestMapping("/reunioes")
public class ReuniaoController {


    @Autowired
    private ReuniaoService reuniaoService;

    @Operation(summary = "Cadastro de reuniões", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro da reunião realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Reunião já cadastrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ReuniaoDTO> cadastrarReuniao(@Valid @RequestBody ReuniaoDTO request) {
        Reuniao reuniao = ReuniaoDTO.toEntity(request);
        Reuniao reuniaoResponse = reuniaoService.cadastrarReuniao(reuniao);
        return ResponseEntity.status(201).body(ReuniaoDTO.toResponseDto(reuniaoResponse));

    }

    @Operation(summary = "Busca por todas as reuniões cadastradas pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca das reuniões realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há reuniões cadastradas")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ReuniaoDTO>> listarReuniao() {
        List<Reuniao> reunioes = reuniaoService.listarReuniao();

        List<ReuniaoDTO> reuniaoDtos = reunioes.stream()
                .map(ReuniaoDTO::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(reuniaoDtos);

    }


    @Operation(summary = "Busca por ID de uma reunião cadastrada pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID da reunião realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reunião não encontrada"),
            @ApiResponse(responseCode = "204", description = "Não há reunião cadastrada com esse ID")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ReuniaoDTO> buscarPorId(@PathVariable Integer id) {
        Reuniao reuniaoResponse = reuniaoService.buscarPorId(id);
        return ResponseEntity.status(200).body(ReuniaoDTO.toResponseDto(reuniaoResponse));
    }

    @Operation(summary = "Atualização das informações das reuniões", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização da reunião realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reunião não encontrada")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<ReuniaoDTO> atualizarReuniao(@Valid @RequestBody Reuniao reuniao, @PathVariable Integer id ) {

        Reuniao reuniaoResponse = reuniaoService.atualizarReuniao(reuniao, id);
        return ResponseEntity.status(200).body(ReuniaoDTO.toResponseDto(reuniaoResponse));

    }

    @Operation(summary = "Exclusão de reuniões", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de reunião realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Reunião não encontrada")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarReuniao (@Valid @PathVariable Integer id){
        reuniaoService.removerPorId(id);
        return ResponseEntity.status(204).build();

    }
    
}
