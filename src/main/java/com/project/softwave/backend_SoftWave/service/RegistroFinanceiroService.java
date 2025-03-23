package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.RegistroFinanceiroDTO;
import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import com.project.softwave.backend_SoftWave.repository.RegistroFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroFinanceiroService {

    @Autowired
    private RegistroFinanceiroRepository registroFinanceiroRepository;

    public RegistroFinanceiroDTO criarRegistro(RegistroFinanceiroDTO dto) {
        RegistroFinanceiro registro = new RegistroFinanceiro();
        registro.setDescricao(dto.getDescricao());
        registro.setValor(dto.getValor());
        registro.setData(dto.getData());

        registro = registroFinanceiroRepository.save(registro);
        dto.setId(registro.getId());
        return dto;
    }

    public List<RegistroFinanceiroDTO> listarRegistros() {
        return registroFinanceiroRepository.findAll()
                .stream()
                .map(registro -> new RegistroFinanceiroDTO(
                        registro.getId() ,
                        registro.getDescricao(),
                        registro.getValor(),
                        registro.getData()))
                .collect(Collectors.toList());
    }

    public RegistroFinanceiroDTO buscarRegistroPorId(Long id) {
        Optional<RegistroFinanceiro> registroOptional = registroFinanceiroRepository.findById(id);
        return registroOptional
                .map(registro -> new RegistroFinanceiroDTO(
                        registro.getId(),
                        registro.getDescricao(),
                        registro.getValor(),
                        registro.getData()))
                .orElse(null);
    }

    public RegistroFinanceiroDTO atualizarRegistro(Long id, RegistroFinanceiroDTO dto) {
        Optional<RegistroFinanceiro> registroOptional = registroFinanceiroRepository.findById(id);
        if (registroOptional.isEmpty()) {
            return null;
        }

        RegistroFinanceiro registro = registroOptional.get();
        registro.setDescricao(dto.getDescricao());
        registro.setValor(dto.getValor());
        registro.setData(dto.getData());

        registroFinanceiroRepository.save(registro);
        return dto;
    }

    public boolean deletarRegistro(Long id) {
        if (!registroFinanceiroRepository.existsById(id)) {
            return false;
        }

        registroFinanceiroRepository.deleteById(id);
        return true;
    }
}