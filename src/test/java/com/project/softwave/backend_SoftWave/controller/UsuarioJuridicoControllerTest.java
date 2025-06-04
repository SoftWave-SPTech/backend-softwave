package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridico.UsuarioJuridicoRequestDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.service.UsuarioJuridicoService;
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
class UsuarioJuridicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioJuridicoService usuarioJuridicoService;

    @Test
    void cadastrarUsuarioJuridico_ComDadosValidos_DeveRetornar201() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        UsuarioJuridico usuarioSalvo = UsuarioJuridicoRequestDTO.toEntity(requestDTO);
        usuarioSalvo.setId(1);

        when(usuarioJuridicoService.cadastrar(any(UsuarioJuridico.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeFantasia").value(requestDTO.getNomeFantasia()))
                .andExpect(jsonPath("$.email").value(requestDTO.getEmail()))
                .andExpect(jsonPath("$.cnpj").value(requestDTO.getCnpj()))
                .andExpect(jsonPath("$.razaoSocial").value(requestDTO.getRazaoSocial()));
    }

    @Test
    void cadastrarUsuarioJuridico_ComEmailExistente_DeveRetornar409() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();

        when(usuarioJuridicoService.cadastrar(any(UsuarioJuridico.class)))
                .thenThrow(new EntidadeConflitoException("Email já cadastrado"));

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarUsuarioJuridico_ComCNPJExistente_DeveRetornar409() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();

        when(usuarioJuridicoService.cadastrar(any(UsuarioJuridico.class)))
                .thenThrow(new EntidadeConflitoException("CNPJ já cadastrado"));

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarUsuarioJuridico_ComEmailInvalido_DeveRetornar400() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setEmail("emailinvalido");

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioJuridico_ComCNPJInvalido_DeveRetornar400() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setCnpj("12345678901234"); // CNPJ inválido

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioJuridico_SemNomeFantasia_DeveRetornar400() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setNomeFantasia(null);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioJuridico_SemRazaoSocial_DeveRetornar400() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setRazaoSocial(null);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioJuridico_SemSenha_DeveRetornar400() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setSenha(null);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarUsuarioJuridico_SemComplemento_DeveRetornar201() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setComplemento(null);
        UsuarioJuridico usuarioSalvo = UsuarioJuridicoRequestDTO.toEntity(requestDTO);
        usuarioSalvo.setId(1);

        when(usuarioJuridicoService.cadastrar(any(UsuarioJuridico.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.complemento").doesNotExist());
    }

    @Test
    void cadastrarUsuarioJuridico_SemRepresentante_DeveRetornar201() throws Exception {
        UsuarioJuridicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setRepresentante(null);
        UsuarioJuridico usuarioSalvo = UsuarioJuridicoRequestDTO.toEntity(requestDTO);
        usuarioSalvo.setId(1);

        when(usuarioJuridicoService.cadastrar(any(UsuarioJuridico.class))).thenReturn(usuarioSalvo);

        mockMvc.perform(post("/usuarios-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.representante").doesNotExist());
    }

    private UsuarioJuridicoRequestDTO criarRequestValido() {
        UsuarioJuridicoRequestDTO requestDTO = new UsuarioJuridicoRequestDTO();
        requestDTO.setNomeFantasia("Empresa XPTO");
        requestDTO.setEmail("empresa@xpto.com");
        requestDTO.setSenha("Senha123@");
        requestDTO.setRazaoSocial("XPTO LTDA");
        requestDTO.setCnpj("17330474000102");
        requestDTO.setCep("03942030");
        requestDTO.setLogradouro("Rua das Flores");
        requestDTO.setBairro("Centro");
        requestDTO.setCidade("São Paulo");
        requestDTO.setTelefone("(11) 91234-5678");
        requestDTO.setComplemento("Sala 3, Edifício Central");
        requestDTO.setNumero("123");
        requestDTO.setRepresentante("João Carlos");
        return requestDTO;
    }
}