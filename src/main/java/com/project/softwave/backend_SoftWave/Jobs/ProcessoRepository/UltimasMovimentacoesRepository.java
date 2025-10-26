package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UltimasMovimentacoesRepository extends JpaRepository<UltimasMovimentacoes, Integer> {
    Optional<UltimasMovimentacoes> findByMovimentoAndData(String movimento, LocalDate data);

    @Query("SELECT u FROM UltimasMovimentacoes u ORDER BY u.data DESC")
    List<UltimasMovimentacoes> findAllOrderedByDataDesc();

    Optional<UltimasMovimentacoes> findById(Integer id);


    List<UltimasMovimentacoes> findAll();

    List<UltimasMovimentacoes> findByProcessoId(Integer processoId);


    List<UltimasMovimentacoes> findByProcessoIdOrderByDataDesc(Integer processoId);

    List<UltimasMovimentacoes> findByProcesso(Processo processo);
}
