package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RegistroFinanceiroRepository extends JpaRepository<RegistroFinanceiro, Integer> {

    List<RegistroFinanceiro> findByAnoOrderByAnoDescMesDesc(Integer ano);
}