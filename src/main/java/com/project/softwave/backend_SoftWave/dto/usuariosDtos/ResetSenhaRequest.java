package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import jakarta.validation.constraints.Pattern;

public class ResetSenhaRequest {

    private String token;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Senha deve conter maiúsculas, minúsculas, números e caracteres especiais")
    private String novaSenha;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Senha deve conter maiúsculas, minúsculas, números e caracteres especiais")
    private String novaSenhaConfirma;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConfirma() {
        return novaSenhaConfirma;
    }

    public void setNovaSenhaConfirma(String novaSenhaConfirma) {
        this.novaSenhaConfirma = novaSenhaConfirma;
    }
}
