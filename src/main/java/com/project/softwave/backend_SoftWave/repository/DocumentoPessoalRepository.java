package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.DocumentoPessoal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoPessoalRepository extends JpaRepository<DocumentoPessoal, Integer> {

    List<DocumentoPessoal> findByNomeArquivoContainingIgnoreCase(String nome);
    List<DocumentoPessoal> findByFkUsuarioId(Integer idUsuario);
}
