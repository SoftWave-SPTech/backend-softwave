package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;

import com.project.softwave.backend_SoftWave.Jobs.ParametrosAPI;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.CadastroProcessoDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoGrau1API;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.RemoverUsuarioProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.VincularUsuariosProcessoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/processos")
public class ProcessoController {

    @Autowired
    private ProcessoService processoService;

    @Autowired
    private ProcessoGrau1API processoGrau1API;

    @Operation(summary = "Vinculação de usuarios aos processos", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários vinculados com sucesso ao processo."),
    })
    @PostMapping("/vincular-usuarios")
    public ResponseEntity<String> vincularUsuarios(@RequestBody VincularUsuariosProcessoDTO dto) {
        processoService.vincularUsuariosAoProcesso(dto);
        return ResponseEntity.ok("Usuários vinculados com sucesso ao processo.");
    }

    @Operation(summary = "Remoção de usuario do processo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido do processo com sucesso."),
    })
    @DeleteMapping("/remover-usuario")
    public ResponseEntity<String> removerUsuarioDoProcesso(@RequestBody RemoverUsuarioProcessoDTO dto) {
        processoService.removerUsuarioDoProcesso(dto);
        return ResponseEntity.ok("Usuário removido do processo com sucesso.");
    }

    @PostMapping
    public ResponseEntity<String> criarProcesso(@RequestBody CadastroProcessoDTO novoProcesso) {
        String numeroProcesso = novoProcesso.getNumeroProcesso();
        ParametrosAPI.resetParametros();
        ParametrosAPI.setParametroProcesso(numeroProcesso);
        try {
            processoGrau1API.getApiParams();
            Processo processoAtual = processoService.buscarPorNumeroProcesso(numeroProcesso);
            processoService.atualizarProcessoComUsuarios(processoAtual, novoProcesso);
            return ResponseEntity.status(HttpStatus.CREATED).body("Processo criado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o processo: " + e.getMessage());
        }

    }

    //Atualizar Processo
}
