package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioFisicoRepository extends JpaRepository<Usuario, Integer> {

//    Optional<Usuario> findByEmailEqualsAndSenhaEquals(String email, String senha);
}
