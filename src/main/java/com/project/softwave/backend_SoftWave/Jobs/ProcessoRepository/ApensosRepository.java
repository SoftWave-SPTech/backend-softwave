package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Apensos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApensosRepository extends JpaRepository<Apensos, Integer> {
    Optional<Integer> findByNumeroProcesso(String numeroProcessoApenso);
}
