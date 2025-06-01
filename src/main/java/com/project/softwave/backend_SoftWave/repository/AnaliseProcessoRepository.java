package com.project.softwave.backend_SoftWave.repository;


import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnaliseProcessoRepository extends JpaRepository<AnaliseProcesso, Integer> {

    Optional<AnaliseProcesso> findByMovimentacoesId(Integer id);


}
