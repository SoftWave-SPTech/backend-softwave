package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.ProcessoDTO;
import com.project.softwave.backend_SoftWave.entity.Processo;
import com.project.softwave.backend_SoftWave.entity.Setor;
import com.project.softwave.backend_SoftWave.exception.ProcessoNotFoundException;
import com.project.softwave.backend_SoftWave.exception.SetorNotFoundException;
import com.project.softwave.backend_SoftWave.repository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private SetorRepository setorRepository;

    public ProcessoDTO criarProcesso(ProcessoDTO dto) {
        Optional<Setor> setorOptional = setorRepository.findById(dto.getSetorId());

        if (setorOptional.isEmpty()) {
            throw new SetorNotFoundException("Setor com ID " + dto.getSetorId() + " não encontrado.");
        }

        Setor setor = setorOptional.get();

        Processo processo = new Processo(dto.getNumero(), dto.getNome(), dto.getDescricao(), setor);
        processo = processoRepository.save(processo);

        return new ProcessoDTO(processo.getId(), processo.getNumero(), processo.getNome(), processo.getDescricao(), setor.getId());
    }


    public List<ProcessoDTO> listarProcessos() {
        return processoRepository.findAll()
                .stream()
                .map(processo -> new ProcessoDTO(processo.getId(), processo.getNumero(), processo.getNome(), processo.getDescricao(), processo.getSetor().getId()))
                .collect(Collectors.toList());
    }

    public ProcessoDTO buscarProcessoPorId(Long id) {
        return processoRepository.findById(id)
                .map(processo -> new ProcessoDTO(processo.getId(), processo.getNumero(), processo.getNome(), processo.getDescricao(), processo.getSetor().getId()))
                .orElseThrow(() -> new ProcessoNotFoundException("Processo com ID " + id + " não encontrado."));    }

    public ProcessoDTO buscarProcessoPorNumero(String numero) {
        Optional<Processo> processoOptional = processoRepository.findByNumero(numero);

        return processoOptional
                .map(processo -> new ProcessoDTO(
                        processo.getId(),
                        processo.getNumero(),
                        processo.getNome(),
                        processo.getDescricao(),
                        processo.getSetor().getId()
                ))
                .orElse(null);
    }


    public ProcessoDTO atualizarProcesso(Long id, ProcessoDTO dto) {
        Optional<Processo> processoOptional = processoRepository.findById(id);
        if (processoOptional.isEmpty()) {
            return null;
        }

        Processo processo = processoOptional.get();
        processo.setNumero(dto.getNumero());
        processo.setNome(dto.getNome());
        processo.setDescricao(dto.getDescricao());

        processoRepository.save(processo);
        return new ProcessoDTO(processo.getId(), processo.getNumero(), processo.getNome(), processo.getDescricao(), processo.getSetor().getId());
    }

    public boolean deletarProcesso(Long id) {
        if (!processoRepository.existsById(id)) {
            return false;
        }

        processoRepository.deleteById(id);
        return true;
    }
}
