package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvogadoFisicoRepository extends JpaRepository<AdvogadoFisico, Integer> {
    Optional<Object> findByEmailEqualsOrCpfEquals(String email, String cpf);

    boolean existsByEmailEqualsOrCpfEqualsAndIdNot(String email, String cpf, Integer id);

    Optional<AdvogadoFisico> findByEmailEqualsAndSenhaEquals(String email, String senha);

    Optional<AdvogadoFisico> findByOab(Integer oab);

    Optional<AdvogadoFisico> findByEmailEqualsOrCpfEqualsOrRgEquals(String email, String cpf, String rg);
}
