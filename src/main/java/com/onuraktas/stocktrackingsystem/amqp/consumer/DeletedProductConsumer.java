package com.onuraktas.stocktrackingsystem.amqp.consumer;

import com.onuraktas.stocktrackingsystem.constant.QueueName;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedProductMessage;
import com.onuraktas.stocktrackingsystem.service.CategoryProductRelService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class DeletedProductConsumer {

    private final CategoryProductRelService categoryProductRelService;

    public DeletedProductConsumer(CategoryProductRelService categoryProductRelService) {
        this.categoryProductRelService = categoryProductRelService;
    }

    @RabbitListener(queues = QueueName.DELETED_PRODUCT_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public <T extends DeletedProductMessage> void deletedProductListener(@Payload List<T> message) {

        List<UUID> deletedProductIdList = message.stream().filter(Objects::nonNull).map(DeletedProductMessage::getProductId).toList();

        this.categoryProductRelService.deleteCategoryProductRelByProductId(deletedProductIdList);
    }
}
