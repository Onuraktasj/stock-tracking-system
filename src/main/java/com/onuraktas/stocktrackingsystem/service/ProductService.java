package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateProductAmountRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.dto.response.DeleteProductByIdResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    List<ProductDto> getAllProduct();
    ProductDto getProduct(UUID productId);

    ResponseEntity<ProductDto> updateProduct(UUID productId, ProductDto productDto);

    ProductDto updateProductAmount(UUID productId, UpdateProductAmountRequest request);

    DeleteProductByIdResponse deleteProductById(UUID productId);

    List<ProductDto> getProductListByCategory(UUID categoryId);
}
