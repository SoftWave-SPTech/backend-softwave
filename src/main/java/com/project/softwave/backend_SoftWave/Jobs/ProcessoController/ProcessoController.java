package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.CadastroProcessoDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.ProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.ProcessoCompletoDTO;
import com.project.softwave.backend_SoftWave.dto.ProcessoSimplesDTO;
import com.project.softwave.backend_SoftWave.dto.RemoverUsuarioProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.VincularUsuariosProcessoDTO;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

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
//        ParametrosAPI.resetParametros();
//        ParametrosAPI.setParametroProcesso(numeroProcesso);
//        try {
//            processoGrau1API.getApiParams();
//            Processo processoAtual = processoService.buscarPorNumeroProcesso(numeroProcesso);
//            processoService.atualizarProcessoComUsuarios(processoAtual, novoProcesso);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Processo criado com sucesso!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o processo: " + e.getMessage());
//        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Processo criado com sucesso!");
    }

//    @GetMapping("/usuario-id/{id}")
//    public ResponseEntity<List<ProcessoSimplesDTO>> listarProcessoPorIdUsuario(@PathVariable Integer id) {
//        try {
//            List<ProcessoSimplesDTO> processosDoUsuario = processoService.listarProcessoPorIdUsuario(id);
//            return ResponseEntity.ok(processosDoUsuario);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }


  @Operation(summary = "Buscar processo por ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado."),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProcessoSimplesDTO> listarProcessoPorId(@PathVariable Integer id) {
        try {
                Processo processo = processoService.listarProcessoPorId(id);
            ProcessoSimplesDTO processoDTO = ProcessoSimplesDTO.toProcessoSimplesDTO(processo);
            return ResponseEntity.ok(processoDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @Operation(summary = "Buscar processo por ID do Usuário", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado."),
    })
    @GetMapping("/usuario-id/{id}")
    public ResponseEntity<List<ProcessoDTO>> listarProcessosPorUsuarioId(@PathVariable Integer id) {
        try {
            List<Processo> processos = processoService.listarProcessosPorUsuarioId(id);
            List<ProcessoDTO> processoDtos = processos.stream()
                    .map(ProcessoDTO::toResponseDto)
                    .toList();
            return ResponseEntity.ok(processoDtos);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Listar todos os processos", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem de processos realizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum processo encontrado.")
    })
    @GetMapping
    public ResponseEntity<List<ProcessoDTO>> listarProcessos(){
        List<Processo> processos = processoService.listarProcessos();

        List<ProcessoDTO> processoDtos = processos.stream()
                .map(ProcessoDTO::toResponseDto)
                .toList();

        return ResponseEntity.status(200).body(processoDtos);
    }


    @Operation(summary = "Exclusão de um processo", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Exclusão do processo realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Processo não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcesso(@PathVariable Integer id) {
        try {
            processoService.deletarProcesso(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("visualizar-processo/{id}")
    public ResponseEntity<ProcessoCompletoDTO> buscarProcessoPorId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(processoService.buscarProcessoPorId(id));
    }
}
