package com.onuraktas.stocktrackingsystem.mapper;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryProductRelDto;
import com.onuraktas.stocktrackingsystem.entity.CategoryProductRel;

import java.util.List;
import java.util.Objects;

public class CategoryProductRelMapper {

    public static CategoryProductRelDto toDto(CategoryProductRel categoryProductRel) {
        if (Objects.isNull(categoryProductRel))
            return null;

        return CategoryProductRelDto.builder()
                .id(categoryProductRel.getId())
                .categoryId(categoryProductRel.getCategoryId())
                .productId(categoryProductRel.getProductId())
                .isActive(categoryProductRel.getIsActive())
                .build();
    }

    public static CategoryProductRel toEntity(CategoryProductRelDto categoryProductRelDto) {
        if (Objects.isNull(categoryProductRelDto))
            return null;

        return CategoryProductRel.builder()
                .id(categoryProductRelDto.getId())
                .categoryId(categoryProductRelDto.getCategoryId())
                .productId(categoryProductRelDto.getProductId())
                .isActive(categoryProductRelDto.getIsActive())
                .build();
    }

    public static List<CategoryProductRelDto> toDtoList(List<CategoryProductRel> categoryProductRelList) {
        if (Objects.isNull(categoryProductRelList))
            return null;

        return categoryProductRelList.stream().map(CategoryProductRelMapper::toDto).toList();
    }

    public static List<CategoryProductRel> toEntityList(List<CategoryProductRelDto> categoryProductRelDtoList) {
        if (Objects.isNull(categoryProductRelDtoList))
            return null;

        return categoryProductRelDtoList.stream().map(CategoryProductRelMapper::toEntity).toList();
    }
}
