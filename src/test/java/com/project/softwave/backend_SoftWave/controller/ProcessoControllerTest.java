package com.project.softwave.backend_SoftWave.controller;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.CadastroProcessoDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoGrau1API;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.ProcessoSimplesDTO;
import com.project.softwave.backend_SoftWave.dto.RemoverUsuarioProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.VincularUsuariosProcessoDTO;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProcessoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("removal")
    @MockBean
    private ProcessoService processoService;

    @SuppressWarnings("removal")
    @MockBean
    private ProcessoGrau1API processoGrau1API;

    @Test
    void vincularUsuarios_ComDadosValidos_DeveRetornar200() throws Exception {
        VincularUsuariosProcessoDTO dto = new VincularUsuariosProcessoDTO(1, Arrays.asList(1, 2));
        
        doNothing().when(processoService).vincularUsuariosAoProcesso(any(VincularUsuariosProcessoDTO.class));

        mockMvc.perform(post("/processos/vincular-usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuários vinculados com sucesso ao processo."));
    }

    @Test
    void vincularUsuarios_ComProcessoInexistente_DeveRetornar404() throws Exception {
        VincularUsuariosProcessoDTO dto = new VincularUsuariosProcessoDTO(999, Arrays.asList(1, 2));
        
        doThrow(new EntidadeNaoEncontradaException("Processo não encontrado"))
                .when(processoService).vincularUsuariosAoProcesso(any(VincularUsuariosProcessoDTO.class));

        mockMvc.perform(post("/processos/vincular-usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void removerUsuario_ComDadosValidos_DeveRetornar200() throws Exception {
        RemoverUsuarioProcessoDTO dto = new RemoverUsuarioProcessoDTO(1, 1);
        
        doNothing().when(processoService).removerUsuarioDoProcesso(any(RemoverUsuarioProcessoDTO.class));

        mockMvc.perform(delete("/processos/remover-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário removido do processo com sucesso."));
    }

    @Test
    void removerUsuario_ComProcessoInexistente_DeveRetornar404() throws Exception {
        RemoverUsuarioProcessoDTO dto = new RemoverUsuarioProcessoDTO(999, 1);
        
        doThrow(new EntidadeNaoEncontradaException("Processo não encontrado"))
                .when(processoService).removerUsuarioDoProcesso(any(RemoverUsuarioProcessoDTO.class));

        mockMvc.perform(delete("/processos/remover-usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    private CadastroProcessoDTO criarRequestValido() {
        CadastroProcessoDTO dto = new CadastroProcessoDTO();
        dto.setNumeroProcesso("0000123-50.2025.8.26.0542");
        dto.setDescricao("Processo de teste");
        dto.setUsuarios(Arrays.asList(1, 2));
        return dto;
    }

    private Processo criarProcessoValido() {
        Processo processo = new Processo();
        processo.setId(1);
        processo.setNumeroProcesso("0000123-50.2025.8.26.0542");
        processo.setClasse("Ação Penal");
        processo.setAssunto("Furto Qualificado");
        processo.setForo("Foro Central Criminal");
        processo.setVara("2ª Vara Criminal");
        processo.setJuiz("Dr. Carlos Silva");
        processo.setDistribuicao("01/01/2024 às 10:00");
        processo.setControle("2024/001234");
        processo.setArea("Criminal");
        processo.setValorAcao("R$ 50.000,00");
        processo.setNormalizadoValorAcao(50000.00);
        processo.setAutor("Ministério Público");
        processo.setExecutado("João da Silva");
        processo.setRequerente("MP");
        processo.setRequerido("João da Silva");
        return processo;
    }
} 