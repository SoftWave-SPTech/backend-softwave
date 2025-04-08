package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.PeticoesDiversas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeticoesDiversasRepository extends JpaRepository<PeticoesDiversas,Integer> {
}
