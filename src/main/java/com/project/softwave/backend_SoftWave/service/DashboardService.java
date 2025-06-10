package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.ProcessoService;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.DashResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private ProcessoService processoService;

    @Autowired
    private UsuarioService usuarioService;

    public DashResponseDTO dadosDash(){

        DashResponseDTO dadosDash = new DashResponseDTO();

        dadosDash.setClientesInativosAndAtivos(usuarioService.quantidadeClienteInativoAndInativo());
        dadosDash.setQtdProcessosPorSetor(processoService.qtdProcessosPorSetor());
        dadosDash.setQuantidadeAdvogados(usuarioService.quantidadeAdvogados());
        dadosDash.setQuantidadeProcessosTotais(processoService.quantidadeProcessos());
        dadosDash.setSetorComMaisProcessos(processoService.setorComMaisProcessos());
        dadosDash.setValorTotalProcessos(processoService.valorTotalProcessos());

        return dadosDash;
    }

}
