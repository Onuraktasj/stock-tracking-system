package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;

import java.util.Objects;

public class CategoryMapper {

    public static Category toDto(CategoryDto categoryDto){
        if (Objects.isNull(categoryDto))
            return null;

        return Category.builder()
                .categoryId(categoryDto.getCategoriesId())
                .categoryName(categoryDto.getCategoriesName())
                .build();
    }

    public static CategoryDto toEntity(Category category){
        if (Objects.isNull(category))
            return null;
        return CategoryDto.builder()
                .categoriesId(category.getCategoryId())
                .categoriesName(category.getCategoryName())
                .build();
    }

    public static Category toEntity(CreateCategoryRequest categoryRequest){
        if (Objects.isNull(categoryRequest))
            return null;
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }

    public static CreateCategoryResponse toCreateCategoryResponse(Category category){
        if (Objects.isNull(category))
            return null;
        return CreateCategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .build();
    }
}
