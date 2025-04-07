package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ProcessoDTO;
import com.project.softwave.backend_SoftWave.service.ProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cadastro de processos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro de processo realizado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Processo já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<ProcessoDTO> criarProcesso(@Valid  @RequestBody ProcessoDTO processoDTO) {
        ProcessoDTO processoCriado = processoService.criarProcesso(processoDTO);
        return ResponseEntity.status(201).body(processoCriado);
    }

    @Operation(summary = "Busca por todos os processos cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos processos realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há processos cadastrados")
    })
    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listarProcessos() {
        List<ProcessoDTO> listaDeProcessos = processoService.listarProcessos();
        return listaDeProcessos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(listaDeProcessos);
    }

    @Operation(summary = "Busca por ID de um processo cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há processo cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProcessoDTO> buscarProcessoPorId(@Valid @PathVariable Long id) {
        ProcessoDTO processo = processoService.buscarProcessoPorId(id);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Busca por número do processo cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por número do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há processo cadastrado com esse número")
    })
    @GetMapping("/numero/{numero}")
    public ResponseEntity<ProcessoDTO> buscarProcessoPorNumero(@Valid @PathVariable String numero) {
        ProcessoDTO processo = processoService.buscarProcessoPorNumero(numero);
        return processo != null ? ResponseEntity.ok(processo) : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Atualização das informações dos processos", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProcessoDTO> atualizarProcesso(@Valid @PathVariable Long id, @RequestBody ProcessoDTO processoDTO) {
        ProcessoDTO processoAtualizado = processoService.atualizarProcesso(id, processoDTO);
        return processoAtualizado != null ? ResponseEntity.ok(processoAtualizado) : ResponseEntity.status(404).build();
    }

    @Operation(summary = "Exclusão de processos", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de processo realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@Valid @PathVariable Long id) {
        boolean deletado = processoService.deletarProcesso(id);
        return deletado ? ResponseEntity.status(204).build() : ResponseEntity.status(404).build();
    }
}
