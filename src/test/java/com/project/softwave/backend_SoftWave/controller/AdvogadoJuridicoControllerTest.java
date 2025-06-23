package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoRequestDTO;
import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoResponseDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioJuridicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.service.AdvogadoJuridicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
class AdvogadoJuridicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private AdvogadoJuridicoService service;

    // Teste de sucesso
    @Test
    void cadastrar_ComDadosValidos_DeveRetornar201() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        AdvogadoJuridicoResponseDTO response = criarResponseValido();

        when(service.cadastrar(any(AdvogadoJuridico.class)))
                .thenReturn(AdvogadoJuridicoResponseDTO.toEntity(response));

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.oab").value(567839))
                .andExpect(jsonPath("$.email").value("advocacia.exemplo@empresa.com"))
                .andExpect(jsonPath("$.cnpj").value("87504726000140"));
    }

    // Teste de campo opcional
    @Test
    void cadastrar_SemComplemento_DeveRetornar201() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        request.setComplemento(null);
        AdvogadoJuridicoResponseDTO response = criarResponseValido();
        response.setComplemento(null);

        when(service.cadastrar(any(AdvogadoJuridico.class)))
                .thenReturn(AdvogadoJuridicoResponseDTO.toEntity(response));

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.complemento").doesNotExist());
    }

    // Testes de validação
    @Test
    void cadastrar_SemOAB_DeveRetornar400() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        request.setOab(null);

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrar_OABMenor6Digitos_DeveRetornar400() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        request.setOab("3123");

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrar_EmailInvalido_DeveRetornar400() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        request.setEmail("emailinvalido");

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    // Testes de conflito
    @Test
    void cadastrar_EmailExistente_DeveRetornar409() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();

        when(service.cadastrar(any(AdvogadoJuridico.class)))
                .thenThrow(new EntidadeConflitoException("Email já cadastrado"));

        mockMvc.perform(post("/advogados-juridicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    void listar_ComAdvogadosExistentes_DeveRetornar200() throws Exception {
        List<AdvogadoJuridico> advogados = List.of(criarAdvogadoValido());
        when(service.listar()).thenReturn(advogados);

        mockMvc.perform(get("/advogados-juridicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeFantasia").value("Advocacia Pereira & Associados"))
                .andExpect(jsonPath("$[0].email").value("advocacia.exemplo@empresa.com"))
                .andExpect(jsonPath("$[0].oab").value(567839));
    }

    @Test
    void listar_SemAdvogados_DeveRetornar204() throws Exception {
        when(service.listar())
                .thenThrow(new EntidadeNaoEncontradaException("Nenhum advogado jurídico encontrado."));

        mockMvc.perform(get("/advogados-juridicos"))
                .andExpect(status().isNotFound());
    }

    @Test
    void atualizar_ComDadosValidos_DeveRetornar200() throws Exception {
        UsuarioJuridicoAtualizacaoDTO dto = criarAtualizacaoValida();
        AdvogadoJuridico advogadoExistente = criarAdvogadoValido();
        AdvogadoJuridico advogadoAtualizado = UsuarioJuridicoAtualizacaoDTO.toEntityAdvogado(dto,advogadoExistente);
        advogadoAtualizado.setId(1);

        when(service.atualizar(any(Integer.class), any(UsuarioJuridicoAtualizacaoDTO.class)))
                .thenReturn(advogadoAtualizado);

        mockMvc.perform(put("/advogados-juridicos/1")
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

        when(service.atualizar(any(Integer.class), any(UsuarioJuridicoAtualizacaoDTO.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Advogado não encontrado com id: 999"));

        mockMvc.perform(put("/advogados-juridicos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletar_ComIdExistente_DeveRetornar200() throws Exception {
        when(service.deletar(any(Integer.class))).thenReturn(true);

        mockMvc.perform(delete("/advogados-juridicos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletar_ComIdInexistente_DeveRetornar404() throws Exception {
        when(service.deletar(any(Integer.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Advogado jurídico não encontrado"));

        mockMvc.perform(delete("/advogados-juridicos/999"))
                .andExpect(status().isNotFound());
    }

    // Métodos auxiliares
    private AdvogadoJuridicoRequestDTO criarRequestValido() {
        AdvogadoJuridicoRequestDTO request = new AdvogadoJuridicoRequestDTO();
        request.setOab("567839");
        request.setEmail("advocacia.exemplo@empresa.com");
        request.setSenha("SenhaForte@123");
        request.setNomeFantasia("Advocacia Pereira & Associados");
        request.setRazaoSocial("Pereira e Associados Sociedade de Advogados LTDA");
        request.setCnpj("87504726000140");
        request.setCep("01310930");
        request.setLogradouro("Avenida Paulista");
        request.setBairro("Bela Vista");
        request.setCidade("São Paulo");
        request.setComplemento("Sala 801, Torre Oeste");
        request.setNumero("123");
        request.setTelefone("(11) 97654-3210");
        request.setRepresentante("João Carlos");
        return request;
    }

    private AdvogadoJuridicoResponseDTO criarResponseValido() {
        AdvogadoJuridicoResponseDTO response = new AdvogadoJuridicoResponseDTO();
        response.setId(1);
        response.setOab(567839);
        response.setEmail("advocacia.exemplo@empresa.com");
        response.setNomeFantasia("Advocacia Pereira & Associados");
        response.setRazaoSocial("Pereira e Associados Sociedade de Advogados LTDA");
        response.setCnpj("87504726000140");
        response.setCep("01310930");
        response.setLogradouro("Avenida Paulista");
        response.setBairro("Bela Vista");
        response.setCidade("São Paulo");
        response.setComplemento("Sala 801, Torre Oeste");
        response.setNumero("123");
        response.setTelefone("(11) 97654-3210");
        response.setRepresentante("João Carlos");
        return response;
    }

    private AdvogadoJuridico criarAdvogadoValido() {
        AdvogadoJuridico advogado = new AdvogadoJuridico();
        advogado.setId(1);
        advogado.setOab(567839);
        advogado.setEmail("advocacia.exemplo@empresa.com");
        advogado.setSenha("SenhaForte@123");
        advogado.setNomeFantasia("Advocacia Pereira & Associados");
        advogado.setRazaoSocial("Pereira e Associados Sociedade de Advogados LTDA");
        advogado.setCnpj("87504726000140");
        advogado.setCep("01310930");
        advogado.setLogradouro("Avenida Paulista");
        advogado.setBairro("Bela Vista");
        advogado.setCidade("São Paulo");
        advogado.setComplemento("Sala 801, Torre Oeste");
        advogado.setNumero("123");
        advogado.setTelefone("(11) 97654-3210");
        advogado.setRepresentante("João Carlos");
        return advogado;
    }

    private UsuarioJuridicoAtualizacaoDTO criarAtualizacaoValida() {
        UsuarioJuridicoAtualizacaoDTO dto = new UsuarioJuridicoAtualizacaoDTO();
        dto.setId(1);
        dto.setNomeFantasia("Advocacia Pereira & Associados Atualizada");
        dto.setEmail("advocacia.atualizada@empresa.com");
        dto.setRazaoSocial("Pereira e Associados Sociedade de Advogados LTDA - Atualizada");
        dto.setCep("01310930");
        dto.setLogradouro("Avenida Paulista");
        dto.setBairro("Bela Vista");
        dto.setCidade("São Paulo");
        dto.setComplemento("Sala 801, Torre Oeste");
        dto.setNumero("123");
        dto.setTelefone("(11) 97654-3210");
        dto.setRepresentante("João Carlos");
        return dto;
    }
}