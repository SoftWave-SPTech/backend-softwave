package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.AdvogadosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioFotoPerfilDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuariosResponseDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.service.FotoPerfilService;
import com.project.softwave.backend_SoftWave.service.PesquisaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PesquisaService pesquisaService;
    @Autowired
    private FotoPerfilService fotoPerfilService;

    @Operation(
            summary = "Listar todos os advogados",
            description = "Retorna uma lista de advogados cadastrados no sistema."
    )
    @GetMapping("/listar-advogados")
    public ResponseEntity<List<AdvogadosResponseDTO>> listarAdvogados() {
        List<AdvogadosResponseDTO> advogados = pesquisaService.listarAdvogados();

        return ResponseEntity.status(200).body(advogados);
    }

    @Operation(
            summary = "Listar todos os clientes",
            description = "Retorna uma lista de clientes cadastrados no sistema."
    )
    @GetMapping("/listar-clientes")
    public ResponseEntity<List<UsuariosResponseDTO>> listarClientes() {
        List<UsuariosResponseDTO> clientes = pesquisaService.listarClientes();

        return ResponseEntity.status(200).body(clientes);
    }

    @Operation(summary = "Atualizar a foto de perfil dos usuários", method = "PUT")
    @PutMapping("foto-perfil/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> atualizarFotoPerfil(
            @PathVariable Integer id,
            @Valid @RequestParam("fotoPerfil") MultipartFile usuarioFotoPerfilDTO
    ) throws IOException {

        String fotoPerfilUrl = fotoPerfilService.atualizarFotoPerfil(id, usuarioFotoPerfilDTO);

        return ResponseEntity.status(200).body(fotoPerfilUrl);
    }

    @Operation(summary = "Deletar a foto de perfil dos usuários", method = "DELETE")
    @DeleteMapping("foto-perfil/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> deletarFotoPerfil(
            @PathVariable Integer id
    ) throws IOException {

        fotoPerfilService.deletarFotoPerfil(id);

        return ResponseEntity.status(200).build();
    }

    @Operation(summary = "Buscar a foto de perfil dos usuários", method = "GET")
    @GetMapping("foto-perfil/{id}")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<String> buscarFotoPerfilPorId(
            @PathVariable Integer id
    ){
        return ResponseEntity.status(200).body(fotoPerfilService.buscarPorId(id));
    }

}
