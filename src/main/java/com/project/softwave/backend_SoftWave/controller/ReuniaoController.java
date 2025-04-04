package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.dto.ReuniaoDTO;
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
    public ResponseEntity<ReuniaoDTO> cadastrarReuniao(@Valid @RequestBody ReuniaoDTO request) {
        Reuniao reuniao = ReuniaoDTO.toEntity(request);
        Reuniao reuniaoResponse = reuniaoService.cadastrarReuniao(reuniao);
        return ResponseEntity.status(201).body(ReuniaoDTO.toResponseDto(reuniaoResponse));

    }


    @GetMapping
    public ResponseEntity<List<ReuniaoDTO>> listarReuniao() {
        List<Reuniao> reunioes = reuniaoService.listarReuniao();

        List<ReuniaoDTO> reuniaoDtos = reunioes.stream()
                .map(ReuniaoDTO::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(reuniaoDtos);

    }



    @GetMapping("/{id}")
    public ResponseEntity<ReuniaoDTO> buscarPorId(@PathVariable Integer id) {
        Reuniao reuniaoResponse = reuniaoService.buscarPorId(id);
        return ResponseEntity.status(200).body(ReuniaoDTO.toResponseDto(reuniaoResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReuniaoDTO> atualizarReuniao(@Valid @RequestBody Reuniao reuniao, @PathVariable Integer id ) {

        Reuniao reuniaoResponse = reuniaoService.atualizarReuniao(reuniao, id);
        return ResponseEntity.status(200).body(ReuniaoDTO.toResponseDto(reuniaoResponse));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReuniao (@Valid @PathVariable Integer id){
        reuniaoService.removerPorId(id);
        return ResponseEntity.status(204).build();

    }
    
}
