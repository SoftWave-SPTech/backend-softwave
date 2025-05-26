package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.AdvogadosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuariosResponseDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PesquisaService pesquisaService;

    @GetMapping("/listar-advogados")
    public ResponseEntity<List<AdvogadosResponseDTO>> listarAdvogados() {
        List<AdvogadosResponseDTO> advogados = pesquisaService.listarAdvogados();

        return ResponseEntity.status(200).body(advogados);
    }

    @GetMapping("/listar-clientes")
    public ResponseEntity<List<UsuariosResponseDTO>> listarClientes() {
        List<UsuariosResponseDTO> clientes = pesquisaService.listarClientes();

        return ResponseEntity.status(200).body(clientes);
    }

}
