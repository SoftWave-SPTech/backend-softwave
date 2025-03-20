package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoDTO;
import com.project.softwave.backend_SoftWave.service.UsuarioJuridicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuariosJuridicos")
public class UsuarioJuridicoController {

    @Autowired
    private UsuarioJuridicoService usuarioJuridicoService;


    @PostMapping
    public ResponseEntity<UsuarioJuridicoDTO> cadastrar(@RequestBody UsuarioJuridicoDTO usuarioJuridicoDTO){

        UsuarioJuridicoDTO usuarioNovo = usuarioJuridicoService.cadastrar(usuarioJuridicoDTO);

        if(usuarioNovo != null){
            return ResponseEntity.status(201).body(usuarioNovo);
        }
          return ResponseEntity.status(409).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioJuridicoDTO> login(@RequestBody UsuarioJuridicoDTO usuarioJuridicoDTO){
        UsuarioJuridicoDTO usuarioAutenticado = usuarioJuridicoService.login(usuarioJuridicoDTO);
        if(usuarioAutenticado != null){
            return ResponseEntity.status(200).body(usuarioAutenticado);
        }
        return ResponseEntity.status(409).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){

        if(usuarioJuridicoService.deletar(id)){
            return  ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

//    Com RequestParam ao invés de PathVariable
//    @DeleteMapping("/id")
//    public ResponseEntity deletar(@RequestParam Integer id){
//        if(usuarioJuridicoService.deletar(id)){
//            return  ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioJuridicoDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody UsuarioJuridicoDTO usuarioJuridicoDTO
    ){
        UsuarioJuridicoDTO usuarioAtualizado = usuarioJuridicoService.atualizar(id,usuarioJuridicoDTO);

        if(usuarioAtualizado != null){
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(409).build();
    }


    @GetMapping
    public ResponseEntity<List<UsuarioJuridicoDTO>> listar(){
        List<UsuarioJuridicoDTO> usuarios = usuarioJuridicoService.listar();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);

    }

}
