package com.project.softwave.backend_SoftWave.Jobs.ProcessoService;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.CadastroProcessoDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoDTO.UltimasMovimentacoesDTO;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.dto.*;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.QtdPorSetorDTO;
import com.project.softwave.backend_SoftWave.dto.DTOsDash.SetorComMaisProcessosDTO;
import com.project.softwave.backend_SoftWave.entity.ComentarioProcesso;
import com.project.softwave.backend_SoftWave.dto.RemoverUsuarioProcessoDTO;
import com.project.softwave.backend_SoftWave.dto.VincularUsuariosProcessoDTO;
import com.project.softwave.backend_SoftWave.entity.AdvogadoFisico;
import com.project.softwave.backend_SoftWave.entity.AnaliseProcesso;
import com.project.softwave.backend_SoftWave.entity.Usuario;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.ComentarioProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.DocumentoProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.AnaliseProcessoRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import com.project.softwave.backend_SoftWave.repository.RegistroFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    @Autowired
    private UltimasMovimentacoesRepository ultimasMovimentacoesRepository;

    @Autowired
    private AnaliseProcessoRepository analiseProcessoRepository;

    @Autowired
    private DocumentoProcessoRepository documentoProcessoRepository;

    @Autowired
    private RegistroFinanceiroRepository registroFinanceiroRepository;

    public void vincularUsuariosAoProcesso(VincularUsuariosProcessoDTO dto) {
        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado!"));

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
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        Processo processo = processoRepository.findById(dto.getProcessoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado!"));

        if (!usuario.getProcessos().contains(processo)) {
            throw new EntidadeConflitoException("Este processo não está vinculado a este usuário!");
        }

        usuario.getProcessos().remove(processo);
        usuarioRepository.save(usuario);
    }

    public Processo buscarPorNumeroProcesso(String numeroProcesso) {
        return processoRepository.findProcessoByNumeroProcesso(numeroProcesso)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado!"));
    }

    public void atualizarProcessoComUsuarios(Processo processoAtual, CadastroProcessoDTO novoProcesso) {
        // Primeiro, remove todos os vínculos existentes
        if (processoAtual.getUsuarios() != null) {
            for (Usuario usuario : processoAtual.getUsuarios()) {
                usuario.getProcessos().remove(processoAtual);
            }
            processoAtual.getUsuarios().clear();
        }
        
        // Depois, adiciona os novos vínculos
        VincularUsuariosProcessoDTO novoVinculo = new VincularUsuariosProcessoDTO(processoAtual.getId(), novoProcesso.getUsuarios());
        vincularUsuariosAoProcesso(novoVinculo);
        
        // Atualiza a descrição
        processoAtual.setDescricao(novoProcesso.getDescricao());
        processoRepository.save(processoAtual);
    }

    public Processo listarProcessoPorId(Integer id) {
        return processoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Processo não encontrado"));
    }

    public List<ProcessoSimplesDTO> listarProcessoPorIdUsuario(Integer id) {
        List<Processo>  listaProcesso = listarProcessosPorUsuarioId(id);

        return listaProcesso.stream()
                .map(ProcessoSimplesDTO::toProcessoSimplesDTO)
                .toList();
    }


    public List<Processo> listarProcessosPorUsuarioId(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado!"));

        return processoRepository.findByUsuariosContaining(usuario);
    }


    public List<Processo> listarProcessos() {
        List<Processo> processos = processoRepository.findAll();

        if (processos.isEmpty()) {
            throw new NoContentException("Nenhum processo encontrado!");
        }

        return processos;
    }

    @Transactional
    public Boolean deletarProcesso(Integer id) {
        Optional<Processo> processoOptional = processoRepository.findById(id);
        if (processoOptional.isPresent()) {
            Processo processo = processoOptional.get();

            // Buscar movimentações relacionadas ao processo
            List<UltimasMovimentacoes> movimentacoes = ultimasMovimentacoesRepository.findByProcesso(processo);

            // Para cada movimentação, excluir primeiro a análise vinculada
            for (UltimasMovimentacoes mov : movimentacoes) {
                Optional<AnaliseProcesso> analise = analiseProcessoRepository.findByMovimentacoes(mov);
                analise.ifPresent(analiseProcessoRepository::delete);
                // Excluir comentários vinculados à última movimentação (se houver)
                var comentariosMov = comentarioProcessoRepository.findByUltimaMovimentacaoId(mov.getId());
                if (comentariosMov != null && !comentariosMov.isEmpty()) {
                    comentarioProcessoRepository.deleteAll(comentariosMov);
                }
            }
            // Excluir comentários vinculados ao processo
            var comentarios = comentarioProcessoRepository.findByProcessoId(processo.getId().longValue());
            if (comentarios != null && !comentarios.isEmpty()) {
                comentarioProcessoRepository.deleteAll(comentarios);
            }
            // Excluir documentos vinculados ao processo
            var documentos = documentoProcessoRepository.findByFkProcessoId(processo.getId());
            if (documentos != null && !documentos.isEmpty()) {
                documentoProcessoRepository.deleteAll(documentos);
            }
            // Excluir registros financeiros vinculados ao processo
            var registrosFinanceiros = registroFinanceiroRepository.findByProcessoId(processo.getId());
            if (registrosFinanceiros != null && !registrosFinanceiros.isEmpty()) {
                registroFinanceiroRepository.deleteAll(registrosFinanceiros);
            }
            ultimasMovimentacoesRepository.deleteAll(movimentacoes);

            // Desvincular usuários do processo
            if (processo.getUsuarios() != null) {
                // faz uma cópia para evitar ConcurrentModification
                List<Usuario> usuariosVinculados = new ArrayList<>(processo.getUsuarios());
                for (Usuario usuario : usuariosVinculados) {
                    usuario.getProcessos().remove(processo);
                }
                // persiste a mudança do lado possivelmente proprietário da relação
                usuarioRepository.saveAll(usuariosVinculados);
                // limpa vínculos no próprio processo e persiste
                processo.getUsuarios().clear();
            }
            processoRepository.save(processo); // necessário para persistir desvinculação
            processoRepository.deleteById(id);
            return true;
        } else {
            throw new EntidadeNaoEncontradaException("Processo não encontrado");
        }
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

        if (valorTotal == null) {
            valorTotal = BigDecimal.ZERO;
        }

        NumberFormat formatador = NumberFormat.getInstance(new Locale("pt", "BR"));
        formatador.setMinimumFractionDigits(1);  // mínimo 1 casa decimal
        formatador.setMaximumFractionDigits(2);  // máximo 2 casas decimais

        return formatador.format(valorTotal);
    }

    public List<QtdPorSetorDTO> qtdProcessosPorSetor(){
        List<QtdPorSetorDTO> setoresQtdProcessos = processoRepository.qtdProcessosPorSetor();

        if(setoresQtdProcessos.isEmpty()){
            return List.of();
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
        List<UltimasMovimentacoes> movimentacoes = movimentacoesRepository.findByProcessoIdOrderByDataDesc(processo.getId());

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
                    comentario.getProcesso().getId().longValue(),
                    comentario.getUsuario().getId()
            );
            dto.setComentario(comentarioDTO);
        } else {
            dto.setComentario(null);
        }

        return dto;
    }

    public List<Processo> listarProcessosOrdenadosPorDataCriacao() {
        List<Processo> processos = processoRepository.findAllByOrderByCreatedAtDesc();
        return processos;
    }

    public void cadastrar(CadastroProcessoDTO processoDTO){
        Processo processoCadastro = new Processo();

        if(processoRepository.findProcessoByNumeroProcesso(processoDTO.getNumeroProcesso()).isPresent()){
            throw new EntidadeConflitoException("Processo já existe!");
        }

        processoCadastro.setNumeroProcesso(processoDTO.getNumeroProcesso());
        processoCadastro.setDescricao(processoDTO.getDescricao());

        processoRepository.save(processoCadastro);

        processoCadastro = processoRepository.findProcessoByNumeroProcesso(processoDTO.getNumeroProcesso()).get();

        if(!processoDTO.getUsuarios().isEmpty()){
            VincularUsuariosProcessoDTO novoVinculo = new VincularUsuariosProcessoDTO(processoCadastro.getId(), processoDTO.getUsuarios());
            vincularUsuariosAoProcesso(novoVinculo);
        }
    }
}
