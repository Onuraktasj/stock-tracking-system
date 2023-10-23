package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreateCategoryResponse> createCategory(@RequestBody CreateCategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
}
