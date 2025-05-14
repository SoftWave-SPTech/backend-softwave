package com.project.softwave.backend_SoftWave.Jobs.ProcessoService;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UltimasMovimentacoesService {


    @Autowired
    private UltimasMovimentacoesRepository ultimasMovimentacoesRepository;


    public List<UltimasMovimentacoes> listarMovimentacoesOrdenadasPorData() {
        return ultimasMovimentacoesRepository.findAllOrderedByDataDesc();
    }


    public UltimasMovimentacoes buscarPorId(Integer id) {
        Optional<UltimasMovimentacoes> movimentacao = ultimasMovimentacoesRepository.findById(id);
        return movimentacao.orElseThrow(() -> new EntidadeNaoEncontradaException("Movimentação com ID " + id + " não encontrada"));
    }

    public List<UltimasMovimentacoes> buscarPorIdProcesso(Integer processoId) {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesRepository.findByProcessoId(processoId);
        if (movimentacoes.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhuma movimentação encontrada para o processo com ID " + processoId);
        }
        return movimentacoes;
    }

    public List<UltimasMovimentacoes> listarMovimentacoesPorIdProcessoOrdenadasPorData(Integer processoId) {
        return ultimasMovimentacoesRepository.findByProcessoIdOrderByDataDesc(processoId);
    }

}
