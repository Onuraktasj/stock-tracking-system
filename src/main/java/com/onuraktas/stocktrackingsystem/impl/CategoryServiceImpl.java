package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.mapper.CategoryMapper;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import com.onuraktas.stocktrackingsystem.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest categoryRequest) {
        Category category = this.categoryRepository.save(CategoryMapper.toEntity(categoryRequest));
        CreateCategoryResponse createCategoryResponse = CategoryMapper.toCreateCategoryResponse(category);
        createCategoryResponse.setStatus(Status.OK.getStatus());

        return createCategoryResponse;
    }
}
