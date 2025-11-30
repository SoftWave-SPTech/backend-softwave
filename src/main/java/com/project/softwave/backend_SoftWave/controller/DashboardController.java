package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.DTOsDash.DashResponseDTO;
import com.project.softwave.backend_SoftWave.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService service;

    @Operation(summary = "Retorna os dados da dashboard", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso, dados tratados para retornarem zerados caso não encontrados")
    })
    @GetMapping
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<DashResponseDTO> dadosDash(){
        return ResponseEntity.status(200).body(service.dadosDash());
    }

}
