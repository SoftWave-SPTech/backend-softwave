package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    boolean existsByNumeroProcesso(String numeroProcesso);
    Optional<Processo> findProcessoByNumeroProcesso(String numeroProcesso);

    @Transactional
    @Query("""
    SELECT p FROM Processo p
    JOIN p.usuarios u
    WHERE LOWER(p.numeroProcesso) LIKE LOWER(CONCAT('%', :termo, '%'))
       OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%'))
""")
    List<Processo> pesquisarPorUsuarioOuNumero(@Param("termo") String termo);

}
