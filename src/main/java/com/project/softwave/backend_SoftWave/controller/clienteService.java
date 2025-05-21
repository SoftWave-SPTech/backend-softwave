package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.service.PesquisaService;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class clienteService {

    @Autowired
    private PesquisaService pesquisaService;

    @GetMapping("/com-processos")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> listarClientesComProcessos() {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.buscarClientesComProcessos();
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
