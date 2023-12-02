package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateCategoryNameRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.exception.CategoryAlreadyExistsException;
import com.onuraktas.stocktrackingsystem.mapper.CategoryMapper;
import com.onuraktas.stocktrackingsystem.message.CategoryMessages;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import com.onuraktas.stocktrackingsystem.repository.ProductRepository;
import com.onuraktas.stocktrackingsystem.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest categoryRequest) {
        this.checkCategoryNameExists(categoryRequest.getCategoryName());

        Category category = this.categoryRepository.save(CategoryMapper.toEntity(categoryRequest));
        CreateCategoryResponse createCategoryResponse = CategoryMapper.toCreateCategoryResponse(category);
        createCategoryResponse.setStatus(Status.OK.getStatus());

        return createCategoryResponse;
    }

    private void checkCategoryNameExists(String categoryName) {
        Category existCategory = categoryRepository.findByCategoryName(categoryName);

        if (Objects.nonNull(existCategory))
            throw new CategoryAlreadyExistsException(CategoryMessages.CATEGORY_ALREADY_EXIST);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return CategoryMapper.toDtoList(this.categoryRepository.findAll());
    }

    @Override
    public CategoryDto getCategory(UUID categoryId) {
        return CategoryMapper.toDto(this.categoryRepository.findById(categoryId).orElseThrow(() -> new NoSuchElementException(CategoryMessages.CATEGORY_NOT_FOUND)));
    }

    @Override
    public ResponseEntity<CategoryDto> updateCategory(UUID categoryId, CategoryDto categoryDto) {
        if (Objects.isNull(categoryId) || Objects.isNull(categoryDto.getCategoryId()) || !Objects.equals(categoryId,categoryDto.getCategoryId()))
            return ResponseEntity.badRequest().build();

        Optional<Category> existCategory = categoryRepository.findById(categoryId);
        if (existCategory.isEmpty())
            return ResponseEntity.notFound().build();

        final CategoryDto updateCategory = this.save(categoryDto);
        if (Objects.nonNull(updateCategory))
            return ResponseEntity.ok(updateCategory);

        return ResponseEntity.internalServerError().build();
    }

    @Override
    public CategoryDto updateCategoryName(UUID categoryId, UpdateCategoryNameRequest request) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new NoSuchElementException(CategoryMessages.CATEGORY_NOT_FOUND));
        category.setCategoryName(request.getCategoryName());
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    private CategoryDto save (CategoryDto categoryDto){
        Category category = CategoryMapper.toEntity(categoryDto);
        category = categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }
}
