package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoDTO;
import com.project.softwave.backend_SoftWave.service.UsuarioFisicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuariosFisicos")
public class UsuarioFisicoController {

    @Autowired
    private UsuarioFisicoService usuarioFisicoService;

    @PostMapping
    public ResponseEntity<UsuarioFisicoDTO> cadastrar(@Valid @RequestBody UsuarioFisicoDTO usuarioFisicoDTO){

        UsuarioFisicoDTO usuarioNovo = usuarioFisicoService.cadastrar(usuarioFisicoDTO);

        if(usuarioNovo != null){
            return ResponseEntity.status(201).body(usuarioNovo);
        }
        return ResponseEntity.status(409).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioFisicoDTO> login(@Valid @RequestBody UsuarioFisicoDTO usuarioFisicoDTO){
        UsuarioFisicoDTO usuarioAutenticado = usuarioFisicoService.login(usuarioFisicoDTO);
        if(usuarioAutenticado != null){
            return ResponseEntity.status(200).body(usuarioAutenticado);
        }
        return ResponseEntity.status(409).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@Valid @PathVariable Integer id){

        if(usuarioFisicoService.deletar(id)){
            return  ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    // Com RequestParam ao invés de PathVariable
//    @DeleteMapping("/id")
//    public ResponseEntity deletar(@RequestParam Integer id){
//
//        if(usuarioFisicoService.deletar(id)){
//            return  ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioFisicoDTO> atualizar(
          @Valid  @PathVariable Integer id,
            @RequestBody UsuarioFisicoDTO usuarioFisicoDTO
    ){
        UsuarioFisicoDTO usuarioAtualizado = usuarioFisicoService.atualizar(id,usuarioFisicoDTO);

        if(usuarioAtualizado != null){
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(409).build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioFisicoDTO>> listar(){
        List<UsuarioFisicoDTO> usuarios = usuarioFisicoService.listar();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);

    }
}
