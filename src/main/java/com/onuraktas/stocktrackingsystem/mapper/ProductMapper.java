package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.entity.Product;

import java.util.List;
import java.util.Objects;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        if (Objects.isNull(product))
            return null;
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .amount(product.getAmount())
                .build();
    }

    public static ProductDto toEntity(Product product){
        if (Objects.isNull(product))
            return null;
        return ProductDto.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .amount(product.getAmount())
                .build();
    }

    public static Product toEntity(CreateProductRequest createProductRequest){
        if (Objects.isNull(createProductRequest))
            return null;
        return Product.builder()
                .productName(createProductRequest.getProductName())
                .description(createProductRequest.getDescription())
                .amount(createProductRequest.getAmount())
                .build();
    }

    public static CreateProductResponse toCreateProductResponse(Product product){
        if (Objects.isNull(product))
            return null;

        return CreateProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .build();
    }

    public static List<ProductDto> toDtoList(List<Product> products){
        return  products.stream().parallel()
                .map(ProductMapper::toDto)
                .toList();
    }
}
