package com.onuraktas.stocktrackingsystem.controller;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateCategoryNameRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.dto.response.DeleteCategoryResponse;
import com.onuraktas.stocktrackingsystem.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }


    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable(value = "categoryId") UUID categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @PutMapping(value = "/{categoryId}")
    public  ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "categoryId") UUID categoryId, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.accepted().body(this.categoryService.updateCategory(categoryId,categoryDto));
    }

    @PatchMapping(value = "/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryName(@PathVariable(value = "categoryId")UUID categoryId, @RequestBody UpdateCategoryNameRequest request){
        return ResponseEntity.accepted().body(this.categoryService.updateCategory(categoryId,request));
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<DeleteCategoryResponse> deleteCategory(@PathVariable(value = "categoryId") UUID categoryId){
        return ResponseEntity.accepted().body(this.categoryService.deleteCategory(categoryId));
    }
}
