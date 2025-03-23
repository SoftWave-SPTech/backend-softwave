package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.RegistroFinanceiroDTO;
import com.project.softwave.backend_SoftWave.service.RegistroFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrosFinanceiros")
public class RegistroFinanceiroController {

    @Autowired
    private RegistroFinanceiroService registroFinanceiroService;

    @PostMapping
    public ResponseEntity<RegistroFinanceiroDTO> criarRegistro(@RequestBody RegistroFinanceiroDTO dto) {
        RegistroFinanceiroDTO novoRegistro = registroFinanceiroService.criarRegistro(dto);
        return ResponseEntity.status(201).body(novoRegistro);
    }

    @GetMapping
    public ResponseEntity<List<RegistroFinanceiroDTO>> listarRegistros() {
        List<RegistroFinanceiroDTO> registros = registroFinanceiroService.listarRegistros();
        return registros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(registros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroDTO> buscarRegistroPorId(@PathVariable Long id) {
        RegistroFinanceiroDTO registro = registroFinanceiroService.buscarRegistroPorId(id);
        return registro != null ? ResponseEntity.ok(registro) : ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroDTO> atualizarRegistro(@PathVariable Long id, @RequestBody RegistroFinanceiroDTO dto) {
        RegistroFinanceiroDTO registroAtualizado = registroFinanceiroService.atualizarRegistro(id, dto);
        return registroAtualizado != null ? ResponseEntity.ok(registroAtualizado) : ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        boolean deletado = registroFinanceiroService.deletarRegistro(id);
        return deletado ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
    }
}