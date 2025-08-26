package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.dto.ClienteComProcessosResponseDTO;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioDetalhesDto;
import com.project.softwave.backend_SoftWave.dto.usuariosDtos.UsuarioLoginDto;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.entity.AdvogadoJuridico;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailEqualsAndTokenPrimeiroAcessoEquals(String email, String senha);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.senha = :senha, u.ativo = true, u.tokenPrimeiroAcesso = null WHERE u.email = :email")
    void updateSenhaByEmail(String senha, String email);

    @Transactional
    @Query("SELECT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " )
    List<Usuario> findClientesComProcessos();

    @Transactional
    @Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND p.area = :setor")
    List<Usuario> findClientesComProcessosPorSetor(@Param("setor") String setor);

    @Transactional
    @Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND p.foro LIKE CONCAT('%', :foro, '%')")
    List<Usuario> findClientesComProcessosPorForo(@Param("foro") String foro);

    @Transactional
    @Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND p.assunto = :assunto")
    List<Usuario> findClientesComProcessosPorAssunto(@Param("assunto") String assunto);

    @Transactional
    @Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND p.vara = :vara")
    List<Usuario> findClientesComProcessosPorVara(@Param("vara") String vara);

    @Transactional
    @Query("SELECT DISTINCT u FROM Usuario u JOIN FETCH u.processos p " +
            "WHERE (u.tipoUsuario = 'usuario_fisico' OR u.tipoUsuario = 'usuario_juridico') " +
            "AND p.descricao LIKE CONCAT('%', :descricao, '%')")
    List<Usuario> findClientesComProcessosPorDescricao(@Param("descricao") String descricao);


    @Query(value = """
            SELECT DISTINCT u.*
    FROM usuario u
    JOIN usuarios_processos up ON u.id = up.usuario_id
    JOIN processo p ON up.processo_id = p.id
    WHERE (
        (u.tipo_usuario = 'usuario_fisico' AND u.nome LIKE CONCAT('%', :termo , '%'))
        OR
        (u.tipo_usuario = 'usuario_juridico' AND u.nome_fantasia LIKE CONCAT('%', :termo , '%'))
        OR
        (p.numero_processo LIKE CONCAT('%', :termo , '%') AND ((u.tipo_usuario = 'usuario_juridico') OR (u.tipo_usuario = 'usuario_fisico')))
    );
    """, nativeQuery = true)
    List<Usuario> buscarClientesPorTermo(@Param("termo") String termo);

    // Busca usuários que NÃO são advogados (clientes)
    @Query("SELECT u FROM Usuario u WHERE TYPE(u) NOT IN (AdvogadoFisico, AdvogadoJuridico)")
    List<Usuario> findClientes();

    // Busca apenas advogados físicos
    @Query("SELECT u FROM Usuario u WHERE TYPE(u) = AdvogadoFisico")
    List<AdvogadoFisico> findAdvogadosFisicos();

    // Busca apenas advogados jurídicos
    @Query("SELECT u FROM Usuario u WHERE TYPE(u) = AdvogadoJuridico")
    List<AdvogadoJuridico> findAdvogadosJuridicos();

    // Busca todos os advogados (físicos e jurídicos)
    @Query("SELECT u FROM Usuario u WHERE TYPE(u) IN (AdvogadoFisico, AdvogadoJuridico)")
    List<Usuario> findAdvogados();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE TYPE(u) IN (AdvogadoFisico, AdvogadoJuridico)")
    Integer quantidadeAdvogados();

    @Query("SELECT COUNT(u) FROM Usuario u WHERE TYPE(u) IN (UsuarioFisico, UsuarioJuridico)")
    Integer quantidadeClientes();

    Optional<Usuario> findByTokenRecuperacaoSenha(String token);

    @Query(value = """
    SELECT DISTINCT u.*
    FROM usuario u
    JOIN usuarios_processos up ON u.id = up.usuario_id
    JOIN processo p ON p.id = up.processo_id
    WHERE u.ativo = :status
      AND (u.tipo_usuario = 'usuario_juridico' OR u.tipo_usuario = 'usuario_fisico')
    """, nativeQuery = true)
    List<Usuario> findClientesComProcessosPorStatus(@Param("status") String status);

    Boolean existsByEmailAndAtivoIsTrue(String email);

    Boolean existsByEmail(String email);

}
