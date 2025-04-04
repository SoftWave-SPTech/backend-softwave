package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.Tarefa;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.PrazoInvalidoException;
import com.project.softwave.backend_SoftWave.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TarefaService {


    @Autowired
    private TarefaRepository repository;


    public List<Tarefa> listarTarefas() {
        return repository.findAll();

    }

    public Tarefa buscarPorId(Integer id) {


        return repository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Tarefa %d não encontrado".formatted(id))
        );

    }

    public Tarefa cadastrarTarefa(Tarefa tarefa) {
        if (tarefa.getPrazo().isBefore(LocalDateTime.now())) {
            throw new PrazoInvalidoException("O prazo da tarefa não pode ser anterior à data atual.");
        }



        List<Tarefa> tarefasExistentes = repository.findByTituloContainingIgnoreCase(tarefa.getTitulo());

        if (!tarefasExistentes.isEmpty()) {
            throw new EntidadeConflitoException("Já existe uma tarefa com esse título.");
        }

        return repository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Tarefa tarefaParaAtualizar, Integer id) {
        boolean existeTarefa = repository.existsById(id);
        if (!existeTarefa) {
            throw new EntidadeNaoEncontradaException("Tarefa %d não encontrada".formatted(id));
        }
        tarefaParaAtualizar.setId(id);
        return repository.save(tarefaParaAtualizar);
    }

    public void deletarTarefa(Integer id) {
        boolean existeTarefa = repository.existsById(id);
        if (!existeTarefa) {
            throw new EntidadeNaoEncontradaException("Tarefa %d não encontrada".formatted(id));
        }

        repository.deleteById(id);
    }


}

