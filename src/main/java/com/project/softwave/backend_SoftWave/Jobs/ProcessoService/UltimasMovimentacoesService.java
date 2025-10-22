package com.project.softwave.backend_SoftWave.Jobs.ProcessoService;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UltimasMovimentacoesService {


    @Autowired
    private UltimasMovimentacoesRepository ultimasMovimentacoesRepository;


    public List<UltimasMovimentacoes> listarMovimentacoesOrdenadasPorData() {
        List<UltimasMovimentacoes> todos = ultimasMovimentacoesRepository.findAllOrderedByDataDesc();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma movimentação encontrada!");
        }
        return todos;
    }


    public UltimasMovimentacoes buscarPorId(Integer id) {
        Optional<UltimasMovimentacoes> movimentacao = ultimasMovimentacoesRepository.findById(id);
        return movimentacao.orElseThrow(() -> new EntidadeNaoEncontradaException("Movimentação com ID " + id + " não encontrada"));
    }

    public List<UltimasMovimentacoes> buscarPorIdProcesso(Integer processoId) {
        List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesRepository.findByProcessoIdOrderByDataDesc(processoId);
        if (movimentacoes.isEmpty()) {
            throw new NoContentException("Nenhuma movimentação encontrada para este processo!");
        }
        return movimentacoes;
    }

    public List<UltimasMovimentacoes> listarMovimentacoesPorIdProcessoOrdenadasPorData(Integer processoId) {
        List<UltimasMovimentacoes> movimentacoesOrdenadas = ultimasMovimentacoesRepository.findByProcessoIdOrderByDataDesc(processoId);
        if (movimentacoesOrdenadas.isEmpty()) {
            throw new NoContentException("Nenhuma movimentação encontrada para este processo!");
        }
        return movimentacoesOrdenadas;
    }

}
