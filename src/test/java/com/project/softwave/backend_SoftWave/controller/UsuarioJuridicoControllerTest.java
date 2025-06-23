package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.UsuarioJuridico.UsuarioJuridicoRequestDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioJuridicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
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

    @Test
    void listar_ComUsuariosExistentes_DeveRetornar200() throws Exception {
        List<UsuarioJuridico> usuarios = List.of(criarUsuarioValido());
        when(usuarioJuridicoService.listar()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios-juridicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeFantasia").value("Empresa XPTO"))
                .andExpect(jsonPath("$[0].email").value("empresa@xpto.com"))
                .andExpect(jsonPath("$[0].cnpj").value("17330474000102"));
    }

    @Test
    void listar_SemUsuarios_DeveRetornar204() throws Exception {
        when(usuarioJuridicoService.listar())
                .thenThrow(new EntidadeNaoEncontradaException("Nenhum usuário encontrado"));

        mockMvc.perform(get("/usuarios-juridicos"))
                .andExpect(status().isNotFound());
    }

    @Test
    void atualizar_ComDadosValidos_DeveRetornar200() throws Exception {
        UsuarioJuridicoAtualizacaoDTO dto = criarAtualizacaoValida();
        UsuarioJuridico usuarioAtualizado = UsuarioJuridicoAtualizacaoDTO.toEntity(dto);
        usuarioAtualizado.setId(1);

        when(usuarioJuridicoService.atualizar(any(Integer.class), any(UsuarioJuridicoAtualizacaoDTO.class)))
                .thenReturn(usuarioAtualizado);

        mockMvc.perform(put("/usuarios-juridicos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeFantasia").value(dto.getNomeFantasia()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()));
    }

    @Test
    void atualizar_ComIdInexistente_DeveRetornar404() throws Exception {
        UsuarioJuridicoAtualizacaoDTO dto = criarAtualizacaoValida();

        when(usuarioJuridicoService.atualizar(any(Integer.class), any(UsuarioJuridicoAtualizacaoDTO.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário não encontrado com id: 999"));

        mockMvc.perform(put("/usuarios-juridicos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletar_ComIdExistente_DeveRetornar200() throws Exception {
        when(usuarioJuridicoService.deletar(any(Integer.class))).thenReturn(true);

        mockMvc.perform(delete("/usuarios-juridicos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletar_ComIdInexistente_DeveRetornar404() throws Exception {
        when(usuarioJuridicoService.deletar(any(Integer.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário não encontrado"));

        mockMvc.perform(delete("/usuarios-juridicos/999"))
                .andExpect(status().isNotFound());
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

    private UsuarioJuridico criarUsuarioValido() {
        UsuarioJuridico usuario = new UsuarioJuridico();
        usuario.setId(1);
        usuario.setNomeFantasia("Empresa XPTO");
        usuario.setEmail("empresa@xpto.com");
        usuario.setSenha("Senha123@");
        usuario.setRazaoSocial("XPTO LTDA");
        usuario.setCnpj("17330474000102");
        usuario.setCep("03942030");
        usuario.setLogradouro("Rua das Flores");
        usuario.setBairro("Centro");
        usuario.setCidade("São Paulo");
        usuario.setComplemento("Sala 3, Edifício Central");
        usuario.setNumero("123");
        usuario.setTelefone("(11) 91234-5678");
        usuario.setRepresentante("João Carlos");
        return usuario;
    }

    private UsuarioJuridicoAtualizacaoDTO criarAtualizacaoValida() {
        UsuarioJuridicoAtualizacaoDTO dto = new UsuarioJuridicoAtualizacaoDTO();
        dto.setId(1);
        dto.setNomeFantasia("Empresa XPTO Atualizada");
        dto.setEmail("empresa.atualizada@xpto.com");
        dto.setRazaoSocial("XPTO LTDA - Atualizada");
        dto.setCep("03942030");
        dto.setLogradouro("Rua das Flores");
        dto.setBairro("Centro");
        dto.setCidade("São Paulo");
        dto.setComplemento("Sala 3, Edifício Central");
        dto.setNumero("123");
        dto.setTelefone("(11) 91234-5678");
        dto.setRepresentante("João Carlos");
        return dto;
    }
}