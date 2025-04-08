package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;

import com.project.softwave.backend_SoftWave.Jobs.ParametrosAPI;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoGrau1API;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/processo")
public class ProcessoAPIController {

    private final ProcessoGrau1API processoGrau1API;

    public ProcessoAPIController(ProcessoGrau1API processoGrau1API) {
        this.processoGrau1API = processoGrau1API;
    }

    // Aqui você pode adicionar métodos para manipular os dados do processo
    @GetMapping("/numero/{numeroProcesso}")
    public ResponseEntity<HttpStatus> getProcessoByNumero(@PathVariable String numeroProcesso) {
        ParametrosAPI.resetParametros();
        ParametrosAPI.setParametroProcesso(numeroProcesso);
        try {
            processoGrau1API.getApiParams();
            return ResponseEntity.status(200).body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/oab/{oab}")
    public ResponseEntity<HttpStatus> getProcessoByOab(@PathVariable String oab) {
        ParametrosAPI.resetParametros();
        ParametrosAPI.setParametroProcesso(oab);
        try {
            processoGrau1API.getApiParams();
            return ResponseEntity.status(200).body(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
