package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.repository.AnaliseProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseProcessoService {

    @Autowired
    private AnaliseProcessoRepository analiseRepository;

    public List<AnaliseProcesso> listarTodas() {
        return analiseRepository.findAll();
    }
}
