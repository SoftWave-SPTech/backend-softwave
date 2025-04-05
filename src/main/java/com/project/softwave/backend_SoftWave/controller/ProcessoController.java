package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ProcessoDTO;
import com.project.softwave.backend_SoftWave.service.ProcessoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    private final ProcessoService processoService;

    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    @PostMapping
    public ResponseEntity<ProcessoDTO> criarProcesso(@Valid  @RequestBody ProcessoDTO processoDTO) {
        ProcessoDTO processoCriado = processoService.criarProcesso(processoDTO);
        return ResponseEntity.status(201).body(processoCriado);
    }

    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listarProcessos() {
        List<ProcessoDTO> listaDeProcessos = processoService.listarProcessos();
        return listaDeProcessos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDeProcessos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDTO> buscarProcessoPorId(@Valid @PathVariable Long id) {
        ProcessoDTO processo = processoService.buscarProcessoPorId(id);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.status(404).build();
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ProcessoDTO> buscarProcessoPorNumero(@Valid @PathVariable String numero) {
        ProcessoDTO processo = processoService.buscarProcessoPorNumero(numero);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoDTO> atualizarProcesso(@Valid @PathVariable Long id, @RequestBody ProcessoDTO processoDTO) {
        ProcessoDTO processoAtualizado = processoService.atualizarProcesso(id, processoDTO);
        return processoAtualizado != null ? ResponseEntity.ok(processoAtualizado) : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@Valid @PathVariable Long id) {
        boolean deletado = processoService.deletarProcesso(id);
        return deletado ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
    }
}
