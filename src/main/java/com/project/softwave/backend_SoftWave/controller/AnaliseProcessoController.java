package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AnaliseProcessoDTO;
import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.service.AnaliseProcessoService;
import com.project.softwave.backend_SoftWave.service.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analise-processo")
public class AnaliseProcessoController {

    @Autowired
    private AnaliseProcessoService analiseService;

    @Autowired
    private GeminiService geminiService;

    @Operation(summary = "Geração das análises de movimentações dos processos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Análises geradas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao gerar as análises")
    })
    @PostMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> analisarProcessos() {
        geminiService.gerarAnalises();
        return ResponseEntity.status(201).body("Análises geradas com sucesso!");
    }

    @Operation(summary = "Lista todas as análises de movimentações dos processos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de análises retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há análises geradas")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<AnaliseProcessoDTO>> listarAnalises() {
        List<AnaliseProcesso> analises = analiseService.listarTodas();
        if (analises.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AnaliseProcessoDTO> analisesDTO = analises.stream()
                .map(AnaliseProcessoDTO::toDTO)
                .toList();
        return ResponseEntity.ok(analisesDTO);
    }

    @Operation(summary = "Busca uma análise por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Análise encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Análise não encontrada")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AnaliseProcessoDTO> buscarPorId(@PathVariable Integer id) {
        AnaliseProcesso analiseProcesso = analiseService.buscarPorId(id);
        return ResponseEntity.ok(AnaliseProcessoDTO.toDTO(analiseProcesso));
    }

    @Operation(summary = "Busca uma análise de processo por ID da movimentação", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Análise encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Análise não encontrada para o ID da movimentação fornecido")
    })
    @GetMapping("/por-movimentacao/{movimentacaoId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<AnaliseProcessoDTO> buscarPorMovimentacao(@PathVariable Integer movimentacaoId) {
        AnaliseProcesso analiseProcesso = analiseService.buscarPorIdMovimentacao(movimentacaoId);
        return ResponseEntity.ok(AnaliseProcessoDTO.toDTO(analiseProcesso));
    }
}
