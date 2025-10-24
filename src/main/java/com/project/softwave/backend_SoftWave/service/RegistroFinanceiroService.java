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
        List<Object[]> resultados = registroFinanceiroRepository.findUltimos6MesesRegistros();

        // Cria mapa ano+mes -> receita
        Map<String, BigDecimal> mapaReceita = resultados.stream()
                .collect(Collectors.toMap(
                        r -> r[1] + "-" + r[0], // mes-ano
                        r -> (BigDecimal) r[2]
                ));

        List<ReceitaUltimosMesesDTO> lista = new ArrayList<>();

        // Pega últimos 6 meses
        LocalDate hoje = LocalDate.now();
        for (int i = 5; i >= 0; i--) {
            LocalDate mesReferencia = hoje.minusMonths(i);
            int mes = mesReferencia.getMonthValue();
            int ano = mesReferencia.getYear();

            String key = mes + "-" + ano;
            BigDecimal receita = mapaReceita.getOrDefault(key, BigDecimal.ZERO);

            lista.add(new ReceitaUltimosMesesDTO(
                    mesReferencia.getMonth().name(), // "JANEIRO", "FEVEREIRO"...
                    ano,
                    receita
            ));
        }

        return lista;
    }

}