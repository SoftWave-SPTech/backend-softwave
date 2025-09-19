package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.service.PesquisaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class PesquisaController {

    @Autowired
    private PesquisaService pesquisaService;

  @io.swagger.v3.oas.annotations.Operation(
    summary = "Lista todos os clientes com processos pelo ID do advogado vinculado",
    description = "Retorna uma lista de clientes que possuem processos cadastrados."
)
    @GetMapping("/com-processos/advogado/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> listarClientesPorAdvogado(@Valid @PathVariable Integer id) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.buscarClientesPorAdvogado(id);
        return ResponseEntity.ok(clientes);
    }

@io.swagger.v3.oas.annotations.Operation(
    summary = "Lista todos os clientes com processos",
    description = "Retorna uma lista de clientes que possuem processos cadastrados."
)
@GetMapping("/com-processos")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> listarClientesComProcessos() {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.buscarClientesComProcessos();
    return ResponseEntity.ok(clientes);
}

@Operation(
    summary = "Filtra clientes por termo",
    description = "Retorna uma lista de clientes que correspondem ao termo pesquisado."
)
@Parameter(
    name = "termo",
    description = "Termo para pesquisa (nome, documento, etc.)",
    required = true
)
@GetMapping("/pesquisa/{termo}")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorTermo(@Valid @PathVariable String termo) {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.pesquisarPorTermo(termo);
    return ResponseEntity.ok(clientes);
}

@Operation(
    summary = "Filtra clientes por setor",
    description = "Retorna uma lista de clientes filtrados pelo setor informado."
)
@Parameter(
    name = "setor",
    description = "Setor para filtro",
    required = true
)
@GetMapping("/filtro-setor/{setor}")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorSetor(@Valid @PathVariable String setor) {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorSetor(setor);
    return ResponseEntity.ok(clientes);
}

@Operation(
    summary = "Filtra clientes por vara",
    description = "Retorna uma lista de clientes filtrados pela vara informada."
)
@Parameter(
    name = "vara",
    description = "Vara para filtro",
    required = true
)
@GetMapping("/filtro-vara/{vara}")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorVara(@Valid @PathVariable String vara) {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorVara(vara);
    return ResponseEntity.ok(clientes);
}

@Operation(
    summary = "Filtra clientes por assunto",
    description = "Retorna uma lista de clientes filtrados pelo assunto informado."
)
@Parameter(
    name = "assunto",
    description = "Assunto para filtro",
    required = true
)
@GetMapping("/filtro-assunto/{assunto}")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorAssunto(@Valid @PathVariable String assunto) {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorAssunto(assunto);
    return ResponseEntity.ok(clientes);
}

@Operation(
    summary = "Filtra clientes por foro",
    description = "Retorna uma lista de clientes filtrados pelo foro informado."
)
@Parameter(
    name = "foro",
    description = "Foro para filtro",
    required = true
)
@GetMapping("/filtro-foro/{foro}")
@SecurityRequirement(name = "Bearer")
public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorForo(@Valid @PathVariable String foro) {
    List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorForo(foro);
    return ResponseEntity.ok(clientes);
}
  

  @Operation(
    summary = "Filtra clientes por descrição do processo",
    description = "Retorna uma lista de clientes filtrados pela descrição informada."
)
@Parameter(
    name = "dercrição",
    description = "Descrição para filtro",
    required = true
)
    @GetMapping("/filtro-descricao/{descricao}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorDescricao(@Valid @PathVariable String descricao) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorDescricao(descricao);
        return ResponseEntity.ok(clientes);
    }

    @Operation(
    summary = "Filtra processos por status de cliente",
    description = "Retorna uma lista de clientes filtrados pela descrição informada."
)
@Parameter(
    name = "status",
    description = "Status para filtro",
    required = true
)
    @GetMapping("/filtro-status-cliente/{status}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<ClienteComProcessosResponseDTO>> filtrarClientesPorStatus(@Valid @PathVariable String status) {
        List<ClienteComProcessosResponseDTO> clientes = pesquisaService.filtrarClientesPorStatus(status);
        return ResponseEntity.ok(clientes);
    }

}
