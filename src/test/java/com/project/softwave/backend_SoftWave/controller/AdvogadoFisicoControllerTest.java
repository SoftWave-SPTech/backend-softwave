package com.project.softwave.backend_SoftWave.controller;
import com.project.softwave.backend_SoftWave.dto.AdvogadoFisico.AdvogadoFisicoRequestDTO;
import com.project.softwave.backend_SoftWave.dto.UsuarioFisicoAtualizacaoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

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

    @Test
    void listar_ComAdvogadosExistentes_DeveRetornar200() throws Exception {
        List<AdvogadoFisico> advogados = List.of(criarAdvogadoValido());
        when(advogadoFisicoService.listar()).thenReturn(advogados);

        mockMvc.perform(get("/advogados-fisicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João Carlos Mendes"))
                .andExpect(jsonPath("$[0].email").value("joao.mendes@advocacia.com"))
                .andExpect(jsonPath("$[0].oab").value(123456));
    }

    @Test
    void listar_SemAdvogados_DeveRetornar204() throws Exception {
        when(advogadoFisicoService.listar())
                .thenThrow(new EntidadeNaoEncontradaException("Nenhum advogado físico encontrado."));

        mockMvc.perform(get("/advogados-fisicos"))
                .andExpect(status().isNotFound());
    }

    @Test
    void atualizar_ComDadosValidos_DeveRetornar200() throws Exception {
        UsuarioFisicoAtualizacaoDTO dto = criarAtualizacaoValida();
        AdvogadoFisico advogadoAtualizado = UsuarioFisicoAtualizacaoDTO.toEntityAdvogado(dto);
        advogadoAtualizado.setId(1);

        when(advogadoFisicoService.atualizar(any(Integer.class), any(UsuarioFisicoAtualizacaoDTO.class)))
                .thenReturn(advogadoAtualizado);

        mockMvc.perform(put("/advogados-fisicos/1")
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

        when(advogadoFisicoService.atualizar(any(Integer.class), any(UsuarioFisicoAtualizacaoDTO.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Advogado não encontrado com id: 999"));

        mockMvc.perform(put("/advogados-fisicos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletar_ComIdExistente_DeveRetornar200() throws Exception {
        when(advogadoFisicoService.deletar(any(Integer.class))).thenReturn(true);

        mockMvc.perform(delete("/advogados-fisicos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletar_ComIdInexistente_DeveRetornar404() throws Exception {
        when(advogadoFisicoService.deletar(any(Integer.class)))
                .thenThrow(new EntidadeNaoEncontradaException("Usuário não encontrado"));

        mockMvc.perform(delete("/advogados-fisicos/999"))
                .andExpect(status().isNotFound());
    }

    private AdvogadoFisicoRequestDTO criarRequestValido() {
        AdvogadoFisicoRequestDTO requestDTO = new AdvogadoFisicoRequestDTO();
        requestDTO.setOab("123456");
        requestDTO.setNome("João Carlos Mendes");
        requestDTO.setEmail("joao.mendes@advocacia.com");
        requestDTO.setTokenPrimeiroAcesso("SenhaSegura@2024");
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

    private AdvogadoFisico criarAdvogadoValido() {
        AdvogadoFisico advogado = new AdvogadoFisico();
        advogado.setId(1);
        advogado.setOab(123456);
        advogado.setNome("João Carlos Mendes");
        advogado.setEmail("joao.mendes@advocacia.com");
        advogado.setSenha("SenhaSegura@2024");
        advogado.setCpf("90869258044");
        advogado.setRg("123456789");
        advogado.setCep("04567000");
        advogado.setLogradouro("Rua das Laranjeiras");
        advogado.setBairro("Jardim Paulista");
        advogado.setCidade("São Paulo");
        advogado.setComplemento("Apto 102, Bloco B");
        advogado.setNumero("123");
        advogado.setTelefone("(11) 98765-4321");
        return advogado;
    }

    private UsuarioFisicoAtualizacaoDTO criarAtualizacaoValida() {
        UsuarioFisicoAtualizacaoDTO dto = new UsuarioFisicoAtualizacaoDTO();
        dto.setId(1);
        dto.setNome("João Carlos Mendes Atualizado");
        dto.setEmail("joao.mendes.atualizado@advocacia.com");
        dto.setCep("04567000");
        dto.setLogradouro("Rua das Laranjeiras");
        dto.setBairro("Jardim Paulista");
        dto.setCidade("São Paulo");
        dto.setComplemento("Apto 102, Bloco B");
        dto.setNumero("123");
        dto.setTelefone("(11) 98765-4321");
        return dto;
    }
}