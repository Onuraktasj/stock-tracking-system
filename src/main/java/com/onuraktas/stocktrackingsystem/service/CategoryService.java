package com.onuraktas.stocktrackingsystem.service;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateCategoryNameRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.dto.response.DeleteCategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CreateCategoryResponse createCategory(CreateCategoryRequest categoryRequest);

    List<CategoryDto> getAllCategory();

    CategoryDto getCategory(UUID categoryId);

    CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto);

    CategoryDto updateCategory(UUID categoryId, UpdateCategoryNameRequest request);

    DeleteCategoryResponse deleteCategory(UUID categoryId);
}
