package com.onuraktas.stocktrackingsystem.amqp.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductProducer {

    private final RabbitTemplate rabbitTemplate;

    public ProductProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public <T> void sendToQueue(String queueName, T message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
