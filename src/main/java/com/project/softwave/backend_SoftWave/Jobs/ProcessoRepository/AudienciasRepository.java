package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Audiencias;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface AudienciasRepository extends JpaRepository<Audiencias,Integer> {
    Optional<Audiencias> findByDataAndProcessoId(String data, Integer processoId);
}
