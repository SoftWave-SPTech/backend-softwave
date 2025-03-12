package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {
    boolean existsByNumero(String numero);
    Optional<Processo> findByNumero(String numero);
}
