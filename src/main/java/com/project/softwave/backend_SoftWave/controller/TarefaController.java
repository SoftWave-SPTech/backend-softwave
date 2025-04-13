package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.dto.TarefaDTO;
import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tarefas")
public class TarefaController {


    @Autowired
    private TarefaService service;


    @Operation(summary = "Cadastro de tarefas", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro da tarefa realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Tarefa já cadastrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TarefaDTO> cadastrarTarefa(@Valid @RequestBody TarefaDTO request) {
            Tarefa tarefa = TarefaDTO.toEntity(request);
            Tarefa tarefaResponse = service.cadastrarTarefa(tarefa);
        return ResponseEntity.status(201).body(TarefaDTO.toResponseDto(tarefaResponse));

    }


    @Operation(summary = "Busca por todas as tarefas cadastradas pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca das tarefas realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há tarefas cadastradas")
    })
        @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<Tarefa> tarefas = service.listarTarefas();

            List<TarefaDTO> tarefaDtos = tarefas.stream()
                    .map(TarefaDTO::toResponseDto)
                    .toList();

            return ResponseEntity.status(200).body(tarefaDtos);
    }


    @Operation(summary = "Busca por ID de uma tarefa cadastrada pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID da tarefa realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "204", description = "Não há tarefa cadastrada com esse ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@Valid @PathVariable Integer id) {
        Tarefa tarefaResponse = service.buscarPorId(id);
        return ResponseEntity.status(200).body(TarefaDTO.toResponseDto(tarefaResponse));

    }

    @Operation(summary = "Atualização das informações das tarefas", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização da tarefa realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@Valid @RequestBody Tarefa tarefa, @Valid @PathVariable Integer id ) {

        Tarefa tarefaResponse = service.atualizarTarefa(tarefa, id);
        return ResponseEntity.status(200).body(TarefaDTO.toResponseDto(tarefaResponse));

    }

    @Operation(summary = "Exclusão de tarefas", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de tarefa realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa (@Valid @PathVariable Integer id){
        service.deletarTarefa(id);
        return ResponseEntity.status(204).build();

        }
    }