package com.onuraktas.stocktrackingsystem.amqp.consumer;

import com.onuraktas.stocktrackingsystem.constant.QueueName;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedCategoryMessage;
import com.onuraktas.stocktrackingsystem.service.CategoryProductRelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class DeletedCategoryConsumer {

    private final CategoryProductRelService categoryProductRelService;

    public DeletedCategoryConsumer(CategoryProductRelService categoryProductRelService) {
        this.categoryProductRelService = categoryProductRelService;
    }

    @RabbitListener(queues = QueueName.DELETED_CATEGORY_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void deletedCategoryListener(@Payload DeletedCategoryMessage message) {
        this.categoryProductRelService.deleteCategoryProductRelByCategoryId(message.getCategoryId());
    }
}
