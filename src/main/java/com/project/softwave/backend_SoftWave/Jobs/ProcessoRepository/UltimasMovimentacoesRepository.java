package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UltimasMovimentacoesRepository extends JpaRepository<UltimasMovimentacoes, Integer> {
    Optional<Integer> findByMovimentoAndData(String movimento, String data);
}
