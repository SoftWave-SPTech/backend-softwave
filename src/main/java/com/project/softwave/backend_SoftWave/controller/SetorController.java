package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.SetorDTO;
import com.project.softwave.backend_SoftWave.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/setores")
public class SetorController {

    @Autowired
    private SetorService setorService;

    @PostMapping
    public ResponseEntity<SetorDTO> criarSetor(@Valid @RequestBody SetorDTO setorDTO) {
        SetorDTO setorNovo = setorService.criarSetor(setorDTO);
        return ResponseEntity.status(201).body(setorNovo);
    }

    @GetMapping
    public ResponseEntity<List<SetorDTO>> listarSetores() {
        List<SetorDTO> setores = setorService.listarSetores();
        return setores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(setores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorDTO> buscarSetorPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarSetorPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorDTO> atualizarSetor(@Valid @PathVariable Long id, @Valid @RequestBody SetorDTO setorDTO) {
        return ResponseEntity.ok(setorService.atualizarSetor(id, setorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSetor(@Valid @PathVariable Long id) {
        setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();
    }

}
