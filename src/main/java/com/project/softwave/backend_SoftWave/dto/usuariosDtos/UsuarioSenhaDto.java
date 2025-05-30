package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class UsuarioSenhaDto {

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Senha deve conter maiúsculas, minúsculas, números e caracteres especiais")
    private String senha;

    private String confirmaSenha;

    public UsuarioSenhaDto() {}

    public UsuarioSenhaDto(String email, String senha, String confirmaSenha) {
        this.email = email;
        this.senha = senha;
        this.confirmaSenha = confirmaSenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
}
