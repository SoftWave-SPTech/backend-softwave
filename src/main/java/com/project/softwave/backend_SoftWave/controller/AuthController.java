package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.usuariosDtos.*;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

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

    @PostMapping("/solicitar-reset-senha")
    public ResponseEntity<String> solicitarResetSenha(
            @Valid @RequestBody String email) {
        usuarioService.solicitarResetSenha(email);
        return ResponseEntity.ok().body("Email enviado ?");
    }

    @PostMapping("/resetar-senha")
    public ResponseEntity<Void> resetarSenha(
            @Valid @RequestBody ResetSenhaRequest request) {
        usuarioService.resetarSenha(request.getToken(), request.getNovaSenha() , request.getNovaSenhaConfirma());
        return ResponseEntity.ok().build();
    }
}
