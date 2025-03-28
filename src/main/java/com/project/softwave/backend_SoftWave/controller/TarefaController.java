package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.repository.TarefaRepository;
import com.project.softwave.backend_SoftWave.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {


    @Autowired
    private TarefaService tarefaService;


    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@Valid @RequestBody Tarefa tarefa) {

            Tarefa tarefaCadastrada = tarefaService.cadastrarTarefa(tarefa);
            return ResponseEntity.status(201).body(tarefaCadastrada);

    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefa();


        return ResponseEntity.status(200).body(tarefas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(tarefaService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@Valid @RequestBody Tarefa tarefaParaAtualizar, @PathVariable Integer id ) {


            return ResponseEntity.status(200).body(tarefaService.atualizarTarefa(tarefaParaAtualizar, id));


    }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletarTarefa (@Valid Integer id){
            tarefaService.removerPorId(id);
            return ResponseEntity.status(204).build();

        }
    }