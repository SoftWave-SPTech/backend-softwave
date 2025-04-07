package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.dto.ComentarioProcessoDTO;
import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.ComentarioProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioProcessoService {

    @Autowired
    private ComentarioProcessoRepository comentarioProcessoRepository;

    public ComentarioProcessoDTO criarComentario(ComentarioProcessoDTO dto) {
        ComentarioProcesso comentario = new ComentarioProcesso();
        comentario.setProcessoId(dto.getProcessoId());
        comentario.setComentario(dto.getComentario());
        comentario.setDataCriacao(dto.getDataCriacao());

        comentario = comentarioProcessoRepository.save(comentario);
        dto.setId(comentario.getId());
        return dto;
    }

    public List<ComentarioProcessoDTO> listarComentarios() {
        return comentarioProcessoRepository.findAll()
                .stream()
                .map(comentario -> new ComentarioProcessoDTO(
                        comentario.getId(),
                        comentario.getProcessoId(),
                        comentario.getComentario(),
                        comentario.getDataCriacao()))
                .collect(Collectors.toList());
    }

    public ComentarioProcessoDTO buscarComentarioPorId(Long id) {
        Optional<ComentarioProcesso> comentarioOptional = comentarioProcessoRepository.findById(id);
        return comentarioOptional
                .map(comentario -> new ComentarioProcessoDTO(
                        comentario.getId(),
                        comentario.getProcessoId(),
                        comentario.getComentario(),
                        comentario.getDataCriacao()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Comentário não encontrado"));
    }

    public ComentarioProcessoDTO atualizarComentario(Long id, ComentarioProcessoDTO dto) {
        Optional<ComentarioProcesso> comentarioOptional = comentarioProcessoRepository.findById(id);
        if (comentarioOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Comentário não encontrado");
        }

        ComentarioProcesso comentario = comentarioOptional.get();
        comentario.setProcessoId(dto.getProcessoId());
        comentario.setComentario(dto.getComentario());
        comentario.setDataCriacao(dto.getDataCriacao());

        comentarioProcessoRepository.save(comentario);
        return dto;
    }

    public void deletarComentario(Long id) {
        if (!comentarioProcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Comentário não encontrado");
        }

        comentarioProcessoRepository.deleteById(id);
    }
}