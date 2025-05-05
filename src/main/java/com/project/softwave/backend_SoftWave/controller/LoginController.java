package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioSenhaDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioTokenDTO;
import com.project.softwave.backend_SoftWave.dto.mappers.UsuarioMapper;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDTO usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @PostMapping("/primeiro-acesso")
    public ResponseEntity<UsuarioLoginDto> primeiroAcesso(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioLoginDto usuarioLogado = this.usuarioService.primeiroAcesso(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioLogado);
    }

    @PatchMapping("/cadastrar-senha")
    public ResponseEntity<UsuarioTokenDTO> cadastrarSenha(@RequestBody UsuarioSenhaDto usuarioSenhaDto) {
        this.usuarioService.cadastrarSenha(usuarioSenhaDto);
        return ResponseEntity.status(200).build();
    }
}
