package com.project.softwave.backend_SoftWave.Jobs.messaging;

import com.project.softwave.backend_SoftWave.Jobs.ParametrosAPI;
import com.project.softwave.backend_SoftWave.Jobs.ProcessoGrau1API;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProcessoConsumer {

    private final ProcessoGrau1API processoGrau1API;

    public ProcessoConsumer(ProcessoGrau1API processoGrau1API) {
        this.processoGrau1API = processoGrau1API;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(ProcessoMessage msg) {
        try {
            String numero = msg.getNumeroProcesso();
            System.out.println("Received processo: " + numero + " correlationId=" + msg.getCorrelationId());
            ParametrosAPI.resetParametros();
            ParametrosAPI.setParametroProcesso(numero);
            processoGrau1API.getApiParams();
            System.out.println("Processed processo: " + numero);
        } catch (Exception e) {
            System.err.println("Error processing message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
