package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
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


    public AnaliseProcesso buscarPorId(Integer id) {

        return analiseRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Análise IA com ID %d não foi encontrada".formatted(id))
        );
    }

    public AnaliseProcesso buscarPorIdMovimentacao(Integer movimentacaoId) {

        return analiseRepository.findByMovimentacoesId(movimentacaoId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        "Nenhuma análise encontrada para a movimentação com ID %d".formatted(movimentacaoId))
        );
    }
}
