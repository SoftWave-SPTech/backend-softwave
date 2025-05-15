package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PesquisaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public List<Processo> pesquisarPorTermo(String termo) {
        if (termo == null || termo.isBlank()) {
            return processoRepository.findAll();
        }
        return processoRepository.pesquisarPorUsuarioOuNumero(termo);
    }

    public List<ClienteComProcessosResponseDTO> buscarClientesComProcessos() {
        return usuarioRepository.findClientesComProcessos()
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

//    public List<ClienteComProcessosResponseDTO> pesquisarPorTermo(String termo) {
//        if (termo == null || termo.isBlank()) {
//            return processoRepository.findAll();
//        }
//        return processoRepository.pesquisarPorUsuarioOuNumero(termo);
//    }
}
