package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.FinanceiroDadosTelaDTO;
import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.FinanceiroRequestDTO;
import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.FinanceiroResponseDTO;
import com.project.softwave.backend_SoftWave.dto.RegistroFinanceiroDTO;
import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import com.project.softwave.backend_SoftWave.service.RegistroFinanceiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros-financeiros")
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
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FinanceiroResponseDTO> criarRegistro(
            @Valid @RequestBody FinanceiroRequestDTO dto,
            @Valid @RequestParam Integer cliente,
            @Valid @RequestParam Integer processo
    ) {
        RegistroFinanceiro registro = FinanceiroRequestDTO.toEntity(dto);
        FinanceiroResponseDTO registroCriado = FinanceiroResponseDTO
                .toDto(
                        registroFinanceiroService.criarRegistro(registro, cliente, processo)
                );

        return ResponseEntity.status(201).body(registroCriado);
    }


    @Operation(summary = "Busca por todos os registros financeiros cadastrados pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca dos registros financeiros realizado com sucesso"),
            @ApiResponse(responseCode = "204", description = "Não há registros financeiros cadastrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<List<FinanceiroResponseDTO>> listarRegistros() {
        List<RegistroFinanceiro> registros = registroFinanceiroService.listarRegistros();

        List<FinanceiroResponseDTO> todos = registros.stream()
                .map(FinanceiroResponseDTO::toDto).toList();

        return ResponseEntity.status(200).body(todos);
    }


    @Operation(summary = "Busca por ID de um registro financeiro cadastrado pelo usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca por ID de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado"),
            @ApiResponse(responseCode = "204", description = "Não há um registro financeiro cadastrado com esse ID")
    })
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FinanceiroResponseDTO> buscarRegistroPorId(@Valid @PathVariable Integer id) {
        RegistroFinanceiro registro = registroFinanceiroService.buscarRegistroPorId(id);

        return ResponseEntity.status(200).body(FinanceiroResponseDTO.toDto(registro));
    }


    @Operation(summary = "Atualização das informações dos registros financeiros", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualização do registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FinanceiroResponseDTO> atualizarRegistro(
            @Valid @PathVariable Integer id,
            @RequestBody FinanceiroRequestDTO dto,
            @Valid @RequestParam Integer cliente,
            @Valid @RequestParam Integer processo
    ) {
        RegistroFinanceiro registro = registroFinanceiroService.atualizarRegistro(id, FinanceiroRequestDTO.toEntity(dto), cliente, processo);

        return ResponseEntity.status(200).body(FinanceiroResponseDTO.toDto(registro));
    }

    @Operation(summary = "Exclusão de registros financeiros", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> deletarRegistro(@Valid @PathVariable Integer id) {
        registroFinanceiroService.deletarRegistro(id);
        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Atualização de status do registros financeiros", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração de registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @PutMapping("/status")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FinanceiroResponseDTO> atualizarStatusRegistro(@Valid @RequestParam String status, @Valid @RequestParam Integer id) {
        RegistroFinanceiro registro = registroFinanceiroService.atualizarStatusRegistro(id, status);

        return ResponseEntity.status(200).body(FinanceiroResponseDTO.toDto(registro));
    }

    @Operation(summary = "Buscar dados do registro financeiro ...", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de dados do registro financeiro realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro financeiro não encontrado")
    })
    @GetMapping("/dados/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<FinanceiroDadosTelaDTO> dadosDaTela(@Valid @PathVariable Integer id) {
        return ResponseEntity.status(200).body(registroFinanceiroService.dadosDaTela(id));
    }
}