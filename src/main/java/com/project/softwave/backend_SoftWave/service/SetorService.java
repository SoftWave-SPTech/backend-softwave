package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.SetorDTO;
import com.project.softwave.backend_SoftWave.entity.Setor;
import com.project.softwave.backend_SoftWave.repository.SetorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    private final SetorRepository setorRepository;

    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    public SetorDTO criarSetor(SetorDTO setorDTO) {
        if (setorRepository.existsByNome(setorDTO.getNome())) {
            return null;
        }

        Setor setor = setorRepository.save(setorDTO.toEntity());
        return new SetorDTO(setor.getId(), setor.getNome(), setor.getDescricao());
    }


    public List<SetorDTO> listarSetores() {
        return setorRepository.findAll()
                .stream()
                .map(setor -> new SetorDTO(setor.getId(), setor.getNome(), setor.getDescricao()))
                .collect(Collectors.toList());
    }

    public SetorDTO buscarSetorPorId(Long id) {
        return setorRepository.findById(id)
                .map(setor -> new SetorDTO(setor.getId(), setor.getNome(), setor.getDescricao()))
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));
    }

    public SetorDTO atualizarSetor(Long id, SetorDTO setorDTO) {
        Setor setor = setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado."));

        setor.setNome(setorDTO.getNome());
        setor.setDescricao(setorDTO.getDescricao());

        setor = setorRepository.save(setor);
        return new SetorDTO(setor.getId(), setor.getNome(), setor.getDescricao());
    }

    public void deletarSetor(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new RuntimeException("Setor não encontrado.");
        }
        setorRepository.deleteById(id);
    }
}
