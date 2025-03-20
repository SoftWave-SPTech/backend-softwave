package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.UsuarioFisico;
import com.project.softwave.backend_SoftWave.entity.UsuarioJuridico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioFisicoRepository extends JpaRepository<UsuarioFisico, Integer> {

    Optional<UsuarioFisico> findByEmailEqualsAndSenhaEquals(String email, String senha);

    Optional<UsuarioFisico> findByEmailEqualsOrCpfEquals(String email, String cpf);

    Boolean existsByEmailEqualsOrCpfEqualsAndIdNot(String email, String cpf, Integer id);
}
