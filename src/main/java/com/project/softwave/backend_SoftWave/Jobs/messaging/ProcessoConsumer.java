package com.project.softwave.backend_SoftWave.Jobs.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.Processo;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoModel.UltimasMovimentacoes;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.ProcessoRepository;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoRepository.UltimasMovimentacoesRepository;
import com.project.softwave.backend_SoftWave.dto.ProcessoRabbit.ProcessoResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessoConsumer {

    private final ProcessoRepository processoRepository;
    private final UltimasMovimentacoesRepository ultimasMovimentacoesRepository;
    private final ObjectMapper objectMapper;

    public ProcessoConsumer(ProcessoRepository processoRepository,
                            UltimasMovimentacoesRepository ultimasMovimentacoesRepository,
                            ObjectMapper objectMapper) {
        this.processoRepository = processoRepository;
        this.ultimasMovimentacoesRepository = ultimasMovimentacoesRepository;
        this.objectMapper = objectMapper.findAndRegisterModules(); // para LocalDate
    }

    @RabbitListener(queues = "${broker.queue.name}")
    public void receberProcessos(List<Object> objetosRecebidos) {
        System.out.println("📥 Recebidos " + objetosRecebidos.size() + " objetos via RabbitMQ");

        try {
            for (Object obj : objetosRecebidos) {
                // Converter JSON para DTO
                ProcessoResponse response = objectMapper.convertValue(obj, ProcessoResponse.class);

                // Criar entidade Processo
                Processo processo = new Processo();
                processo.setNumeroProcesso(response.getNumeroProcesso());
                processo.setClasse(response.getClasse());
                processo.setAssunto(response.getAssunto());
                processo.setForo(response.getForo());
                processo.setVara(response.getVara());
                processo.setJuiz(response.getJuiz());
                processo.setApensado(response.getApensado());
                processo.setDistribuicao(response.getDistribuicao());
                processo.setControle(response.getControle());
                processo.setArea(response.getArea());
                processo.setValorAcao(response.getValorAcao());
                processo.setNormalizadoValorAcao(response.getNormalizadoValorAcao());
                processo.setAutor(response.getAutor());
                processo.setDescricao(response.getDescricao());
                processo.setExecutado(response.getExecutado());
                processo.setRequerente(response.getRequerente());
                processo.setRequerido(response.getRequerido());
                processo.setIndiciado(response.getIndiciado());

                // Salvar processo primeiro
                Processo processoSalvo = processoRepository.save(processo);

                // Mapear e salvar ultimasMovimentacoes
                if (response.getUltimasMovimentacoes() != null) {
                    List<UltimasMovimentacoes> movimentacoes = response.getUltimasMovimentacoes().stream()
                            .map(umr -> {
                                UltimasMovimentacoes m = new UltimasMovimentacoes();
                                m.setData(umr.getData());
                                m.setMovimento(umr.getMovimento());
                                m.setProcesso(processoSalvo); // FK
                                return m;
                            }).toList();

                    ultimasMovimentacoesRepository.saveAll(movimentacoes);
                }
            }

            System.out.println("✅ Processos e movimentações salvos com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao converter ou salvar processos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
