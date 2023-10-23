package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product/v1")
public class ProductController {

    private final ProductService productService;

    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest createProductRequest){
        return ResponseEntity.ok(productService.createProduct(createProductRequest));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ProductDto>> getAll (){
        return ResponseEntity.ok(productService.getAllProduct());
    }


}
