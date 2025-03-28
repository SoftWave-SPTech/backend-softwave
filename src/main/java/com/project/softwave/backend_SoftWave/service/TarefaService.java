package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TarefaService {


    @Autowired
    private TarefaRepository tarefaRepository;


    public List<Tarefa> listarTarefa() {
        return tarefaRepository.findAll();

    }

    public Tarefa buscarPorId(Integer id) {


        return tarefaRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Tarefa %d não encontrado".formatted(id))
        );

    }

    public Tarefa cadastrarTarefa(Tarefa tarefa) {
        if (tarefa.getPrazo().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("O prazo da tarefa não pode ser anterior à data atual.");
        }

        List<Tarefa> tarefasExistentes = tarefaRepository.findByTituloContainingIgnoreCase(tarefa.getTitulo());

        if (!tarefasExistentes.isEmpty()) {
            throw new EntidadeConflitoException("Já existe uma tarefa com esse título.");
        }

        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Tarefa tarefaParaAtualizar, Integer id) {
        boolean existeTarefa = tarefaRepository.existsById(id);
        if (!existeTarefa) {
            throw new EntidadeNaoEncontradaException("Tarefa %d não encontrada".formatted(id));
        }
        tarefaParaAtualizar.setId(id);
        return tarefaRepository.save(tarefaParaAtualizar);
    }

    public void removerPorId(Integer id) {

        if (!tarefaRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Tarefa %d não encontrada".formatted(id));
        }

        tarefaRepository.deleteById(id);
    }


}

