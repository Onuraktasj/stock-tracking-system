package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.entity.Product;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.ProductMapper;
import com.onuraktas.stocktrackingsystem.repository.ProductRepository;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductRequest createProductRequest) {

        Product product = this.productRepository.save(ProductMapper.toEntity(createProductRequest));
        CreateProductResponse createProductResponse = ProductMapper.toCreateProductResponse(product);
        createProductResponse.setStatus(Status.OK.getStatus());
        return createProductResponse;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return ProductMapper.toDtoList(productRepository.findAll());
    }
}
