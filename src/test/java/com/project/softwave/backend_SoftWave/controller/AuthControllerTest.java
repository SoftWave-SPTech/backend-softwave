package com.project.softwave.backend_SoftWave.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.ResetSenhaRequest;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioSenhaDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioTokenDTO;
import com.project.softwave.backend_SoftWave.exception.LoginIncorretoException;
import com.project.softwave.backend_SoftWave.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private UsuarioService usuarioService;

    @Test
    void login_ComCredenciaisValidas_DeveRetornar200() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("teste@email.com", "senha123");
        UsuarioTokenDTO tokenDto = new UsuarioTokenDTO();
        tokenDto.setToken("jwt-token-teste");
        tokenDto.setRole("ROLE_USER");
        tokenDto.setNome("Usuário Teste");
        
        when(usuarioService.autenticar(any(UsuarioLoginDto.class))).thenReturn(tokenDto);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-teste"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"))
                .andExpect(jsonPath("$.nome").value("Usuário Teste"));
    }

    @Test
    void login_ComCredenciaisInvalidas_DeveRetornar401() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("teste@email.com", "senha_errada");
        
        when(usuarioService.autenticar(any(UsuarioLoginDto.class)))
                .thenThrow(new LoginIncorretoException("Credenciais inválidas"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void primeiroAcesso_ComCredenciaisValidas_DeveRetornar200() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("teste@email.com", "senha123");
        
        when(usuarioService.primeiroAcesso(any(UsuarioLoginDto.class))).thenReturn(loginDto);

        mockMvc.perform(post("/auth/primeiro-acesso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("teste@email.com"));
    }

    @Test
    void primeiroAcesso_ComCredenciaisInvalidas_DeveRetornar401() throws Exception {
        UsuarioLoginDto loginDto = new UsuarioLoginDto("teste@email.com", "senha_errada");
        
        when(usuarioService.primeiroAcesso(any(UsuarioLoginDto.class)))
                .thenThrow(new LoginIncorretoException("Credenciais inválidas"));

        mockMvc.perform(post("/auth/primeiro-acesso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDto)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void cadastrarSenha_ComDadosValidos_DeveRetornar200() throws Exception {
        UsuarioSenhaDto senhaDto = new UsuarioSenhaDto();
        senhaDto.setEmail("teste@email.com");
        senhaDto.setNovaSenha("novaSenha@123");
        senhaDto.setNovaSenhaConfirma("novaSenha@123");
        
        doNothing().when(usuarioService).cadastrarSenha(any(UsuarioSenhaDto.class));

        mockMvc.perform(patch("/auth/cadastrar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(senhaDto)))
                .andExpect(status().isOk());
    }

    @Test
    void solicitarResetSenha_ComEmailValido_DeveRetornar200() throws Exception {
        String email = "teste@email.com";
        
        doNothing().when(usuarioService).solicitarResetSenha(email);

        mockMvc.perform(post("/auth/solicitar-reset-senha")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().string("Email enviado com sucesso"));
    }

    @Test
    void resetarSenha_ComDadosValidos_DeveRetornar200() throws Exception {
        ResetSenhaRequest request = new ResetSenhaRequest();
        request.setToken("token-teste");
        request.setNovaSenha("novaSenha@123");
        request.setNovaSenhaConfirma("novaSenha@123");
        
        doNothing().when(usuarioService).resetarSenha(
                request.getToken(),
                request.getNovaSenha(),
                request.getNovaSenhaConfirma()
        );

        mockMvc.perform(post("/auth/resetar-senha")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
} 