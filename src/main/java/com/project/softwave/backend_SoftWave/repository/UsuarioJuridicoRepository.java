package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJuridicoRepository extends JpaRepository<UsuarioJuridico, Integer> {
    Optional<UsuarioJuridico> findByEmailEqualsAndSenhaEquals(String email, String senha);

    Optional<UsuarioJuridico> findByEmailEqualsOrCnpjEquals(String email, String cnpj);

    Boolean existsByEmailEqualsOrCnpjEqualsAndIdNot(String email, String cnpj, Integer id);
}
