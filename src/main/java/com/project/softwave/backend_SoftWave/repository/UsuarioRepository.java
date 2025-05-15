package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailEqualsAndSenhaEquals(String email, String senha);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.senha = :senha WHERE u.email = :email")
    void updateSenhaByEmail(String senha, String email);

    @Transactional
    @Query("SELECT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND u.oab IS NULL")
    List<Usuario> findClientesComProcessos();

    @Transactional
    @Query("""
    SELECT DISTINCT u FROM Usuario u
    LEFT JOIN u.processos p
    WHERE (
        LOWER(u.nome) LIKE LOWER(CONCAT('%', :termo, '%')) 
        OR LOWER(p.numeroProcesso) LIKE LOWER(CONCAT('%', :termo, '%'))
    )
    AND (TYPE(u) = UsuarioFisico OR TYPE(u) = UsuarioJuridico)
    AND (TREAT(u AS AdvogadoFisico).oab IS NULL OR TREAT(u AS AdvogadoJuridico).oab IS NULL)
""")
    List<Usuario> buscarClientesPorTermo(@Param("termo") String termo);
}
