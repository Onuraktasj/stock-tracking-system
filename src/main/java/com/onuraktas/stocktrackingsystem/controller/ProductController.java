package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.ProductDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateProductRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateProductAmountRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateProductResponse;
import com.onuraktas.stocktrackingsystem.service.ProductService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
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

    @GetMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable(value = "productId") UUID productId){
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PutMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(value = "productId") UUID productId, @RequestBody @NotNull ProductDto productDto){
        return productService.updateProduct(productId,productDto);
    }

    @PatchMapping(value = "/{productId}")
    public ResponseEntity<ProductDto> updateProductAmount(@PathVariable(value = "productId") UUID productId, @RequestBody UpdateProductAmountRequest request){
        return ResponseEntity.ok(productService.updateProductAmount(productId,request));
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable(value = "productId") UUID productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product Delete Successfully!");
    }

    @GetMapping(value = "/getByCategory/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductListByCategory(@PathVariable(value = "categoryId") UUID categoryId){
        return ResponseEntity.ok(productService.getProductListByCategory(categoryId));
    }


}
