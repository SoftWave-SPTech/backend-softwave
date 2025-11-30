package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.DashResponseDTO;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdPorSetorDTO;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdPorSetorDTOImpl;
import com.project.softwave.backend_SoftWave.dto.ProcessoSimplesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {
    @Autowired
    private ProcessoService processoService;

    @Autowired
    private RegistroFinanceiroService registroFinanceiroService;

    @Autowired
    private UsuarioService usuarioService;

    @Cacheable(value = "dashboard", key = "'dadosDash'", unless = "#result == null")
    public DashResponseDTO dadosDash(){

        DashResponseDTO dadosDash = new DashResponseDTO();

        dadosDash.setClientesInativosAndAtivos(usuarioService.quantidadeClienteInativoAndInativo());
        
        // Converter proxies do Spring Data JPA em objetos concretos para serialização
        List<QtdPorSetorDTO> qtdPorSetor = processoService.qtdProcessosPorSetor().stream()
                .map(dto -> new QtdPorSetorDTOImpl(dto.getSetor(), dto.getQtdProcessos()))
                .collect(Collectors.toList());
        dadosDash.setQtdProcessosPorSetor(qtdPorSetor);
        
        dadosDash.setQuantidadeAdvogados(usuarioService.quantidadeAdvogados());
        dadosDash.setQuantidadeClientes(usuarioService.quantidadeClientes());
        dadosDash.setQuantidadeProcessosTotais(processoService.quantidadeProcessos());
//        dadosDash.setSetorComMaisProcessos(processoService.setorComMaisProcessos());
        dadosDash.setReceitaUltimos6Meses(registroFinanceiroService.getReceitaUltimos6Meses());
        dadosDash.setValorTotalProcessos(processoService.valorTotalProcessos());
        dadosDash.setProcessosOrdenadosPorData(processoService.listarProcessosOrdenadosPorDataCriacao().stream()
                .map(ProcessoSimplesDTO::toProcessoSimplesDTO)
                .collect(Collectors.toList()));
        return dadosDash;
    }



}
