package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean validarSenha(String senha){
        if(senha.length() >= 8 &&
                senha.matches(".*[*$#@&/%].*") &&
                senha.matches(".*[A-Z].*") &&
                senha.matches(".*[a-z].*") &&
                senha.matches(".*[0-9].*") &&
                senha != null &&
                senha != ""
        ){
            return true;
        }else{
            return false;
        }
    }

    public Boolean validarEmail(String email){
        if(email.contains("@") &&
                email.contains(".com") &&
                email != null &&
                email != ""
        ){
            return true;
        }else{
            return false;
        }
    }
    //validar se existe cnpj igual na base
    public Boolean validarCamposVaziosJuridico(String nomeFantasia, String razaoSocial, String cnpj){

        return true;
    }
    // validar se existe rg igual na base
    public Boolean validarCamposVaziosFisico(String nome, String rg){
        return true;
    }

    //Validar este campo melhor depois e validar se já existe cpf na base
    public Boolean validarCpf(String cpf){
        return true;
    }


// validar emails iguais e cpf iguais,
    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
       if(
               usuario.getEmail() != null &&
               usuario. getSenha() != null
       ){

           if(usuario.getSenha().length() >= 8 &&
                   usuario.getSenha().matches(".*[*$#@&/%].*") &&
                   usuario.getSenha().matches(".*[A-Z].*") &&
                   usuario.getSenha().matches(".*[a-z].*") &&
                   usuario.getSenha().matches(".*[0-9].*")
           ){

               if(usuario.getEmail().contains("@") &&
                    usuario.getEmail().contains(".com")
               ){

                   Usuario usuarioCadastrado = this.usuarioRepository.save(usuario);
                   return ResponseEntity.status(201).body(usuarioCadastrado);

               }else{
                   return ResponseEntity.status(404).build();
               }
           }else{
               return ResponseEntity.status(404).build();
           }
       }
           return ResponseEntity.status(404).build();

    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario){
        Optional<Usuario> possivelUsuario = usuarioRepository.findByEmailEqualsAndSenhaEquals(usuario.getEmail(), usuario.getSenha());
        if(possivelUsuario.isPresent()){
            Usuario usuarioVerificado = possivelUsuario.get();
            return ResponseEntity.status(200).body(usuarioVerificado);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Integer id){
        if(usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return  ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

//    @DeleteMapping("/id")
//    public ResponseEntity<Usuario> deletar(@RequestParam Integer id){
//        if(usuarioRepository.existsById(id)){
//            usuarioRepository.deleteById(id);
//            return  ResponseEntity.status(200).build();
//        }
//        return ResponseEntity.status(404).build();
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody Usuario usuario){
        if(usuarioRepository.existsById(id)){

            Usuario usuarioAtualizado = usuarioRepository.getReferenceById(id);
            usuarioAtualizado.setId(id);

            if(usuario.getEmail() != null && usuario.getEmail() != ""){
                usuarioAtualizado.setEmail(usuario.getEmail());
            }

            if(usuario.getSenha() != null &&
                    usuario.getSenha() != "" &&
                    usuario.getSenha().length() >= 8 &&
                    usuario.getSenha().matches(".*[*$#@&/%].*") &&
                    usuario.getSenha().matches(".*[A-Z].*") &&
                    usuario.getSenha().matches(".*[a-z].*") &&
                    usuario.getSenha().matches(".*[0-9].*")

            ){
                usuarioAtualizado.setSenha(usuario.getSenha());
            }

            usuarioRepository.save(usuarioAtualizado);

            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        if(usuarios.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarios);

    }

}
