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
import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;


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

    @Test
    void listar_ComUsuariosExistentes_DeveRetornar200() throws Exception {
        List<UsuarioFisico> usuarios = List.of(criarUsuarioValido());
        when(usuarioFisicoService.listar()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios-fisicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Maria Helena Costa"))
                .andExpect(jsonPath("$[0].email").value("maria.costa@gmail.com"))
                .andExpect(jsonPath("$[0].cpf").value("44868521845"));
    }

    @Test
    void listar_SemUsuarios_DeveRetornar204() throws Exception {
        when(usuarioFisicoService.listar())
                .thenThrow(new EntidadeNaoEncontradaException("Nenhum usuário encontrado"));

        mockMvc.perform(get("/usuarios-fisicos"))
                .andExpect(status().isNotFound());
    }

    @Test
    void atualizar_ComDadosValidos_DeveRetornar200() throws Exception {
        UsuarioFisicoAtualizacaoDTO dto = criarAtualizacaoValida();
        UsuarioFisico usuarioAtualizado = UsuarioFisicoAtualizacaoDTO.toEntity(dto);
        usuarioAtualizado.setId(1);

        when(usuarioFisicoService.atualizar(any(Integer.class), any(UsuarioFisicoAtualizacaoDTO.class)))
                .thenReturn(usuarioAtualizado);

        mockMvc.perform(put("/usuarios-fisicos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value(dto.getNome()))
                .andExpect(jsonPath("$.email").value(dto.getEmail()));
    }

    @Test
    void atualizar_ComIdInexistente_DeveRetornar404() throws Exception {
        UsuarioFisicoAtualizacaoDTO dto = criarAtualizacaoValida();

        when(usuarioFisicoService.atualizar(any(Integer.class), any(UsuarioFisicoAtualizacaoDTO.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário não encontrado com id: 999"));

        mockMvc.perform(put("/usuarios-fisicos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletar_ComIdExistente_DeveRetornar200() throws Exception {
        when(usuarioFisicoService.deletar(any(Integer.class))).thenReturn(true);

        mockMvc.perform(delete("/usuarios-fisicos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletar_ComIdInexistente_DeveRetornar404() throws Exception {
        when(usuarioFisicoService.deletar(any(Integer.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário não encontrado"));

        mockMvc.perform(delete("/usuarios-fisicos/999"))
                .andExpect(status().isNotFound());
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

    private UsuarioFisico criarUsuarioValido() {
        UsuarioFisico usuario = new UsuarioFisico();
        usuario.setId(1);
        usuario.setNome("Maria Helena Costa");
        usuario.setEmail("maria.costa@gmail.com");
        usuario.setSenha("Maria123@");
        usuario.setCpf("44868521845");
        usuario.setRg("12.345.678-9");
        usuario.setCep("03942030");
        usuario.setLogradouro("Rua das Acácias");
        usuario.setBairro("Vila Mariana");
        usuario.setCidade("São Paulo");
        usuario.setComplemento("Apto 12, Bloco A");
        usuario.setNumero("123");
        usuario.setTelefone("(11) 98877-6655");
        return usuario;
    }

    private UsuarioFisicoAtualizacaoDTO criarAtualizacaoValida() {
        UsuarioFisicoAtualizacaoDTO dto = new UsuarioFisicoAtualizacaoDTO();
        dto.setId(1);
        dto.setNome("Maria Helena Costa Atualizada");
        dto.setEmail("maria.costa.atualizada@gmail.com");
        dto.setCep("03942030");
        dto.setLogradouro("Rua das Acácias");
        dto.setBairro("Vila Mariana");
        dto.setCidade("São Paulo");
        dto.setComplemento("Apto 12, Bloco A");
        dto.setNumero("123");
        dto.setTelefone("(11) 98877-6655");
        return dto;
    }
}