package com.project.softwave.backend_SoftWave.controller;
import com.project.softwave.backend_SoftWave.dto.AdvogadoFisico.AdvogadoFisicoRequestDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.service.AdvogadoFisicoService;
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
class AdvogadoFisicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private AdvogadoFisicoService advogadoFisicoService;

    @Test
    void cadastrarAdvogado_ComDadosValidos_DeveRetornar201() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        AdvogadoFisico advogadoSalvo = AdvogadoFisicoRequestDTO.toEntity(requestDTO);
        advogadoSalvo.setId(1);

        when(advogadoFisicoService.cadastrar(any(AdvogadoFisico.class))).thenReturn(advogadoSalvo);

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.oab").value(requestDTO.getOab()))
                .andExpect(jsonPath("$.nome").value(requestDTO.getNome()))
                .andExpect(jsonPath("$.email").value(requestDTO.getEmail()))
                .andExpect(jsonPath("$.cpf").value(requestDTO.getCpf()));
    }

    @Test
    void cadastrarAdvogado_ComEmailExistente_DeveRetornar409() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();

        when(advogadoFisicoService.cadastrar(any(AdvogadoFisico.class)))
                .thenThrow(new EntidadeConflitoException("Email já cadastrado"));

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarAdvogado_ComCPFExistente_DeveRetornar409() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();

        when(advogadoFisicoService.cadastrar(any(AdvogadoFisico.class)))
                .thenThrow(new EntidadeConflitoException("CPF já cadastrado"));

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarAdvogado_ComOABExistente_DeveRetornar409() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();

        when(advogadoFisicoService.cadastrar(any(AdvogadoFisico.class)))
                .thenThrow(new EntidadeConflitoException("OAB já cadastrada"));

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    void cadastrarAdvogado_ComEmailInvalido_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setEmail("emailinvalido");

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_ComCPFInvalido_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setCpf("123.456.789-00"); // CPF inválido

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_ComOABNula_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setOab(null);

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_ComOABMaiorQue6Digitos_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setOab("1123456");

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_SemNome_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setNome(null);

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_SemSenha_DeveRetornar400() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setSenha(null);

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void cadastrarAdvogado_SemComplemento_DeveRetornar201() throws Exception {
        AdvogadoFisicoRequestDTO requestDTO = criarRequestValido();
        requestDTO.setComplemento(null);
        AdvogadoFisico advogadoSalvo = AdvogadoFisicoRequestDTO.toEntity(requestDTO);
        advogadoSalvo.setId(1);

        when(advogadoFisicoService.cadastrar(any(AdvogadoFisico.class))).thenReturn(advogadoSalvo);

        mockMvc.perform(post("/advogados-fisicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.complemento").doesNotExist());
    }

    private AdvogadoFisicoRequestDTO criarRequestValido() {
        AdvogadoFisicoRequestDTO requestDTO = new AdvogadoFisicoRequestDTO();
        requestDTO.setOab("123456");
        requestDTO.setNome("João Carlos Mendes");
        requestDTO.setEmail("joao.mendes@advocacia.com");
        requestDTO.setSenha("SenhaSegura@2024");
        requestDTO.setCpf("90869258044");
        requestDTO.setRg("123456789");
        requestDTO.setCep("04567000");
        requestDTO.setLogradouro("Rua das Laranjeiras");
        requestDTO.setBairro("Jardim Paulista");
        requestDTO.setCidade("São Paulo");
        requestDTO.setComplemento("Apto 102, Bloco B");
        requestDTO.setNumero("123");
        requestDTO.setTelefone("(11) 98765-4321");
        return requestDTO;
    }
}