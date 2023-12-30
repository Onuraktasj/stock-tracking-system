package com.onuraktas.stocktrackingsystem.amqp.producer;

import com.onuraktas.stocktrackingsystem.contant.QueueName;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedCategoryMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryProducer {

    private final RabbitTemplate rabbitTemplate;


    public CategoryProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(DeletedCategoryMessage message) {
        rabbitTemplate.convertAndSend(QueueName.DELETED_CATEGORY_QUEUE, message);
    }
}
