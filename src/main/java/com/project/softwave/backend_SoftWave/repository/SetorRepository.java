package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
    boolean existsByNome(String nome);
}
