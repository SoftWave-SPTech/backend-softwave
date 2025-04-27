package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvogadoJuridicoRepository extends JpaRepository<AdvogadoJuridico, Integer> {

    Optional<AdvogadoJuridico> findByEmailEqualsOrCnpjEquals(String email, String cnpj);

    boolean existsByEmailEqualsOrCnpjEqualsAndIdNot(String email, String cnpj, Integer id);

    Optional<AdvogadoJuridico> findByEmailEqualsAndSenhaEquals(String email, String senha);
    Optional<AdvogadoJuridico> findByOab(Integer oab);
    boolean existsByOab(Integer oab);
}
