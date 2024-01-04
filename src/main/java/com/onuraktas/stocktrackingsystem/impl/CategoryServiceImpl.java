package com.onuraktas.stocktrackingsystem.impl;

import com.onuraktas.stocktrackingsystem.amqp.producer.CategoryProducer;
import com.onuraktas.stocktrackingsystem.dto.amqp.DeletedCategoryMessage;
import com.onuraktas.stocktrackingsystem.dto.entity.CategoryDto;
import com.onuraktas.stocktrackingsystem.dto.request.CreateCategoryRequest;
import com.onuraktas.stocktrackingsystem.dto.request.UpdateCategoryNameRequest;
import com.onuraktas.stocktrackingsystem.dto.response.CreateCategoryResponse;
import com.onuraktas.stocktrackingsystem.dto.response.DeleteCategoryResponse;
import com.onuraktas.stocktrackingsystem.entity.Category;
import com.onuraktas.stocktrackingsystem.entity.enums.Status;
import com.onuraktas.stocktrackingsystem.exception.CategoryAlreadyExistsException;
import com.onuraktas.stocktrackingsystem.exception.CategoryBadRequestException;
import com.onuraktas.stocktrackingsystem.exception.CategoryNotFoundException;
import com.onuraktas.stocktrackingsystem.mapper.CategoryMapper;
import com.onuraktas.stocktrackingsystem.message.CategoryMessages;
import com.onuraktas.stocktrackingsystem.repository.CategoryRepository;
import com.onuraktas.stocktrackingsystem.service.CategoryService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryProducer categoryProducer;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryProducer categoryProducer){
        this.categoryRepository = categoryRepository;
        this.categoryProducer = categoryProducer;
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
    @Cacheable(value = "categoryCache", key = "'category_' + #categoryId")
    public CategoryDto getCategory(UUID categoryId) {
        return CategoryMapper.toDto(this.categoryRepository.findByCategoryIdAndIsActive(categoryId, Boolean.TRUE).orElseThrow(() -> new NoSuchElementException(CategoryMessages.CATEGORY_NOT_FOUND)));
    }

    @Override
    @CachePut(value = "categoryCache", key = "'category_' + #categoryId")
    public CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto) {
        if (Objects.isNull(categoryId) || Objects.isNull(categoryDto.getCategoryId()) || !Objects.equals(categoryId,categoryDto.getCategoryId()))
            throw new CategoryBadRequestException(CategoryMessages.CATEGORY_ID_NOT_MATCH);

        Category existingCategory = this.categoryRepository.findByCategoryIdAndIsActive(categoryId, Boolean.TRUE).orElseThrow(()-> new CategoryNotFoundException(CategoryMessages.CATEGORY_NOT_FOUND));

        existingCategory.setCategoryName(categoryDto.getCategoryName());
        existingCategory.setIsActive(categoryDto.getIsActive());

        this.categoryRepository.save(existingCategory);

        return CategoryMapper.toDto(existingCategory);
    }

    @Override
    @CachePut(value = "categoryCache", key = "'category_' + #categoryId")
    public CategoryDto updateCategory(UUID categoryId, UpdateCategoryNameRequest request) {

        if (Objects.isNull(request.getCategoryName()))
            throw new CategoryBadRequestException(CategoryMessages.CATEGORY_NAME_CANNOT_BE_NULL);

        Category category = this.categoryRepository.findByCategoryIdAndIsActive(categoryId, Boolean.TRUE).orElseThrow(()-> new CategoryNotFoundException(CategoryMessages.CATEGORY_NOT_FOUND));
        category.setCategoryName(request.getCategoryName());

        categoryRepository.save(category);

        return CategoryMapper.toDto(category);
    }

    @Override
    @CacheEvict(value = "categoryCache", key = "'category_' + #categoryId")
    public DeleteCategoryResponse deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findByCategoryIdAndIsActive(categoryId, Boolean.TRUE).orElseThrow(()-> new CategoryNotFoundException(CategoryMessages.CATEGORY_NOT_FOUND));
        category.setIsActive(Boolean.FALSE);
        categoryRepository.save(category);

        this.categoryProducer.sendToQueue(DeletedCategoryMessage.builder().categoryId(categoryId).build());

        return DeleteCategoryResponse.builder().categoryId(categoryId).build();
    }
}
