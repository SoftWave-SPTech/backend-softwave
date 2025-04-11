package com.project.softwave.backend_SoftWave.util;


import org.springframework.stereotype.Component;

@Component
public class UserValidator {


    public static Boolean validarSenha(String senha){
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

    public static Boolean validarCamposVaziosJuridico(String nomeFantasia, String razaoSocial, String cnpj){
        if(
                nomeFantasia != null &&
                        nomeFantasia != "" &&
                        razaoSocial != null &&
                        razaoSocial != "" &&
                        cnpj != null &&
                        cnpj != ""
        ){
            return true;
        }
        return false;
    }

    public static Boolean validarCamposVaziosFisico(String nome, String rg){

        if(
                nome != null &&
                        nome != "" &&
                        rg != null &&
                        rg != ""
        ){
            return true;
        }
        return false;
    }

    //Validar este campo melhor depois
    public static Boolean validarCpf(String cpf){

        return true;
    }


}
