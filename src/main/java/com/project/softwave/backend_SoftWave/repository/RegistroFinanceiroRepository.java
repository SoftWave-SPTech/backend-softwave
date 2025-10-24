package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Integer> {

    @Query(value = """
            SELECT\s
                r.mes,
                r.ano,
                SUM(
                    (COALESCE(r.valor_pago, 0) + COALESCE(r.valor_pagar, 0)) +
                    (COALESCE(p.normalizado_valor_acao, 0) * COALESCE(r.honorario_sucumbencia, 0))
                ) AS valor
            FROM registro_financeiro r
            JOIN processo p ON r.processo_id = p.id
            WHERE r.ano = YEAR(CURDATE())  -- ano atual dinamicamente
            GROUP BY ano, mes
            ORDER BY ano DESC, mes DESC
            LIMIT 6;
    """, nativeQuery = true)
    List<Object[]> findUltimos6MesesRegistros();


}