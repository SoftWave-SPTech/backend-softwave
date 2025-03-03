package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
