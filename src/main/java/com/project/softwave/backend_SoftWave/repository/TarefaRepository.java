package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.dto.TarefaDTO;

import com.project.softwave.backend_SoftWave.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
    List<Tarefa> findByTituloContainingIgnoreCase(String tarefa);

}
