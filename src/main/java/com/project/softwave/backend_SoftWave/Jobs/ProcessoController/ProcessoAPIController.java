package com.project.softwave.backend_SoftWave.Jobs.ProcessoController;

import com.project.softwave.backend_SoftWave.Jobs.ParametrosAPI;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoGrau1API;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/processo")
public class ProcessoAPIController {

    private final ProcessoGrau1API processoGrau1API;

    public ProcessoAPIController(ProcessoGrau1API processoGrau1API) {
        this.processoGrau1API = processoGrau1API;
    }

//    @GetMapping("/numero/{numeroProcesso:[0-9.-]+}")
//    public ResponseEntity<Object> getProcessoByNumero(@PathVariable String numeroProcesso) {
//        String correlationId = java.util.UUID.randomUUID().toString();
//        try {
//            Map<String, String> body = new HashMap<>();
//            body.put("status", "ACCEPTED");
//            body.put("numeroProcesso", numeroProcesso);
//            body.put("correlationId", correlationId);
//            body.put("message", "Processo recebido com sucesso! Em breve o resultado estará disponível.");
//            return ResponseEntity.accepted().body(body);
//        } catch (Exception e) {
//            Map<String, String> body = new HashMap<>();
//            body.put("status", "error");
//            body.put("message", "Falha ao enfileirar processamento");
//            return ResponseEntity.status(500).body(body);
//        }
//    }

//    @GetMapping("/oab/{oab}")
//    public ResponseEntity<HttpStatus> getProcessoByOab(@PathVariable String oab) {
//        ParametrosAPI.resetParametros();
//        ParametrosAPI.setParametroOab(oab);
//        try {
//            processoGrau1API.getApiParams();
//            return ResponseEntity.status(200).body(HttpStatus.OK);
//        } catch (Exception e) {
//            return ResponseEntity.status(420).body(HttpStatus.METHOD_FAILURE);
//        }
//    }
}
