package com.project.softwave.backend_SoftWave.Jobs.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProcessoProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "exchange_name";
    private static final String QUEUE = "queue_name";

    public ProcessoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String numeroProcesso, String correlationId) {
        if (!StringUtils.hasText(numeroProcesso)) {
            throw new IllegalArgumentException("O campo 'numeroProcesso' não pode ser nulo ou vazio.");
        }
        if (!StringUtils.hasText(correlationId)) {
            throw new IllegalArgumentException("O campo 'correlationId' não pode ser nulo ou vazio.");
        }

        ProcessoMessage msg = createProcessoMessage(numeroProcesso, correlationId);

        try {
            rabbitTemplate.convertAndSend(EXCHANGE, QUEUE, msg);
        } catch (Exception e) {
            throw new MessageSendingException("Falha ao enviar a mensagem para o RabbitMQ.", e);
        }
    }

    private ProcessoMessage createProcessoMessage(String numeroProcesso, String correlationId) {
        ProcessoMessage msg = new ProcessoMessage();
        msg.setNumeroProcesso(numeroProcesso);
        msg.setCorrelationId(correlationId);
        return msg;
    }

    public static class MessageSendingException extends RuntimeException {
        public MessageSendingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
