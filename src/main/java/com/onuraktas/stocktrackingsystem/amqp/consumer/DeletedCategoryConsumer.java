package com.onuraktas.stocktrackingsystem.amqp.consumer;

import com.onuraktas.stocktrackingsystem.contant.QueueName;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedCategoryMessage;
import com.onuraktas.stocktrackingsystem.service.CategoryProductRelService;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DeletedCategoryConsumer {

    private final CategoryProductRelService categoryProductRelService;
    private final ProductService productService;

    public DeletedCategoryConsumer(CategoryProductRelService categoryProductRelService, ProductService productService) {
        this.categoryProductRelService = categoryProductRelService;
        this.productService = productService;
    }

    @RabbitListener(queues = QueueName.DELETED_CATEGORY_QUEUE, containerFactory = "rabbitListenerContainerFactory")
    public void deletedCategoryListener(@Payload DeletedCategoryMessage message) {
        List<UUID> productIdList = this.categoryProductRelService.deleteCategoryProductRelByCategoryId(message.getCategoryId());
        this.productService.deleteProductListByProductListIdList(productIdList);
    }
}
