package com.project.softwave.backend_SoftWave.Jobs.ProcessoService;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.CadastroProcessoDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.UltimasMovimentacoesDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdPorSetorDTO;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.SetorComMaisProcessosDTO;
import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.ComentarioProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class ProcessoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UltimasMovimentacoesRepository movimentacoesRepository;

    @Autowired
    private ComentarioProcessoRepository comentarioProcessoRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public void vincularUsuariosAoProcesso(VincularUsuariosProcessoDTO dto) {
        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado"));

        List<Usuario> usuarios = usuarioRepository.findAllById(dto.getUsuariosIds());

        for (Usuario usuario : usuarios) {
            if (!usuario.getProcessos().contains(processo)) {
                usuario.getProcessos().add(processo); // Adiciona processo ao usuário
            }
        }
        usuarioRepository.saveAll(usuarios); // Persiste a alteração na tabela de associação
    }

    public void removerUsuarioDoProcesso(RemoverUsuarioProcessoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado"));

        if (!usuario.getProcessos().contains(processo)) {
            throw new EntidadeNaoEncontradaException("Este processo não está vinculado a este usuário.");
        }

        usuario.getProcessos().remove(processo);
        usuarioRepository.save(usuario);
    }

    public Processo buscarPorNumeroProcesso(String numeroProcesso) {
        return processoRepository.findProcessoByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado"));
    }

    public void atualizarProcessoComUsuarios(Processo processoAtual, CadastroProcessoDTO novoProcesso) {
        VincularUsuariosProcessoDTO novoVinculo = new VincularUsuariosProcessoDTO(processoAtual.getId(), novoProcesso.getUsuarios());
        vincularUsuariosAoProcesso(novoVinculo);
        processoAtual.setDescricao(novoProcesso.getDescricao());
        processoRepository.save(processoAtual);
    }

    public List<ProcessoSimplesDTO> listarProcessoPorIdUsuario(Integer id) {
        List<Processo> processos = processoRepository.findByUsuariosId(id);
        List<ProcessoSimplesDTO> processosDoUsuario = new ArrayList<>();

        for(Processo processoDaVez : processos){
            ProcessoSimplesDTO auxiliar = new ProcessoSimplesDTO(
                    processoDaVez.getId(),
                    processoDaVez.getNumeroProcesso()
            );

            processosDoUsuario.add(auxiliar);

        }
        return processosDoUsuario;
    }

    public Integer quantidadeProcessos(){
        Integer quantidadeProcessos = processoRepository.quantidadeProcessos();

        if(quantidadeProcessos > 0){
            return quantidadeProcessos;
        }else {
            return 0;
        }
    }

    public String valorTotalProcessos(){
        BigDecimal valorTotal = processoRepository.valorTotalProcessos();

        NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatador.setMinimumFractionDigits(1);  // mínimo 1 casa decimal
        formatador.setMaximumFractionDigits(2);  // máximo 2 casas decimais

        return formatador.format(valorTotal);
    }

    public List<QtdPorSetorDTO> qtdProcessosPorSetor(){
        List<QtdPorSetorDTO> setoresQtdProcessos = processoRepository.qtdProcessosPorSetor();

        if(setoresQtdProcessos.isEmpty()){
            return null;
        }

        return setoresQtdProcessos;
    }


    public SetorComMaisProcessosDTO setorComMaisProcessos(){
        List<QtdPorSetorDTO> setoresQtdProcessos = processoRepository.qtdProcessosPorSetor();
        SetorComMaisProcessosDTO setorComMaisProcesso = new SetorComMaisProcessosDTO();
        Integer valorInicial = 0;

        if(!setoresQtdProcessos.isEmpty()){
            for(QtdPorSetorDTO setorDTO : setoresQtdProcessos){
                if(setorDTO.getQtdProcessos() > valorInicial){
                    valorInicial = setorDTO.getQtdProcessos();
                    setorComMaisProcesso.setQtdProcessos(setorDTO.getQtdProcessos());
                    setorComMaisProcesso.setSetor(setorDTO.getSetor());
                }
            }
        }

        return setorComMaisProcesso;
    }

    public ProcessoCompletoDTO buscarProcessoPorId(Integer id) {
        // Busca o processo no banco
        Processo processo = processoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com ID: " + id));

        // Cria DTO básico com dados do processo e advogados
        ProcessoCompletoDTO dto = new ProcessoCompletoDTO(processo);

        // 🔸 Busca movimentações no banco
        List<UltimasMovimentacoes> movimentacoes = movimentacoesRepository.findByProcessoId(processo.getId());

        // 🔸 Converte movimentações para DTO
        List<UltimasMovimentacoesDTO> movimentacoesDTO = movimentacoes.stream()
                .map(UltimasMovimentacoesDTO::fromEntity) // método que você deve ter criado no DTO
                .toList();

        dto.setMovimentacoes(movimentacoesDTO);

        // 🔸 Busca o último comentário associado ao processo
        ComentarioProcesso comentario = comentarioProcessoRepository
                .findFirstByProcessoIdOrderByDataCriacaoDesc(processo.getId());

        // 🔸 Converte para DTO, se existir
        if (comentario != null) {
            ComentarioProcessoDTO comentarioDTO = new ComentarioProcessoDTO(
                    comentario.getId(),
                    comentario.getComentario(),
                    comentario.getDataCriacao(),
                    Integer.valueOf(comentario.getUltimaMovimentacao() != null ? comentario.getUltimaMovimentacao().getMovimento() : null)
            );
            dto.setComentario(comentarioDTO);
        } else {
            dto.setComentario(null);
        }

        return dto;
    }



}
