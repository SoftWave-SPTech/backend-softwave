package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.RegistroFinanceiro;
import com.project.softwave.backend_SoftWave.entity.StatusFinanceiro;
import com.project.softwave.backend_SoftWave.exception.CorpoRequisicaoVazioException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.repository.RegistroFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegistroFinanceiroService {

    @Autowired
    private RegistroFinanceiroRepository registroFinanceiroRepository;

    public RegistroFinanceiro criarRegistro(RegistroFinanceiro registroFinanceiro) {
        if(registroFinanceiro != null){
            return registroFinanceiroRepository.save(registroFinanceiro);
        }
        throw  new CorpoRequisicaoVazioException("Campos vazios!");
    }

    public List<RegistroFinanceiro> listarRegistros() {
        return registroFinanceiroRepository.findAll();
    }

    public RegistroFinanceiro buscarRegistroPorId(Integer id) {
        return registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );
    }

    public RegistroFinanceiro atualizarRegistro(Integer id, RegistroFinanceiro registroFinanceiro) {
        RegistroFinanceiro registroFinanceiroAtualizado = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );

        registroFinanceiroAtualizado.setAno(registroFinanceiro.getAno());
        registroFinanceiroAtualizado.setStatusFinanceiro(registroFinanceiro.getStatusFinanceiro());
        registroFinanceiroAtualizado.setMes(registroFinanceiro.getMes());
        registroFinanceiroAtualizado.setCliente(registroFinanceiro.getCliente());
        registroFinanceiroAtualizado.setMetodoPagamento(registroFinanceiro.getMetodoPagamento());
        registroFinanceiroAtualizado.setParcelaAtual(registroFinanceiro.getParcelaAtual());
        registroFinanceiroAtualizado.setProcesso(registroFinanceiro.getProcesso());
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

    public RegistroFinanceiro atualizarStatusRegistro(Integer id, StatusFinanceiro statusFinanceiro){
        RegistroFinanceiro registroFinanceiroAtualizado = registroFinanceiroRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntidadeNaoEncontradaException("Registro não encontrado!")
                );

        registroFinanceiroAtualizado.setStatusFinanceiro(statusFinanceiro);

        return registroFinanceiroRepository.save(registroFinanceiroAtualizado);
    }

    public RegistroFinanceiro funcaopararetornardadosdecimadatela(){
        return  null;
    }
}