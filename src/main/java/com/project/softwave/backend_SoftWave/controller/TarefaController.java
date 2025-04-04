package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.dto.TarefaDTO;
import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.service.TarefaService;
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


    @PostMapping
    public ResponseEntity<TarefaDTO> cadastrarTarefa(@Valid @RequestBody TarefaDTO request) {
        Tarefa tarefa = TarefaDTO.toEntity(request);
            Tarefa tarefaResponse = service.cadastrarTarefa(tarefa);
        return ResponseEntity.status(201).body(TarefaDTO.toResponseDto(tarefaResponse));

    }



        @GetMapping
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<Tarefa> tarefas = service.listarTarefas();


            if (tarefas.isEmpty()) {
              return ResponseEntity.status(204).build();
            }

            List<TarefaDTO> tarefaDtos = tarefas.stream()
                    .map(TarefaDTO::toResponseDto)
                    .toList();

            return ResponseEntity.status(200).body(tarefaDtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@Valid @PathVariable Integer id) {
        Tarefa tarefaResponse = service.buscarPorId(id);
        return ResponseEntity.status(200).body(TarefaDTO.toResponseDto(tarefaResponse));

    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizarTarefa(@Valid @RequestBody Tarefa tarefa, @Valid @PathVariable Integer id ) {

        Tarefa tarefaResponse = service.atualizarTarefa(tarefa, id);
        return ResponseEntity.status(200).body(TarefaDTO.toResponseDto(tarefaResponse));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa (@Valid @PathVariable Integer id){
        service.deletarTarefa(id);
        return ResponseEntity.status(204).build();

        }
    }