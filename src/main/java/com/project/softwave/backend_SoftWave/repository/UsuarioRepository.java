package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailEqualsAndSenhaEquals(String email, String senha);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.senha = :senha WHERE u.email = :email")
    void updateSenhaByEmail(String senha, String email);
}
