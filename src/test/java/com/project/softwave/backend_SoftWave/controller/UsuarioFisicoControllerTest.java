package com.project.softwave.backend_SoftWave.controller;

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoRequestDTO;
//import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
//import com.project.softwave.backend_SoftWave.service.UsuarioFisicoService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;

import com.project.softwave.backend_SoftWave.dto.UsuarioFisico.UsuarioFisicoRequestDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.service.UsuarioFisicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UsuarioFisicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private UsuarioFisicoService usuarioFisicoService;

    @Test
    void cadastrarUsuarioFisico_ComDadosValidos_DeveRetornar201() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        UsuarioFisico usuarioSalvo = UsuarioFisicoRequestDTO.toEntity(requestDTO);
        usuarioSalvo.setId(1);

        when(usuarioFisicoService.cadastrar(any(UsuarioFisico.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(requestDTO.getNome()))
                .andExpect(jsonPath("$.email").value(requestDTO.getEmail()))
                .andExpect(jsonPath("$.cpf").value(requestDTO.getCpf()));
    }

    @Test
    void cadastrarUsuarioFisico_ComEmailExistente_DeveRetornar409() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();

        when(usuarioFisicoService.cadastrar(any(UsuarioFisico.class)))
                .thenThrow(new EntidadeConflitoException("Email já cadastrado"));

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarUsuarioFisico_ComCPFExistente_DeveRetornar409() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();

        when(usuarioFisicoService.cadastrar(any(UsuarioFisico.class)))
                .thenThrow(new EntidadeConflitoException("CPF já cadastrado"));

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarUsuarioFisico_ComEmailInvalido_DeveRetornar400() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setEmail("emailinvalido");

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioFisico_ComCPFInvalido_DeveRetornar400() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setCpf("12345678901"); // CPF inválido

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioFisico_SemNome_DeveRetornar400() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setNome(null);

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioFisico_SemSenha_DeveRetornar400() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setSenha(null);

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioFisico_SemTelefone_DeveRetornar400() throws Exception {
        UsuarioFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setTelefone(null);

        mockMvc.perform(post("/usuarios-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    private UsuarioFisicoRequestDTO criarRequestValido() {
        UsuarioFisicoRequestDTO requestDTO = new UsuarioFisicoRequestDTO();
        requestDTO.setNome("Maria Helena Costa");
        requestDTO.setEmail("maria.costa@gmail.com");
        requestDTO.setSenha("Maria123@");
        requestDTO.setCpf("44868521845");
        requestDTO.setRg("12.345.678-9");
        requestDTO.setCep("03942030");
        requestDTO.setLogradouro("Rua das Acácias");
        requestDTO.setBairro("Vila Mariana");
        requestDTO.setCidade("São Paulo");
        requestDTO.setTelefone("(11) 98877-6655");
        requestDTO.setComplemento("Apto 12, Bloco A");
        requestDTO.setNumero("123");
        return requestDTO;
    }
}