package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateProductAmountRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.entity.Product;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.ProductMapper;
import com.onuraktas.stocktrackingsystem.message.ProductMessages;
import com.onuraktas.stocktrackingsystem.repository.ProductRepository;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public ProductDto getProduct(UUID productId) {
        return ProductMapper.toDto(productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException(ProductMessages.PRODUCT_NOT_FOUND)));

    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(UUID productId, ProductDto productDto) {
        if (Objects.isNull(productId) || Objects.isNull(productDto.getProductId()) || !Objects.equals(productId,productDto.getProductId()))
            return ResponseEntity.badRequest().build();

        Optional<Product> existProduct = productRepository.findById(productId);
        if (existProduct.isEmpty())
            return ResponseEntity.notFound().build();

        final ProductDto updateProduct = this.save(productDto);

        if (Objects.nonNull(updateProduct))
            return ResponseEntity.ok(updateProduct);


        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ProductDto updateProductAmount(UUID productId, UpdateProductAmountRequest request) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException(ProductMessages.PRODUCT_NOT_FOUND));
        product.setAmount(request.getAmount());
        productRepository.save(product);
        return ProductMapper.toDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException(ProductMessages.PRODUCT_NOT_FOUND));
        productRepository.deleteById(productId);

    }

    private ProductDto save (ProductDto productDto){
        Product product = ProductMapper.toEntity(productDto);
        product = productRepository.save(product);
        return ProductMapper.toDto(product);
    }
}
