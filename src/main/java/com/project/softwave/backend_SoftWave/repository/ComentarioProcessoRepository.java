package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ComentarioProcessoRepository extends JpaRepository<ComentarioProcesso, Long> {

    List<ComentarioProcesso> findByUltimaMovimentacaoId(Integer ultimaMovimentacaoId);

    List<ComentarioProcesso> findByProcessoId(Integer processoId);

    ComentarioProcesso findFirstByProcessoIdOrderByDataCriacaoDesc(Integer processoId);
}