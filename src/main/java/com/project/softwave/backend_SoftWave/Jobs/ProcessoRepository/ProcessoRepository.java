package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdPorSetorDTO;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    boolean existsByNumeroProcesso(String numeroProcesso);
    Optional<Processo> findProcessoByNumeroProcesso(String numeroProcesso);

    @Query("SELECT p.area AS setor, COUNT(p) AS qtdProcessos " +
            "FROM Processo p " +
            "GROUP BY p.area " +
            "ORDER BY qtdProcessos DESC" )
    List<QtdPorSetorDTO> qtdProcessosPorSetor();

    @Query("SELECT SUM(p.normalizadoValorAcao) FROM Processo p" )
    BigDecimal valorTotalProcessos();

    @Query("SELECT COUNT(p) FROM Processo p" )
    Integer quantidadeProcessos();

    List<Processo> findByUsuariosContaining(Usuario usuario);

    @Query("""
    SELECT DISTINCT u FROM Usuario u
    JOIN u.processos p
    JOIN p.usuarios a
    WHERE a.id = :advogadoId AND u.id <> :advogadoId
""")
    List<Usuario> findClientesPorAdvogado(@Param("advogadoId") Integer advogadoId);

    List<Processo> findByUsuariosId(Integer id);


//    List<Processo> findByUsuario(Usuario usuario);

//    @Transactional
//    @Query("""
//    SELECT p FROM Processo p
//    JOIN p.usuarios u
//    WHERE LOWER(p.numeroProcesso) LIKE LOWER(CONCAT('%', :termo, '%'))
//       OR LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%'))
//""")
//    List<Processo> pesquisarPorUsuarioOuNumero(@Param("termo") String termo);

}
