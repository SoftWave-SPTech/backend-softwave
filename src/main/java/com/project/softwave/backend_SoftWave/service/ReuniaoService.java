package com.project.softwave.backend_SoftWave.service;

import com.project.softwave.backend_SoftWave.entity.Reuniao;
import com.project.softwave.backend_SoftWave.entity.StatusReuniao;
import com.project.softwave.backend_SoftWave.exception.EntidadeConflitoException;
import com.project.softwave.backend_SoftWave.exception.EntidadeNaoEncontradaException;
import com.project.softwave.backend_SoftWave.exception.NoContentException;
import com.project.softwave.backend_SoftWave.repository.ReuniaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReuniaoService {


    @Autowired
    private ReuniaoRepository reuniaoRepository;


    public List<Reuniao> listarReuniao() {
        List<Reuniao> todos = reuniaoRepository.findAll();

        if(todos.isEmpty()){
            throw new NoContentException("Nenhuma reuniao encontrada!");
        }
        return todos;

    }

    public Reuniao buscarPorId(Integer id) {

        return reuniaoRepository.findById(id).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Reunião %d não encontrada".formatted(id))
        );

    }


    public Reuniao cadastrarReuniao(Reuniao reuniao) {
//        boolean conflitoAdvogado = reuniaoRepository.existsByIdAdvogadoAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
//                reuniao.getIdAdvogado(), reuniao.getDataHoraFim(), reuniao.getDataHoraInicio());
//
//        boolean conflitoCliente = reuniaoRepository.existsByIdClienteAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
//                reuniao.getIdCliente(), reuniao.getDataHoraFim(), reuniao.getDataHoraInicio());
//
//        if (conflitoAdvogado || conflitoCliente) {
//            throw new EntidadeConflitoException("O advogado ou o cliente já possuem outra reunião neste horário.");
//        }

        return reuniaoRepository.save(reuniao);
    }



    public Reuniao atualizarReuniao(Reuniao reuniaoParaAtualizar, Integer id) {


        Reuniao reuniaoExistente = reuniaoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Reunião não encontrada!"));

        if (reuniaoExistente.getStatusReuniao() == StatusReuniao.REALIZADA) {
            throw new EntidadeConflitoException("A reunião não pode ser atualizada, pois já foi realizada.");
        }

        reuniaoParaAtualizar.setId(id);
        return reuniaoRepository.save(reuniaoParaAtualizar);
    }

    public void removerPorId(Integer id) {


            Reuniao reuniao = reuniaoRepository.findById(id).orElseThrow(
                    () -> new EntidadeNaoEncontradaException("Reunião não encontrada!")
            );


            if (reuniao.getStatusReuniao() == StatusReuniao.CANCELADA) {

                reuniaoRepository.deleteById(id);
            } else {
                throw new EntidadeConflitoException("A reunião não pode ser deletada, pois não está no status CANCELADA.");
            }
        }

    }



