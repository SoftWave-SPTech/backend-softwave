package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    boolean existsByNumeroProcesso(String numeroProcesso);
    Optional<Processo> findProcessoByNumeroProcesso(String numeroProcesso);

}
