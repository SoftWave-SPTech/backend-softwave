package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.service.AnaliseProcessoService;
import com.project.softwave.backend_SoftWave.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analises")
public class AnaliseProcessoController {

    @Autowired
    private AnaliseProcessoService analiseService;

    @Autowired
    private GeminiService geminiService;


    @PostMapping
    public ResponseEntity<String> analisarProcessos() {
        geminiService.gerarAnalises();
        return ResponseEntity.status(201).body("Análises geradas com sucesso!");
    }


    @GetMapping
    public ResponseEntity<List<AnaliseProcesso>> listarAnalises() {
        List<AnaliseProcesso> analises = analiseService.listarTodas();
        return ResponseEntity.ok(analises);
    }
}

