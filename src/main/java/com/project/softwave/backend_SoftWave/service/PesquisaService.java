package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.AdvogadosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuariosResponseDTO;
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

    public List<ClienteComProcessosResponseDTO> buscarClientesComProcessos() {
        return usuarioRepository.findClientesComProcessos()
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorSetor(String setor) {
        return usuarioRepository.findClientesComProcessosPorSetor(setor)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorForo(String foro) {
        return usuarioRepository.findClientesComProcessosPorForo(foro)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorAssunto(String assunto) {
        return usuarioRepository.findClientesComProcessosPorAssunto(assunto)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorVara(String vara) {
        return usuarioRepository.findClientesComProcessosPorVara(vara)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<ClienteComProcessosResponseDTO> pesquisarPorTermo(String termo) {
        return usuarioRepository.buscarClientesPorTermo(termo)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();
    }

    public List<AdvogadosResponseDTO> listarAdvogados() {
        return usuarioRepository.findAdvogados()
                .stream()
                .map(AdvogadosResponseDTO::toAdvogadosResponseDTO)
                .toList();
    }

    public List<UsuariosResponseDTO> listarClientes() {
        return usuarioRepository.findClientes()
                .stream()
                .map(UsuariosResponseDTO::toUsuariosResponseDTO)
                .toList();
    }
}
