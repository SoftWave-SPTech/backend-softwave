package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.SetorDTO;
import com.project.softwave.backend_SoftWave.service.SetorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cadastro de setores", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de setor realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Setor já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<SetorDTO> criarSetor(@Valid @RequestBody SetorDTO setorDTO) {
        SetorDTO setorNovo = setorService.criarSetor(setorDTO);
        return ResponseEntity.status(201).body(setorNovo);
    }

    @Operation(summary = "Busca por todos os setores cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos setores realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há setores cadastradas")
    })
    @GetMapping
    public ResponseEntity<List<SetorDTO>> listarSetores() {
        List<SetorDTO> setores = setorService.listarSetores();
        return setores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(setores);
    }

    @Operation(summary = "Busca por ID de uma setor cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID do setor realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há setor cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SetorDTO> buscarSetorPorId(@Valid @PathVariable Long id) {
        return ResponseEntity.ok(setorService.buscarSetorPorId(id));
    }

    @Operation(summary = "Atualização das informações dos setores", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do setor realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SetorDTO> atualizarSetor(@Valid @PathVariable Long id, @Valid @RequestBody SetorDTO setorDTO) {
        return ResponseEntity.ok(setorService.atualizarSetor(id, setorDTO));
    }

    @Operation(summary = "Exclusão de setor", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão do setor realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Setor não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSetor(@Valid @PathVariable Long id) {
        setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();
    }

}
