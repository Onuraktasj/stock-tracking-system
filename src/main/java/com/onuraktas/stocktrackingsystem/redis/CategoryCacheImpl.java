package com.onuraktas.stocktrackingsystem.redis;

import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.mapper.CategoryMapper;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class CategoryCacheImpl {

    private final CategoryRepository categoryRepository;
    private final RedisCacheImpl redisCacheImpl;


    public CategoryCacheImpl(CategoryRepository categoryRepository, RedisCacheImpl redisCacheImpl) {
        this.categoryRepository = categoryRepository;
        this.redisCacheImpl = redisCacheImpl;
    }

    public CategoryDto getCategoryFromCache(UUID categoryId){
        CategoryDto cachedCategory = redisCacheImpl.getCache("category_" + categoryId);

        if (Objects.isNull(cachedCategory)){
            CategoryDto categoryDto = CategoryMapper.toDto(categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new NoSuchElementException("Category not found")));

            redisCacheImpl.setCache("category_" + categoryId, categoryDto);

            return categoryDto;
        }

        return cachedCategory;
    }

    public void updateCategoryCache(CategoryDto categoryDto) {
        CategoryDto cachedCategory = redisCacheImpl.getCache("category_" + categoryDto.getCategoryId());
        if (Objects.nonNull(cachedCategory)){
            redisCacheImpl.setCache("category_" + categoryDto.getCategoryId(), categoryDto);
        }
    }

    public void deleteCategoryCache(UUID categoryId) {
        redisCacheImpl.deleteCache("category_" + categoryId);
    }
}
