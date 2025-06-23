package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;


import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.UltimasMovimentacoesDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.UltimasMovimentacoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ultimas-movimentacoes")
public class UltimasMovimentacoesController {

    @Autowired
    private UltimasMovimentacoesService ultimasMovimentacoesService;

    @Operation(summary = "Listagem das movimentações ordenadas por data mais recente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
    })
    @GetMapping("/ordenadas")
    public ResponseEntity<List<UltimasMovimentacoesDTO>> listarMovimentacoesOrdenadasPorData() {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.listarMovimentacoesOrdenadasPorData();
        List<UltimasMovimentacoesDTO> movimentacoesDTO = movimentacoes.stream()
                .map(UltimasMovimentacoesDTO::toDTO)
                .toList();
        return ResponseEntity.status(200).body(movimentacoesDTO);
    }

    @Operation(summary = "Busca das movimentações por ID ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<UltimasMovimentacoesDTO> buscarPorId (@PathVariable Integer id){
            UltimasMovimentacoes movimentacao = ultimasMovimentacoesService.buscarPorId(id);
            UltimasMovimentacoesDTO dto = UltimasMovimentacoesDTO.toDTO(movimentacao);
            return ResponseEntity.status(200).body(dto);
    }


    @Operation(summary = "Busca das movimentações de um processo pelo ID ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
    })
    @GetMapping("/processo/{processoId}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UltimasMovimentacoesDTO>> buscarPorIdProcesso (@PathVariable Integer processoId){
            List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.buscarPorIdProcesso(processoId);
        List<UltimasMovimentacoesDTO> movimentacoesDTO = movimentacoes.stream()
                .map(UltimasMovimentacoesDTO::toDTO)
                .toList();
            return ResponseEntity.status(200).body(movimentacoesDTO);
    }

    @Operation(summary = "Busca das movimentações ordenadas por data mais recente pelo ID do processo", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem realizada com sucesso"),
    })
    @GetMapping("/processo/{processoId}/ordenadas")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<UltimasMovimentacoesDTO>> listarMovimentacoesPorIdProcessoOrdenadasPorData(
            @PathVariable Integer processoId) {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.listarMovimentacoesPorIdProcessoOrdenadasPorData(processoId);
        List<UltimasMovimentacoesDTO> movimentacoesDTO = movimentacoes.stream()
                .map(UltimasMovimentacoesDTO::toDTO)
                .toList();
        return ResponseEntity.status(200).body(movimentacoesDTO);
    }

}
