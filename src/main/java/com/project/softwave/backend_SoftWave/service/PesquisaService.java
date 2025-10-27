package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.AdvogadosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuariosResponseDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.Role;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PesquisaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public Page<ClienteComProcessosResponseDTO> buscarClientesComProcessos(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Usuario> usuariosPage = usuarioRepository.findClientesComProcessos(pageable);

        List<ClienteComProcessosResponseDTO> clientes = usuariosPage.stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if (clientes.isEmpty()) {
            throw new NoContentException("Nenhuma pesquisa encontrada!");
        }

        return new PageImpl<>(clientes, pageable, usuariosPage.getTotalElements());
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorSetor(String setor) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorSetor(setor)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }
        return todos;
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorForo(String foro) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorForo(foro)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorAssunto(String assunto) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorAssunto(assunto)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorVara(String vara) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorVara(vara)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }

    public List<ClienteComProcessosResponseDTO> pesquisarPorTermo(String termo) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.buscarClientesPorTermo(termo)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }
        return todos;
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorDescricao(String descricao) {
        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorDescricao(descricao)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }
        return todos;
    }


    public Page<ClienteComProcessosResponseDTO> buscarClientesPorAdvogado(Integer advogadoId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Usuario advogado = usuarioRepository.findById(advogadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Advogado não encontrado!"));

        // Pega todos os processos que o advogado está vinculado
        List<Processo> processos = processoRepository.findByUsuariosContaining(advogado);

        // Conjunto para evitar duplicação de clientes
        Set<Usuario> clientesUnicos = new HashSet<>();

        for (Processo processo : processos) {
            for (Usuario usuario : processo.getUsuarios()) {
                // Adiciona apenas se não for o próprio advogado
                if (!usuario.getId().equals(advogadoId)) {
                    clientesUnicos.add(usuario);
                }
            }
        }

        // Remove advogados e mapeia para DTO
        List<ClienteComProcessosResponseDTO> todos = clientesUnicos.stream()
                .filter(u -> !(u instanceof AdvogadoFisico || u instanceof AdvogadoJuridico))
                .map(cliente -> ClienteComProcessosResponseDTO.toClienteComProcessosVinculadosAdvogadoResponseDTO(cliente, advogadoId))
                .toList();

        // Transformar a lista em uma página
        return new PageImpl<>(todos, pageable, todos.size());
    }

    public List<ClienteComProcessosResponseDTO> filtrarClientesPorStatus(String statusDescritivo) {
        String status;

        if (statusDescritivo.equalsIgnoreCase("ativo") || statusDescritivo.equalsIgnoreCase("ativos")) {
            status = "1";
        } else if (statusDescritivo.equalsIgnoreCase("inativo") || statusDescritivo.equalsIgnoreCase("inativos")) {
            status = "0";
        } else {
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        List<ClienteComProcessosResponseDTO> todos = usuarioRepository.findClientesComProcessosPorStatus(status)
                .stream()
                .map(ClienteComProcessosResponseDTO::toClienteComProcessosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }


    public List<AdvogadosResponseDTO> listarAdvogados() {
        List<AdvogadosResponseDTO> todos = usuarioRepository.findAdvogados()
                .stream()
                .map(AdvogadosResponseDTO::toAdvogadosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }

    public List<UsuariosResponseDTO> listarClientes() {
        List<UsuariosResponseDTO> todos = usuarioRepository.findClientes()
                .stream()
                .map(UsuariosResponseDTO::toUsuariosResponseDTO)
                .toList();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrada!");
        }

        return todos;
    }




}
