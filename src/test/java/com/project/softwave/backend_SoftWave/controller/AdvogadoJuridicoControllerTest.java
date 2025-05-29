package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoRequestDTO;
import com.project.softwave.backend_SoftWave.dto.AdvogadoJuridico.AdvogadoJuridicoResponseDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.service.AdvogadoJuridicoService;
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
class AdvogadoJuridicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void cadastrar_OABNegativa_DeveRetornar400() throws Exception {
        AdvogadoJuridicoRequestDTO request = criarRequestValido();
        request.setOab(-123);

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

    // Métodos auxiliares
    private AdvogadoJuridicoRequestDTO criarRequestValido() {
        AdvogadoJuridicoRequestDTO request = new AdvogadoJuridicoRequestDTO();
        request.setOab(567839);
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
}