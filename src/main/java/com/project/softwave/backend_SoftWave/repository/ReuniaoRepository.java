package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Reuniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {
    boolean existsByIdAdvogadoAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
            Integer idAdvogado, LocalDateTime dataHoraFim, LocalDateTime dataHoraInicio);

    boolean existsByIdClienteAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
            Integer idCliente, LocalDateTime dataHoraFim, LocalDateTime dataHoraInicio);


}