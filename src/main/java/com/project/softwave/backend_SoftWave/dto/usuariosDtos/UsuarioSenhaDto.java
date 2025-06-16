package com.project.softwave.backend_SoftWave.dto.usuariosDtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "DTO para cadastro e atualização de senha")
public class UsuarioSenhaDto {

    @Schema(description = "Email do usuário", example = "usuario@exemplo.com")
    @NotBlank(message = "O email é obrigatório")
    private String email;

    @Schema(description = "Nova senha do usuário", example = "NovaSenha@123")
    @NotBlank(message = "A nova senha é obrigatória")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "A senha deve conter pelo menos 8 caracteres, incluindo letras maiúsculas, minúsculas, números e caracteres especiais")
    private String novaSenha;

    @Schema(description = "Confirmação da nova senha", example = "NovaSenha@123")
    @NotBlank(message = "A confirmação da nova senha é obrigatória")
    private String novaSenhaConfirma;

    public UsuarioSenhaDto() {
    }

    public UsuarioSenhaDto(String email, String novaSenha, String novaSenhaConfirma) {
        this.email = email;
        this.novaSenha = novaSenha;
        this.novaSenhaConfirma = novaSenhaConfirma;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
