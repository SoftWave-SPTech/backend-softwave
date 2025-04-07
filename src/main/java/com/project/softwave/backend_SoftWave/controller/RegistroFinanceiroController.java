package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.RegistroFinanceiroDTO;
import com.project.softwave.backend_SoftWave.service.RegistroFinanceiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrosFinanceiros")
public class RegistroFinanceiroController {

    @Autowired
    private RegistroFinanceiroService registroFinanceiroService;

    @Operation(summary = "Cadastro de registros financeiros", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Registro financeiro já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<RegistroFinanceiroDTO> criarRegistro(@Valid @RequestBody RegistroFinanceiroDTO dto) {
        RegistroFinanceiroDTO novoRegistro = registroFinanceiroService.criarRegistro(dto);
        return ResponseEntity.status(201).body(novoRegistro);
    }


    @Operation(summary = "Busca por todos os registros financeiros cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos registros financeiros realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há registros financeiros cadastrados")
    })
    @GetMapping
    public ResponseEntity<List<RegistroFinanceiroDTO>> listarRegistros() {
        List<RegistroFinanceiroDTO> registros = registroFinanceiroService.listarRegistros();
        return registros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(registros);
    }


    @Operation(summary = "Busca por ID de um registro financeiro cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há um registro financeiro cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroDTO> buscarRegistroPorId(@Valid @PathVariable Long id) {
        RegistroFinanceiroDTO registro = registroFinanceiroService.buscarRegistroPorId(id);
        return registro != null ? ResponseEntity.ok(registro) : ResponseEntity.status(404).build();
    }


    @Operation(summary = "Atualização das informações dos registros financeiros", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RegistroFinanceiroDTO> atualizarRegistro(@Valid @PathVariable Long id, @RequestBody RegistroFinanceiroDTO dto) {
        RegistroFinanceiroDTO registroAtualizado = registroFinanceiroService.atualizarRegistro(id, dto);
        return registroAtualizado != null ? ResponseEntity.ok(registroAtualizado) : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Exclusão de registros financeiros", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRegistro(@Valid @PathVariable Long id) {
        boolean deletado = registroFinanceiroService.deletarRegistro(id);
        return deletado ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
    }
}