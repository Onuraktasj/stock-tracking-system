package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;

import java.util.List;

public interface ProductService {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    List<ProductDto> getAllProduct();
}
