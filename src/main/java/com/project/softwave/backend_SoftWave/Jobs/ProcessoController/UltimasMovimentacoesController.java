package com.project.softwave.backend_SoftWave.controller;


import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.UltimasMovimentacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ultimas-movimentacoes")
public class UltimasMovimentacoesController {

    @Autowired
    private UltimasMovimentacoesService ultimasMovimentacoesService;

    @GetMapping("/ordenadas")
    public ResponseEntity<List<UltimasMovimentacoes>> listarMovimentacoesOrdenadasPorData() {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.listarMovimentacoesOrdenadasPorData();
        return ResponseEntity.status(200).body(movimentacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UltimasMovimentacoes> buscarPorId (@PathVariable Integer id){
            UltimasMovimentacoes movimentacao = ultimasMovimentacoesService.buscarPorId(id);
            return ResponseEntity.status(200).body(movimentacao);
    }


    @GetMapping("/processo/{processoId}")
    public ResponseEntity<List<UltimasMovimentacoes>> buscarPorIdProcesso (@PathVariable Integer processoId){
            List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.buscarPorIdProcesso(processoId);
            return ResponseEntity.status(200).body(movimentacoes);
    }

    @GetMapping("/processo/{processoId}/ordenadas")
    public ResponseEntity<List<UltimasMovimentacoes>> listarMovimentacoesPorIdProcessoOrdenadasPorData(
            @PathVariable Integer processoId) {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesService.listarMovimentacoesPorIdProcessoOrdenadasPorData(processoId);
        return ResponseEntity.status(200).body(movimentacoes);
    }

}
