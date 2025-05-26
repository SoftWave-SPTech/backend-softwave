package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class PesquisaController {

    @Autowired
    private PesquisaService pesquisaService;

    @GetMapping("/com-processos")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> listarClientesComProcessos() {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.buscarClientesComProcessos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/pesquisa/{termo}")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorTermo(@PathVariable String termo) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.pesquisarPorTermo(termo);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/filtro-setor/{setor}")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorSetor(@PathVariable String setor) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorSetor(setor);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/filtro-vara/{vara}")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorVara(@PathVariable String vara) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorVara(vara);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/filtro-assunto/{assunto}")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorAssunto(@PathVariable String assunto) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorAssunto(assunto);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/filtro-foro/{foro}")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorForo(@PathVariable String foro) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorForo(foro);
        return ResponseEntity.ok(clientes);
    }

//    @GetMapping("/processos/pesquisa")
//    public ResponseEntity<List<ProcessoResponseDTO>> pesquisarProcessos(
//            @RequestParam(required = false) String termo
//    ) {
//        List<Processo> processos = pesquisaService.pesquisarPorTermo(termo);
//        List<ProcessoResponseDTO> dto = processos.stream()
//                .map(ProcessoResponseDTO::toResponseDto)
//                .toList();
//        return ResponseEntity.ok(dto);
//    }

}
