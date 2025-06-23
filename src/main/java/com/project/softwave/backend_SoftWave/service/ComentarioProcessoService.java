package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoService.UltimasMovimentacoesService;
import com.project.softwave.backend_SoftWave.dto.ComentarioProcessoDTO;
import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.ComentarioProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComentarioProcessoService {

    @Autowired
    private ComentarioProcessoRepository comentarioProcessoRepository;

    @Autowired
    private UltimasMovimentacoesRepository ultimasMovimentacoesRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ComentarioProcessoDTO criarComentarioUltimaMovimentacao(ComentarioProcessoDTO dto) {
        if (dto.getUltimaMovimentacaoID() == null){
            throw new EntidadeNaoEncontradaException("ID da movimentação não pode ser nulo");
        }

        UltimasMovimentacoes ultimaMovimentacao = ultimasMovimentacoesRepository.findById(dto.getUltimaMovimentacaoID())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Última movimentação não encontrada"));

        Usuario usuarioAutor = usuarioRepository.findById(dto.getUsuarioID())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        ComentarioProcesso comentario = new ComentarioProcesso();
        comentario.setComentario(dto.getComentario());
        comentario.setDataCriacao(dto.getDataCriacao());
        comentario.setUltimaMovimentacao(ultimaMovimentacao);
        comentario.setUsuario(usuarioAutor);

        comentario = comentarioProcessoRepository.save(comentario);
        dto.setId(comentario.getId());
        return dto;
    }

    public ComentarioProcessoDTO criarComentarioProcesso(ComentarioProcessoDTO dto) {
        if (dto.getProcessoID() == null) {
            throw new EntidadeNaoEncontradaException("ID do processo não pode ser nulo");
        }

        Processo processo = processoRepository.findById( (dto.getProcessoID().intValue()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrada"));

        Usuario usuarioAutor = usuarioRepository.findById(dto.getUsuarioID())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        ComentarioProcesso comentario = new ComentarioProcesso();
        comentario.setComentario(dto.getComentario());
        comentario.setDataCriacao(dto.getDataCriacao());
        comentario.setProcesso(processo);
        comentario.setUsuario(usuarioAutor);

        comentario = comentarioProcessoRepository.save(comentario);
        dto.setId(comentario.getId());
        return dto;
    }

    public List<ComentarioProcessoDTO> listarComentarios() {
        return comentarioProcessoRepository.findAll()
                .stream()
                .map(comentario -> new ComentarioProcessoDTO(
                        comentario.getId(),
                        comentario.getComentario(),
                        comentario.getDataCriacao(),
                        comentario.getUltimaMovimentacao().getId())
                        )
                .collect(Collectors.toList());
    }

    public ComentarioProcessoDTO buscarComentarioPorId(Long id) {
        Optional<ComentarioProcesso> comentarioOptional = comentarioProcessoRepository.findById(id);
        return comentarioOptional
                .map(comentario -> new ComentarioProcessoDTO(
                        comentario.getId(),
                        comentario.getComentario(),
                        comentario.getDataCriacao(),
                        comentario.getUltimaMovimentacao().getId()))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Comentário não encontrado"));
    }

    public ComentarioProcessoDTO atualizarComentario(Long id, ComentarioProcessoDTO dto) {
        Optional<ComentarioProcesso> comentarioOptional = comentarioProcessoRepository.findById(id);
        if (comentarioOptional.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Comentário não encontrado");
        }
        UltimasMovimentacoes ultimaMovimentacao = ultimasMovimentacoesRepository.findById(dto.getUltimaMovimentacaoID())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Última movimentação não encontrada"));


        ComentarioProcesso comentario = comentarioOptional.get();
        comentario.setComentario(dto.getComentario());
        comentario.setDataCriacao(dto.getDataCriacao());
        comentario.setUltimaMovimentacao(ultimaMovimentacao);

        comentarioProcessoRepository.save(comentario);
        return dto;
    }

    public void deletarComentario(Long id) {
        if (!comentarioProcessoRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Comentário não encontrado");
        }
        comentarioProcessoRepository.deleteById(id);
    }

    public List<ComentarioProcesso> listarComentariosPorUltimaMovimentacaoId(Integer ultimaMovimentacaoId) {
            return comentarioProcessoRepository.findByUltimaMovimentacaoId(ultimaMovimentacaoId)
                    .stream()
                    .map(comentario -> new ComentarioProcesso(comentario.getId(),
                            comentario.getComentario(),
                            comentario.getDataCriacao(),
                            comentario.getUltimaMovimentacao(),
                            comentario.getUsuario()))
                    .collect(Collectors.toList());
    }

    public List<ComentarioProcesso> listarComentariosPorProcessoId(Long processoId) {
        return comentarioProcessoRepository.findByProcessoId(processoId)
                .stream()
                .map(comentario -> new ComentarioProcesso(comentario.getId(),
                        comentario.getComentario(),
                        comentario.getDataCriacao(),
                        comentario.getProcesso(),
                        comentario.getUsuario()))
                .collect(Collectors.toList());
    }
}