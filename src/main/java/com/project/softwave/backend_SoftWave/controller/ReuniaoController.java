package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.entity.Reuniao;
import com.project.softwave.backend_SoftWave.service.ReuniaoService;
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


    @PostMapping
    public ResponseEntity<Reuniao> cadastrarReuniao(@Valid @RequestBody Reuniao reuniao) {

        Reuniao reuniaoCadastrada = reuniaoService.cadastrarReuniao(reuniao);
        return ResponseEntity.status(201).body(reuniaoCadastrada);

    }

    @GetMapping
    public ResponseEntity<List<Reuniao>> listarReuniao() {
        List<Reuniao> reunioes = reuniaoService.listarReuniao();


        return ResponseEntity.status(200).body(reunioes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Reuniao> buscarPorId(@PathVariable Integer id) {

        return ResponseEntity.status(200).body(reuniaoService.buscarPorId(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Reuniao> atualizarReuniao(@Valid @RequestBody Reuniao reuniaoParaAtualizar, @PathVariable Integer id ) {


        return ResponseEntity.status(200).body(reuniaoService.atualizarReuniao(reuniaoParaAtualizar, id));


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReuniao (@Valid Integer id){
        reuniaoService.removerPorId(id);
        return ResponseEntity.status(204).build();

    }
    
}
