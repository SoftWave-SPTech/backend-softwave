package com.project.softwave.backend_SoftWave.repository;

import com.project.softwave.backend_SoftWave.entity.DocumentosProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoProcessoRepository extends JpaRepository<DocumentosProcesso, Integer> {

    List<DocumentosProcesso> findByNomeArquivoContainingIgnoreCase(String nome);
    List<DocumentosProcesso> findByFkProcessoId(Integer idProcesso);
}
