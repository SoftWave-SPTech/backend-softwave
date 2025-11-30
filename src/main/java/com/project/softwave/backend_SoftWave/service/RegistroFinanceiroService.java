package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.FinanceiroDadosTelaDTO;
import com.project.softwave.backend_SoftWave.dto.FinanceiroDTO.ReceitaUltimosMesesDTO;
import com.project.softwave.backend_SoftWave.entity.Meses;
import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import com.project.softwave.backend_SoftWave.entity.StatusFinanceiro;
import com.project.softwave.backend_SoftWave.exception.CorpoRequisicaoVazioException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.RegistroFinanceiroRepository;
import com.project.softwave.backend_SoftWave.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegistroFinanceiroService {

    @Autowired
    private RegistroFinanceiroRepository registroFinanceiroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    public RegistroFinanceiro criarRegistro(
            RegistroFinanceiro registroFinanceiro,
            Integer cliente,
            Integer processo
    ) {
        if(registroFinanceiro != null){

            registroFinanceiro.setCliente(
                    usuarioRepository.findById(cliente)
                            .orElseThrow(
                                    () -> new EntidadeNaoEncontradaException("Cliente não encontrados!")
                            )
            );

            registroFinanceiro.setProcesso(
                    processoRepository.findById(processo)
                            .orElseThrow(
                                    () -> new EntidadeNaoEncontradaException("Processo não encontrados!")
                            )
            );

            return registroFinanceiroRepository.save(registroFinanceiro);
        }
        throw  new CorpoRequisicaoVazioException("Campos vazios!");
    }

    public List<RegistroFinanceiro> listarRegistros() {
        List<RegistroFinanceiro> todos = registroFinanceiroRepository.findAll();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma pesquisa encontrado!");
        }

        return todos;
    }

    public RegistroFinanceiro buscarRegistroPorId(Integer id) {
        return registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );
    }

    public RegistroFinanceiro atualizarRegistro(
            Integer id,
            RegistroFinanceiro registroFinanceiro,
            Integer cliente,
            Integer processo
    ) {
        RegistroFinanceiro registroFinanceiroAtualizado = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );

        registroFinanceiroAtualizado.setAno(registroFinanceiro.getAno());
        registroFinanceiroAtualizado.setStatusFinanceiro(registroFinanceiro.getStatusFinanceiro());
        registroFinanceiroAtualizado.setMes(registroFinanceiro.getMes());
        registroFinanceiroAtualizado.setCliente(usuarioRepository.findById(cliente).get());
        registroFinanceiroAtualizado.setMetodoPagamento(registroFinanceiro.getMetodoPagamento());
        registroFinanceiroAtualizado.setParcelaAtual(registroFinanceiro.getParcelaAtual());
        registroFinanceiroAtualizado.setProcesso(processoRepository.findById(processo).get());
        registroFinanceiroAtualizado.setTotalParcelas(registroFinanceiro.getTotalParcelas());
        registroFinanceiroAtualizado.setTipoPagamento(registroFinanceiro.getTipoPagamento());
        registroFinanceiroAtualizado.setValorPagar(registroFinanceiro.getValorPagar());
        registroFinanceiroAtualizado.setValorPago(registroFinanceiro.getValorPago());
        registroFinanceiroAtualizado.setValorParcela(registroFinanceiro.getValorParcela());

        return registroFinanceiroRepository.save(registroFinanceiroAtualizado);
    }

    public void deletarRegistro(Integer id) {
        RegistroFinanceiro registroFinanceiroDeletar = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );

        registroFinanceiroRepository.deleteById(registroFinanceiroDeletar.getId());
    }

    public RegistroFinanceiro atualizarStatusRegistro(Integer id, String statusFinanceiro){
        RegistroFinanceiro registroFinanceiroAtualizado = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );

        registroFinanceiroAtualizado.setStatusFinanceiro(statusFinanceiro != null ? StatusFinanceiro.valueOf(statusFinanceiro.toUpperCase()) : null);

        return registroFinanceiroRepository.save(registroFinanceiroAtualizado);
    }

    public FinanceiroDadosTelaDTO dadosDaTela(Integer id){
        RegistroFinanceiro registroFinanceiro = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("registro não encontrado")
                );

        FinanceiroDadosTelaDTO dadosTela = new FinanceiroDadosTelaDTO();

        dadosTela.setValorTotalProcesso(
                (registroFinanceiro.getValorPagar() + registroFinanceiro.getValorPago())
        );

        dadosTela.setValorTotalCaso(registroFinanceiro.getProcesso().getNormalizadoValorAcao());

        dadosTela.setValorHonorarioSucumbencia(
                (registroFinanceiro.getProcesso().getNormalizadoValorAcao() * registroFinanceiro.getHonorarioSucumbencia())
        );

        dadosTela.setValorTotalReceber(
                (dadosTela.getValorTotalProcesso() + dadosTela.getValorHonorarioSucumbencia())
        );

        return dadosTela;
    }

    public List<ReceitaUltimosMesesDTO> getReceitaUltimos6Meses() {
        int anoAtual = LocalDate.now().getYear();
        List<RegistroFinanceiro> ultimos6 = registroFinanceiroRepository.findByAnoOrderByAnoDescMesDesc(anoAtual);

        Double receitaJaneiro = 0.0;
        ReceitaUltimosMesesDTO dadosJaneiro = new ReceitaUltimosMesesDTO(Meses.JANEIRO, anoAtual);

        Double receitaFevereiro = 0.0;
        ReceitaUltimosMesesDTO dadosFevereiro = new ReceitaUltimosMesesDTO(Meses.FEVEREIRO, anoAtual);

        Double receitaMarco = 0.0;
        ReceitaUltimosMesesDTO dadosMarco = new ReceitaUltimosMesesDTO(Meses.MARCO, anoAtual);

        Double receitaAbril = 0.0;
        ReceitaUltimosMesesDTO dadosAbril = new ReceitaUltimosMesesDTO(Meses.ABRIL, anoAtual);

        Double receitaMaio = 0.0;
        ReceitaUltimosMesesDTO dadosMaio = new ReceitaUltimosMesesDTO(Meses.MAIO, anoAtual);

        Double receitaJunho = 0.0;
        ReceitaUltimosMesesDTO dadosJunho = new ReceitaUltimosMesesDTO(Meses.JUNHO, anoAtual);

        Double receitaJulho = 0.0;
        ReceitaUltimosMesesDTO dadosJulho = new ReceitaUltimosMesesDTO(Meses.JULHO, anoAtual);

        Double receitaAgosto = 0.0;
        ReceitaUltimosMesesDTO dadosAgosto = new ReceitaUltimosMesesDTO(Meses.AGOSTO, anoAtual);

        Double receitaSetembro = 0.0;
        ReceitaUltimosMesesDTO dadosSetembro = new ReceitaUltimosMesesDTO(Meses.SETEMBRO, anoAtual);

        Double receitaOutubro = 0.0;
        ReceitaUltimosMesesDTO dadosOutubro = new ReceitaUltimosMesesDTO(Meses.OUTUBRO, anoAtual);

        Double receitaNovembro = 0.0;
        ReceitaUltimosMesesDTO dadosNovembro = new ReceitaUltimosMesesDTO(Meses.NOVEMBRO, anoAtual);

        Double receitaDezembro = 0.0;
        ReceitaUltimosMesesDTO dadosDezembro = new ReceitaUltimosMesesDTO(Meses.DEZEMBRO, anoAtual);

        List<ReceitaUltimosMesesDTO> lista = new ArrayList<>();

        for (RegistroFinanceiro registroFinanceiroDavez : ultimos6) {
            Meses mes = registroFinanceiroDavez.getMes();
            Double valorReceita = (
                    (registroFinanceiroDavez.getValorPagar() + registroFinanceiroDavez.getValorPago())
                            +
                            (registroFinanceiroDavez.getProcesso().getNormalizadoValorAcao() * registroFinanceiroDavez.getHonorarioSucumbencia())
            );

            switch (mes) {
                case JANEIRO -> receitaJaneiro += valorReceita;
                case FEVEREIRO -> receitaFevereiro += valorReceita;
                case MARCO -> receitaMarco += valorReceita;
                case ABRIL -> receitaAbril += valorReceita;
                case MAIO -> receitaMaio += valorReceita;
                case JUNHO -> receitaJunho += valorReceita;
                case JULHO -> receitaJulho += valorReceita;
                case AGOSTO -> receitaAgosto += valorReceita;
                case SETEMBRO -> receitaSetembro += valorReceita;
                case OUTUBRO -> receitaOutubro += valorReceita;
                case NOVEMBRO -> receitaNovembro += valorReceita;
                case DEZEMBRO -> receitaDezembro += valorReceita;
            }
        }

        dadosJaneiro.setReceita(BigDecimal.valueOf(receitaJaneiro).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosFevereiro.setReceita(BigDecimal.valueOf(receitaFevereiro).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosMarco.setReceita(BigDecimal.valueOf(receitaMarco).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosAbril.setReceita(BigDecimal.valueOf(receitaAbril).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosMaio.setReceita(BigDecimal.valueOf(receitaMaio).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosJunho.setReceita(BigDecimal.valueOf(receitaJunho).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosJulho.setReceita(BigDecimal.valueOf(receitaJulho).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosAgosto.setReceita(BigDecimal.valueOf(receitaAgosto).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosSetembro.setReceita(BigDecimal.valueOf(receitaSetembro).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosOutubro.setReceita(BigDecimal.valueOf(receitaOutubro).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosNovembro.setReceita(BigDecimal.valueOf(receitaNovembro).setScale(2, RoundingMode.HALF_UP).doubleValue());
        dadosDezembro.setReceita(BigDecimal.valueOf(receitaDezembro).setScale(2, RoundingMode.HALF_UP).doubleValue());

        lista.addAll(Arrays.asList(
                dadosJaneiro, dadosFevereiro, dadosMarco, dadosAbril,
                dadosMaio, dadosJunho, dadosJulho, dadosAgosto,
                dadosSetembro, dadosOutubro, dadosNovembro, dadosDezembro
        ));
        List<ReceitaUltimosMesesDTO> mesesComReceita = lista.stream()
                .filter(dto -> dto.getReceita() > 0)
                .sorted(Comparator.comparing(ReceitaUltimosMesesDTO::getAno)
                        .thenComparing(dto -> dto.getMes().ordinal()))
                .collect(Collectors.toList());

        int total = mesesComReceita.size();
        // Criar uma nova ArrayList em vez de retornar SubList (que não pode ser serializado)
        return new ArrayList<>(mesesComReceita.subList(Math.max(total - 6, 0), total));

    }


}