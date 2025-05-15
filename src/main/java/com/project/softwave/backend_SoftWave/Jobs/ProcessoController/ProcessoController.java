package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.RemoverUsuarioProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.VincularUsuariosProcessoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Operation(summary = "Vinculação de usuarios aos processos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários vinculados com sucesso ao processo."),
    })
    @PostMapping("/vincular-usuarios")
    public ResponseEntity<String> vincularUsuarios(@RequestBody VincularUsuariosProcessoDTO dto) {
        processoService.vincularUsuariosAoProcesso(dto);
        return ResponseEntity.ok("Usuários vinculados com sucesso ao processo.");
    }

    @Operation(summary = "Remoção de usuario do processo", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido do processo com sucesso."),
    })
    @DeleteMapping("/remover-usuario")
    public ResponseEntity<String> removerUsuarioDoProcesso(@RequestBody RemoverUsuarioProcessoDTO dto) {
        processoService.removerUsuarioDoProcesso(dto);
        return ResponseEntity.ok("Usuário removido do processo com sucesso.");
    }



}
