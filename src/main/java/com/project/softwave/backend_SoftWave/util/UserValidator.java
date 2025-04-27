package com.project.softwave.backend_SoftWave.util;


import org.springframework.stereotype.Component;

@Component
public class UserValidator {


    public static Boolean validarSenha(String senha){
        if(senha.length() >= 8 &&
                senha.matches(".*[*$#@&/%_].*") &&
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





}
