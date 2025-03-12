package com.project.softwave.backend_SoftWave.util;

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

    public static Boolean validarEmail(String email){
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
    public static Boolean validarCamposVaziosJuridico(String nomeFantasia, String razaoSocial, String cnpj){
        return true;
    }
    // validar se existe rg igual na base
    public static Boolean validarCamposVaziosFisico(String nome, String rg){
        return true;
    }

    //Validar este campo melhor depois e validar se já existe cpf na base
    public static Boolean validarCpf(String cpf){
        return true;
    }


}
