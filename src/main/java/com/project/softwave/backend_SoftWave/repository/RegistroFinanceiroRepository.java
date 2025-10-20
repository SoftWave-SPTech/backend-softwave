package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Integer> {

    @Query(value = """
    SELECT * FROM registro_financeiro r
    WHERE (r.ano, r.mes) IN (
        SELECT ano, mes FROM registro_financeiro
        GROUP BY ano, mes
        ORDER BY ano DESC, mes DESC
        LIMIT 6
    )
    ORDER BY ano DESC, mes DESC
    """, nativeQuery = true)
    List<RegistroFinanceiro> findUltimos6MesesRegistros();

}