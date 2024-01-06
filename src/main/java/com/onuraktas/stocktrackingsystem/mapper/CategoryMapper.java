package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;
import java.util.List;
import java.util.Objects;

public class CategoryMapper {

    private CategoryMapper() {
    }

    public static CategoryDto toDto(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }

        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .isActive(category.getIsActive())
                .build();
    }

    public static CategoryDto toEntity(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }
        return CategoryDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .isActive(category.getIsActive())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if (Objects.isNull(categoryDto)) {
            return null;
        }
        return Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .build();
    }

    public static Category toEntity(CreateCategoryRequest categoryRequest) {
        if (Objects.isNull(categoryRequest)) {
            return null;
        }
        return Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();
    }

    public static CreateCategoryResponse toCreateCategoryResponse(Category category) {
        if (Objects.isNull(category)) {
            return new CreateCategoryResponse();
        }
        return CreateCategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .build();
    }

    public static List<CategoryDto> toDtoList(List<Category> categories) {
        return categories.stream().parallel()
                .map(CategoryMapper::toDto)
                .toList();
    }
}
