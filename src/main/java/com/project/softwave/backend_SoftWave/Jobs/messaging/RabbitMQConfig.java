package com.project.softwave.backend_SoftWave.Jobs.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "processo.exchange";
    public static final String QUEUE = "processo.queue";

    @Bean
    public Queue processoQueue() {
        return new Queue(QUEUE, true);
    }

    public DirectExchange processoExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue processoQueue, DirectExchange processoExchange) {
        return BindingBuilder.bind(processoQueue).to(processoExchange).with(QUEUE);
    }
}
