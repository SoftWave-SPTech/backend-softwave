package com.project.softwave.backend_SoftWave.repository;


import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseProcessoRepository extends JpaRepository<AnaliseProcesso, Integer> {
}
