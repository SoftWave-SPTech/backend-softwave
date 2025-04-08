package com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.HistoricoClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoClassesRepository extends JpaRepository<HistoricoClasses,Integer> {

    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<HistoricoClasses> findByTipo(String tipo);
    List<HistoricoClasses> findByTipo(String tipo);
}
